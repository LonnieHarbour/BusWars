package buswars

import org.grails.databinding.BindUsing
import org.springframework.context.annotation.PropertySource

import grails.validation.Validateable

@Validateable
class BidRequest {

	String sku
	
	@BindUsing({obj, source -> allOrNone = source['all-or-none']})
	boolean allOrNone
	
	float price
	int qty
	
	static constraints = {
		sku blank: false
		price nullable: false, min:0.01f
		qty nullable: false, min:1
	}
}
