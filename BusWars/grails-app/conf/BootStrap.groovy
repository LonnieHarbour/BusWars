import grails.converters.*
import buswars.Product
import buswars.Purchased

class BootStrap {

	
    def init = { servletContext ->
		new Product(name: "Light Saber", price: 114.11, qtyOnHand: 1500, sku: "10-1050" ).save()		
		new Product(name: "Thor's Hammer", price: 12.11, qtyOnHand: 1500, sku: "99AA 450" ).save()
				
		JSON.registerObjectMarshaller(Purchased) { Purchased p ->
			def output = [:]
            output['trans-id'] = p.id
			output['sku'] = p.sku
			output['name'] = p.name
			output['qty-purchased'] = p.qtyPurchased
			output['price'] = p.price
			output['total'] = p.price * p.qtyPurchased
			output
		}
		
		JSON.registerObjectMarshaller(Product) { Product p ->
			def output = [:]
			output['sku'] = p.sku
			output['name'] = p.name
			output['price'] = p.price
			output['qty'] = p.qtyOnHand
			output
		}
    }
	
    def destroy = {
    }
}
