package buswars

import grails.transaction.Transactional

@Transactional
class ProductService {

    Purchased purchase(Product product, BuyRequest buyRequest) {
		if (product?.qtyOnHand >= buyRequest?.qty) {
			
			product.qtyOnHand = product.qtyOnHand - buyRequest.qty
			product.save()
						
			return new Purchased(sku: product.sku,
				name: product.name,
				qtyPurchased: buyRequest.qty,
				price: product.price).save()				
		}

    }
}
