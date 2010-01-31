import com.kirhgoff.zephyr.client.ZephyrClient;

public function createDataSet(serverURL:String): void {
	trace ("METHOD [createDataSet]" + " serverURL=" + serverURL);
	var client:ZephyrClient = new ZephyrClient (serverURL, function (error:String):void {trace ("createDataSet error=" + error);});
	client.createDataSet (
		function(result:String):void {
			dataSetID.text = result;
	    }
	);
}

public function storeData(serverURL:String, dataSetID:String, dataType:String, data:String): void {
	trace ("METHOD [storeData] id=" + dataSetID + ", type=" + dataType + ", data=" + data + ", serverURL=" + serverURL);
	var client:ZephyrClient = new ZephyrClient (serverURL, function (error:String):void {trace ("storeData error=" + error);});
	client.storeData (
		dataSetID, dataType, data,
		function(result:String):void {
			dataRead.text = result;
	    }
	);
}

public function getData(serverURL:String, dataSetID:String, dataType:String): void {
	trace ("METHOD [getData] id=" + dataSetID + ", type=" + dataType + ", serverURL=" + serverURL);
	var client:ZephyrClient = new ZephyrClient (serverURL, function (error:String):void {trace ("getData error=" + error);});
	client.getData (
		dataSetID, dataType,
		function(result:String):void {
			dataRead.text = result;
	    }
	);
}

public function deleteData(serverURL:String, dataSetID:String, dataType:String): void {
	trace ("METHOD [deleteData] id=" + dataSetID + ", type=" + dataType + ", serverURL=" + serverURL);
	var client:ZephyrClient = new ZephyrClient (serverURL, function (error:String):void {trace ("deleteData error=" + error);});
	client.deleteData (
		dataSetID, dataType,
		function(result:String):void {
			dataRead.text = result;
	    }
	);
}