import buswars.DatabaseInit
import buswars.JSONMarshallerInit

class BootStrap {
	
    def init = { servletContext ->		
		DatabaseInit.init()		
		JSONMarshallerInit.init()		
    }
	
    def destroy = {
    }
}
