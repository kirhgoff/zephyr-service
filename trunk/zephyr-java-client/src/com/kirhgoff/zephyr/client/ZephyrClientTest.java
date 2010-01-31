package com.kirhgoff.zephyr.client;

import junit.framework.Assert;

import org.junit.Test;

public class ZephyrClientTest {
	private static final String LOCAL = "http://localhost:8888/zephyrService";
	private static final String DEV = "http://localhost:8080/zephyrService";
	private static final String PROD = "http://zephyr-service.appspot.com/zephyrService";	

	@Test
	public void testService() throws Exception {
		String dataType = "testData";
		String data = "This is the data to save";
		String updatedData = "This is updated data";

		//ZephyrService service = new ZephyrServiceImpl(DEV);
		ZephyrService service = new ZephyrServiceImpl(LOCAL);
		//ZephyrService service = new ZephyrServiceImpl(PROD);
		
		//create data set record
		String id = service.createDataSet ();
		Assert.assertNotNull(id);
		System.out.println("received id=" + id);
		
		//store some data to dataset
		service.storeData (id, dataType, data);
		String result = service.getData (id, dataType);
		Assert.assertEquals(data, result);
	
		//amend data
		service.storeData (id, dataType, updatedData);
		result = service.getData (id, dataType);	
		Assert.assertEquals(updatedData, result);
		
		//wipe data
		service.deleteData (id, dataType);
		result = service.getData (id, dataType);
		Assert.assertEquals (null, result);
	}
	
	public static void main(String[] args) throws Exception {
		new ZephyrClientTest().testService();
	}
}
