package buswars

class Account {
	
	String name
	
	static hasMany = [transactions:Transaction]
	
    static constraints = {
		transactions nullable: true
    }
}
