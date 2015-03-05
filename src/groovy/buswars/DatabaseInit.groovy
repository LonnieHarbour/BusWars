package buswars

class DatabaseInit {

	static products = [
		[name:"Light Saber", price:114.11, sku:"10-1050", qtyOnHand:47],
		[name:"Thor's Hammer", price:12.5, sku:"99AA 450", qtyOnHand:100],
		[name:"Dodge Challenger SRT Hellcat", price:64091.0, sku:"D99959699-HC", qtyOnHand:1],
		[name:"Silly Putty", price:2.99, sku:"4107", qtyOnHand:10000],
		[name:"Marginally Operational PS-1", price:14.0, sku:"SONY-1230-1", qtyOnHand:0]
	]
	
	static accounts = ["CC & Company", "McPeek Inc.", "LH Heavy Industries"]
	
	def static init() {
		products.each { datum ->
			def product = new Product(name:datum.name, price:datum.price, sku:datum.sku).save()
			if (datum.qtyOnHand) {
				product.inventory = new Inventory(qtyOnHand: datum.qtyOnHand, product:product).save()
				product.save()
			}			
		}
		
		accounts.each { co ->
			new Account(name:co).save()			
		}
	}
}
