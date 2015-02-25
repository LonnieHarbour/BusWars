package buswars

class Transaction {
	
	float total
	Product product
	
	static hasMany = [lines:Line]
	
    static constraints = {
    }
}
