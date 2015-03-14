package buswars

import org.grails.databinding.BindUsing
import org.springframework.context.annotation.PropertySource

import grails.validation.Validateable

@Validateable
class TransactionRequest {

	TransactionRequest(def src) {
		allOrNone = src.'all-or-none'
		price = src.total
		src.lineItems?.each { line ->
			lines.add new TransactionLine(
					sku: line.sku,
					qty: line.qty,
					name: line.name,
					price: line.price,
					subtotal: line.subtotal)
		}
	}

	boolean allOrNone
	float price
	def lines = []

	static constraints = {
	}
}
