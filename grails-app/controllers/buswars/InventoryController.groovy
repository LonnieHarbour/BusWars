package buswars

import grails.converters.*
import grails.rest.RestfulController
import grails.transaction.Transactional

class InventoryController {
	
	ProductService productService
	
    def show(String id) { 
		
		Product product = Product.where { sku == id }.find()
		
		if (product) {
			render product as JSON
		} else {
		    render status: 404
		}
	}
	
	@Transactional
	def buy(BuyRequest buyRequest) {
		
		Product product = Product.where { sku == buyRequest.sku }.find()
		
		if (product) {
			render productService.purchase(product, buyRequest) as JSON 
		} else {
		    render status: 404
		}
	}		
	
	def history() {		
		render Purchased.list() as JSON
	}
	
	def products() {
		render Product.list() as JSON
	}		
		
	
}
