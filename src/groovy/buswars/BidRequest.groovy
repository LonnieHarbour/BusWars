package buswars

import org.grails.databinding.BindUsing
import grails.validation.Validateable

@Validateable
class BidRequest {

	String sku
	boolean allOrNone
	float price
	int qty
	
	@BindUsing({obj, source ->
		allOrNone = source['all-or-none'] 
	})
	
	static constraints = {
		sku blank: false
		price nullable: false, min:0.01f
		qty nullable: false, min:1
	}
}
