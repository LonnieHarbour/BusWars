package buswars

class ProductService {

    Bid purchase(Product product, BidRequest bidRequest) {		
		
		int qty = fullfill(product, bidRequest)
		
		if (qty) {
			product.qtyOnHand -= qty
			product.save()
		}
		
		new Bid(sku: product.sku, 
			name: product.name,
			qtyRequested: bidRequest.qty,
			qtyFullfilled: qty,
			price: bidRequest.price,
			total: (qty*bidRequest.price)?.round(2),
			accepted: qty>0).save()
		
    }
	
	private int fullfill(Product product, BidRequest bidRequest) {
		if (bidRequest?.allOrNone) {
			product.qtyOnHand >= bidRequest.qty ? bidRequest.qty : 0
		} else {
		    product.qtyOnHand - bidRequest.qty <= 0 ? product.qtyOnHand : bidRequest.qty
		}
	}
	
}
