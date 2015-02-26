package buswars

import org.grails.databinding.BindUsing
import org.springframework.context.annotation.PropertySource

import grails.validation.Validateable

@Validateable
class TransactionRequest {
	
	TransactionRequest(def src) {
		sku = src.sku
		allOrNone = src.'all-or-none'
		price = src.price
		qty = src.qty
		src.lineItems?.each { line ->
			lines.add new TransactionLine(
				sku: line.sku,
	            qty: line.qty, 
	            name: line.name,
	            price: line.price,
	            subtotal: subtotal)
		}
	}
	
	boolean allOrNone
	float price
	def lines = []
	
	static constraints = {
		sku blank: false
		price nullable: false, min:0.01f
		qty nullable: false, min:1
	}
}
