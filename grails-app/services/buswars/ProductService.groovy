package buswars

class ProductService {

    Purchased purchase(Product product, BuyRequest buyRequest) {
		if (product?.qtyOnHand >= buyRequest?.qty) {
			
			product.qtyOnHand = product.qtyOnHand - buyRequest.qty
			product.save()
						
			new Purchased(sku: product.sku,
				name: product.name,
				qtyPurchased: buyRequest.qty,
				price: product.price,
				total: (product.price*buyRequest.qty)?.round(2)
				).save()				
		}

    }
}
