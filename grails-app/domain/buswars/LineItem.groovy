package buswars

class LineItem {

	int qtyRequested
	int qtyFullfilled	
	float total
	boolean accepted	
	Product product
	Transaction transaction
	
	//static hasOne = [transaction:Transaction]
	
    static constraints = {
    }
}
