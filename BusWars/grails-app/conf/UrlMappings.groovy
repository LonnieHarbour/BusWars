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
		
		"/inventory/$id"(controller:"inventory", action:"show", method:"GET")
		
		"/inventory/$id"(controller:"inventory", action:"buy", method:"POST")
		
		"/inventory"(controller:"inventory", action:"buy", method:"POST")
		
		"/purchases"(controller:"inventory", action:"history", method:"GET")
		
		
	}
}
