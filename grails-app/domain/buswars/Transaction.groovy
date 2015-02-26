package buswars

class Transaction {
	
	float total
	Account account
	
	static hasMany = [lineItems:LineItem]
	
    static constraints = {
    }
}
