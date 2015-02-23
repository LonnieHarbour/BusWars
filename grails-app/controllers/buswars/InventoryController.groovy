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
	def buy(BidRequest bidRequest) {
		
		Product product = Product.where { sku == bidRequest.sku }.find()
		
		if (product) {
			render productService.purchase(product, bidRequest) as JSON 
		} else {
		    render status: 404
		}
	}		
	
	def history() {		
		render Bid.list() as JSON
	}
	
	def products() {
		render Product.list() as JSON
	}		
		
	
}
