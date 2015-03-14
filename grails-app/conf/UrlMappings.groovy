class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{ constraints { // apply constraints here
			} }

		"/"(view:"/index")
		"500"(view:'/error')
		"404"(view:'/404')

		//show it all minus inventories
		"/catalog"(controller:"inventory", action:"catalog", method:"GET")

		//show products with existing inventories
		"/inventory"(controller:"inventory", action:"products", method:"GET")

		//show a product regardless of if it has inventory or not
		"/inventory/$sku"(controller:"inventory", action:"show", method:"GET")

		//buy
		"/accounts/$accountid/transactions"(controller:"inventory", action:"buy", method:"POST")

		//get all transactions
		"/transactions"(controller:"inventory", action:"history", method:"GET")

		//get a transaction for an account number
		"/accounts/$accountid/transactions/$tranid"(controller:"inventory", action:"accountTranHistory", method:"GET")

		//get a specific transaction
		"/transactions/$tranid"(controller:"inventory", action:"tranHistory", method:"GET")

		//misc
		"/accounts"(controller:"inventory", action:"customers", method:"GET")


	}
}
