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
	def buy() {
		
		TransactionRequest trans = new TransactionRequest(request.JSON)
		
		Product product = Product.where { sku == trans.sku }.find()
		
		if (product) {
			render productService.purchase(product, bidRequest) as JSON 
		} else {
		    render status: 404
		}
	}		
	
	def history() {		
		render Transaction.list() as JSON
	}
	
	/*
	 * Inventory from API spec
	 */
	def products() {				
		render Product.where { inventory?.qtyOnHand > 0 }.list() as JSON
	}		
	
	/*
	 * Catalog from API spec.
	 */
	def catalog() {				
		render Product.list()?.collect { p ->  new Catalog(p) } as JSON			
	}
	
		
	
}
