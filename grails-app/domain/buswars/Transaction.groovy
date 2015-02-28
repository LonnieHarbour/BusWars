package buswars

class Transaction {
	
	float total
	Account account
	boolean complete
	
	//def lineItems 
	
	static hasMany = [lineItems:LineItem]
	
    static constraints = {
    }
}
