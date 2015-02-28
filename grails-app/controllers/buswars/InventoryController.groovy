package buswars

import grails.converters.*
import grails.rest.RestfulController
import grails.transaction.Transactional

class InventoryController {

	ProductService productService

	def show(String sku) {
		render got(productService.productBySku(sku)) as JSON
	}

	@Transactional
	def buy(int accountid) {
		Transaction transaction = productService.purchase(accountid, new TransactionRequest(request.JSON))		
		response.status = 201
		//set location of created resource as per http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.30
		response.setHeader 'Location', "/accounts/$accountid/transactions/${transaction.id}"
		render transaction as JSON
	}

	def history() {
		render got(productService.history) as JSON
	}
	
	def accountTranHistory(int accountid, int tranid) {
		render got(productService.historyByAccountTranid(accountid, tranid)) as JSON
	}
	
	def tranHistory(int tranid) {
	    render got(productService.historyByTranid(tranid)) as JSON
    }

	def products() {
		render got(productService.products) as JSON
	}

	def catalog() {
		render got(productService.catalog) as JSON
	}

	def customers() {
		render got(productService.customers) as JSON
	}
	
	def handleNotFound(NotFound nf) {
		render status: 404
	}

	def handleProductMissing(ProductMissing pm) {
		render pm, status: 500
	}
	
	private def got(def object) {
		if (object) { object } else throw new NotFound()
	}
	
}
