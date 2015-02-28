package buswars

class ProductService {

	Account account(int accountId) {		
		Account account = Account.where { id == accountId }.find()
		if (!account) throw new NotFound()
		account		
	}
	
	
    Transaction purchase(Account account, TransactionRequest order) {	
		
		Transaction transaction = new Transaction(account:account).save()	
		
		def products = Product.findBySkuInList(order?.lines.collect { sku } )		
				
		order?.lines.each { line ->
			
			Product product = products.find { line.sku = sku }
			
			int qtyFullfilled = fullfill(product, line )
			
			if (qty) {
				product.qtyOnHand -= qtyFullfilled
				product.save()
			}
			
			transaction.lineItems.add(new LineItem(
				accepted: qty>0,
				product:product,
				transaction:transaction,
				qtyFullfilled: qtyFullfilled,
				qtyRequest: line.qtyRequested
				))
		}
		
		transaction.save()
    }	
	
	private int fullfill(Product product, TransactionRequest bidRequest) {
		if (bidRequest?.allOrNone) {
			product.qtyOnHand >= bidRequest.qty ? bidRequest.qty : 0
		} else {
		    product.qtyOnHand - bidRequest.qty <= 0 ? product.qtyOnHand : bidRequest.qty
		}
	}
	
	private Product productBySku(def skuId) {		
		Product product = Product.where { sku == skuId }.find()		 
		if (!product) throw new NotFound()		
		product
	}
	
}
