package buswars

import grails.converters.*
import grails.rest.RestfulController
import grails.transaction.Transactional

class InventoryController {

	ProductService productService

	def show(String sku) {
		got productService.productBySku(sku)
	}

	@Transactional
	def buy(int accountid) {
		Transaction transaction = productService.purchase(accountid, new TransactionRequest(request.JSON))
		response.status = 201
		//set location of created resource as per http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.30
		response.setHeader 'Location', "/accounts/$accountid/transactions/${transaction.id}"
		got transaction
	}

	def history() {
		got productService.history
	}

	def accountTranHistory(int accountid, int tranid) {
		got productService.historyByAccountTranid(accountid, tranid)
	}

	def tranHistory(int tranid) {
		got productService.historyByTranid(tranid)
	}

	def products() {
		got productService.products
	}

	def catalog() {
		got productService.catalog
	}

	def customers() {
		got productService.customers 
	}

	def handleNotFound(NotFound nf) {
		render status: 404
	}

	def handleProductMissing(ProductMissing pm) {
		render pm, status: 500
	}

	private def got(def object) {
		if (object) {
			render object as JSON
		} else throw new NotFound()
	}
}
