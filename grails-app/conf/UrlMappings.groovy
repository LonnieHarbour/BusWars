class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
		"404"(view:'/404')
		
		//show it all minus inventories
		"/catalog"(controller:"inventory", action:"catalog", method:"GET")
		
		//show products with existing inventories
		"/inventory"(controller:"inventory", action:"products", method:"GET")
		
		//show a product regardless of if it has inventory or not
		"/inventory/$id"(controller:"inventory", action:"show", method:"GET")
		
		//buy
		"/accounts/$accountNumber/transactions"(controller:"inventory", action:"buy", method:"POST")
		
		"/purchases"(controller:"inventory", action:"history", method:"GET")
		
		
		
		
	}
}
