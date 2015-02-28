import grails.converters.*
import buswars.Account;
import buswars.Catalog;
import buswars.DatabaseInit;
import buswars.LineItem;
import buswars.Product
import buswars.Transaction

class BootStrap {

	
    def init = { servletContext ->
		
		new DatabaseInit().init()
				
		JSON.registerObjectMarshaller(Transaction) { Transaction t ->
			def output = [:]
            output['trans-id'] = t.id
			output['account'] = t.account?.id
			output['total'] = t.total			
			output['lineItems'] = t.lineItems
			output
		}
		
		JSON.registerObjectMarshaller(LineItem) { LineItem i ->
			def output = [:]
			output['sku'] = i.product?.sku
			output['qty'] = i.qtyFullfilled
			output['name'] = i.product?.name
			output['price'] = i.product?.price
			output['subtotal'] = i.total
			output['accepted'] = i.accepted
			output
		}
		
		JSON.registerObjectMarshaller(Product) { Product p ->
			def output = [:]
			output['sku'] = p.sku
			output['name'] = p.name
			output['price'] = p.price
			output['qty'] = p.inventory ? p.inventory.qtyOnHand : 0
			output
		}
		
		JSON.registerObjectMarshaller(Catalog) { Catalog c ->
			def output = [:]
			output['sku'] = c.product?.sku
			output['name'] = c.product.name
			output['price'] = c.product.price
			output
		}
		
		JSON.registerObjectMarshaller(Account) { Account a ->
			def output = [:]
			output['id'] = a.id
			output['name'] = a.name
			output
		}
		
    }
	
    def destroy = {
    }
}
