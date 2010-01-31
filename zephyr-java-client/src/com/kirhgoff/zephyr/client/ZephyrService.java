package com.kirhgoff.zephyr.client;

public interface ZephyrService {

	/**
	 * Initializes data set to start writing to it 
	 */
	String createDataSet();

	/**
	 * Stores data under named type
	 * in case of data exists, data will be rewritten
	 */
	void storeData(String id, String dataType, String data);

	/**
	 * retrieve named data from the data set 
	 */
	String getData(String id, String dataType);

	/**
	 * Delete data from dataset
	 */
	void deleteData(String id, String dataType);

}
