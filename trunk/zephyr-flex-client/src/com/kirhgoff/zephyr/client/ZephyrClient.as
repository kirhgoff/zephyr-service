package com.kirhgoff.zephyr.client {
  
  import flash.errors.*;
  import flash.events.*;
  import flash.net.*;
  
  public class ZephyrClient {
    private var serverURL: String;
    private var errorHandler: Function;
    
    public function ZephyrClient(serverURL:String, errorHandler:Function) {
    	this.serverURL = serverURL;
    	this.errorHandler = errorHandler;
    }
    
	public function createDataSet(onComplete:Function):void {
		callServer ({method: "createDataSet"}, onComplete);
	}
   
    public function storeData(id:String, dataType:String, data:String, onComplete:Function):void {
    	callServer ({method: "storeData", id:id, dataType:dataType, data:data}, onComplete);
    }

    public function getData(id:String, dataType:String, onComplete:Function):void {
    	callServer ({method: "getData", id:id, dataType:dataType}, onComplete);
    }
    
    public function deleteData(id:String, dataType:String, onComplete:Function):void {
    	callServer ({method: "deleteData", id:id, dataType:dataType}, onComplete);
    }

    public function callServer(requestParams:Object, onComplete:Function):void {
		var headers:Array = new Array ();
		for (var key: String in requestParams) {
			headers.push (new URLRequestHeader (key,requestParams[key]));
		}
		
		var request:URLRequest = new URLRequest();
      	request.url = serverURL;
      	request.method = URLRequestMethod.POST;
  	  	request.data = " ";//something needs to be sent 
      	request.requestHeaders = headers;
        
		var loader:URLLoader = new URLLoader();
		loader.dataFormat = URLLoaderDataFormat.TEXT;

    	loader.addEventListener(IOErrorEvent.IO_ERROR, function():void {
      		errorHandler("Connection error occured");
    	});
        loader.addEventListener(SecurityErrorEvent.SECURITY_ERROR, function():void {
          errorHandler("Security error occured");
        });

      	loader.addEventListener(Event.COMPLETE, function(e:Event):void{
        	var loader:URLLoader = URLLoader(e.target);
			trace(loader.data);
			//could get nothing
          	onComplete(loader.data);
		});
      
		try {
        	loader.load(request);
      	}
      	catch (error:Error) {
        	errorHandler(error);
      	}
    }
    
  }
}
