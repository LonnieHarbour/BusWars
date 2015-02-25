package buswars

class Product {

	String sku
	String name	
	float price
	
	static hasMany = [transactions:Transaction]
	
	static hasOne = [inventory:Inventory]
	
    static constraints = {
		inventory nullable: true 
    }
}
