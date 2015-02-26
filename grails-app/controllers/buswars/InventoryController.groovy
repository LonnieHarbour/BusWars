package buswars

import grails.converters.*
import grails.rest.RestfulController
import grails.transaction.Transactional

class InventoryController {
	
	ProductService productService
	
	def handleNotFound(NotFound nf) {
		render status: 404
	}
	
	def handleProductMissing(ProductMissing pm) {
		render pm, status: 500
	}
	
    def show(String sku) { 
		
		render productService.productBySku(sku) as JSON
	}
	
	@Transactional
	def buy(int account) {
		
		TransactionRequest order = new TransactionRequest(request.JSON)
		
		Transaction transaction = productService.purchase(account, order)
		
		response.status = 201
			
		render transaction as JSON 
		
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
	
	def customers() {
		render Account.list() as JSON
	}
	
}
