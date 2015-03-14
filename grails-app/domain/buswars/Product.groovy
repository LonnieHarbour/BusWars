package buswars

class Product {

	String sku
	String name
	float price

	static hasOne = [inventory:Inventory]

	static constraints = { inventory nullable: true  }
}
