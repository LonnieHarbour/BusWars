package buswars

import grails.validation.Validateable

@Validateable
class BuyRequest {

	String sku
	boolean allOrNone
	float price
	int qty
	
}
