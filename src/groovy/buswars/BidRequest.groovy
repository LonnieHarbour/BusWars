package buswars

import org.grails.databinding.BindUsing
import org.springframework.context.annotation.PropertySource

import grails.validation.Validateable

@Validateable
class BidRequest {

	BidRequest(def src) {
		sku = src.sku
		allOrNone = src.'all-or-none'
		price = src.price
		qty = src.qty
	}
	
	String sku
	boolean allOrNone
	float price
	int qty
	
	static constraints = {
		sku blank: false
		price nullable: false, min:0.01f
		qty nullable: false, min:1
	}
}
