import grails.converters.*
import buswars.Catalog;
import buswars.DatabaseInit;
import buswars.Product
import buswars.Transaction

class BootStrap {

	
    def init = { servletContext ->
		
		new DatabaseInit().init()
				
		JSON.registerObjectMarshaller(Transaction) { Transaction p ->
			def output = [:]
            output['trans-id'] = p.id
			output['sku'] = p.sku
			output['name'] = p.name
			output['qty-purchased'] = p.qtyFullfilled
			output['price'] = p.price
			output['total'] = p.total
			output['accepted'] = p.accepted
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
		
    }
	
    def destroy = {
    }
}
