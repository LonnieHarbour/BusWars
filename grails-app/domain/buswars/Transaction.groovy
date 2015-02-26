package buswars

class Transaction {
	
	float total
	Account account
	boolean complete
	
	static hasMany = [lineItems:LineItem]
	
    static constraints = {
    }
}
