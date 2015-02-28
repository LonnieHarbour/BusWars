package buswars

class ProductService {

	Transaction purchase(int accountId, TransactionRequest order) {
		
		Account account = account(accountId)	
		
		Transaction transaction = new Transaction(account:account, complete:true).save()	
		
		def products = Product.where { sku in order?.lines.collect { it.sku } }.list()
				
		order?.lines.each { line ->
			
			Product product = products.find { p -> p.sku == line.sku }
			
			if (product) {
				
				int qtyFullfilled = fullfill(product, order, line )
				
				if (qtyFullfilled) {
					product.inventory?.qtyOnHand -= qtyFullfilled
					product.save()
				}
				
				float total = (product.price * qtyFullfilled).round(2)
				
				LineItem item = new LineItem(
					accepted: qtyFullfilled>0,
					product:product,
					qtyFullfilled: qtyFullfilled,
					qtyRequested: line.qty,
					total: total
					).save()
					
				transaction.lineItems.add(item)
				
				transaction.total += total
			} else {
						
			   transaction.complete = false
			}			
		}
		
		transaction.save()
    }	
	
	private int fullfill(Product product, TransactionRequest order, TransactionLine line) {
		if (order?.allOrNone) {
			product.inventory?.qtyOnHand >= line.qty ? line.qty : 0
		} else {
		    product.inventory?.qtyOnHand - line.qty <= 0 ? product.inventory?.qtyOnHand : line.qty
		}
	}
	
	/*
	 * Finders
	 */	
	Account account(int accountId) {
		Account.where { id == accountId }.find()
	}
		
	Product productBySku(def skuId) {		
		Product.where { sku == skuId }.find()
	}
	
	def historyByAccountTranid(int accountid, int tranid) {
		Transaction t = Transaction.where { id == tranid && account.id == accountid }.find()
		t
	}
	
	def historyByTranid(int tranid) {
		Transaction.where { id == tranid }.find()
	}

	
	def getProducts() {
		Product.where { inventory?.qtyOnHand > 0 }.list()
	}
	
	def getCatalog() {
		Product.list()?.collect { new Catalog(product:it) }
	}
	
	def getCustomers() {
		Account.list()
	}
	
	def getHistory() {
		Transaction.list()
	}
	
}
