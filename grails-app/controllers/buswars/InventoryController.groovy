package buswars

import grails.converters.*
import grails.rest.RestfulController
import grails.transaction.Transactional

class InventoryController {
	
	ProductService productService
	
	def handleNotFound(NotFound nf) {
		render status: 404
	}
	
    def show(String sku) { 
		
		render productService.productBySku(sku) as JSON
	}
	
	@Transactional
	def buy() {
		
		TransactionRequest trans = new TransactionRequest(request.JSON)
			
		render productService.purchase(trans) as JSON 
		
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
		render Product.list()?.collect { new Catalog(product:it) } as JSON			
	}
	
}
