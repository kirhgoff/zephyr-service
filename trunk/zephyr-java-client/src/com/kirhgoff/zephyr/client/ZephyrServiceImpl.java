package com.kirhgoff.zephyr.client;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import sun.net.www.protocol.http.HttpURLConnection;

public class ZephyrServiceImpl implements ZephyrService {

	private static final String DATA = "data";
	private static final String DATA_TYPE = "dataType";
	private static final String ID = "id";
	private static final String METHOD = "method";
	
	private final String serverURL;

	public ZephyrServiceImpl(String serverUrl) {
		this.serverURL = serverUrl;
	}

	@Override
	public String createDataSet() {
		try {
			return callServer(new MapBuilder ().
					put (METHOD, "createDataSet").
					instance ());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteData(String id, String dataType) {
		try {
			callServer(new MapBuilder ().
					put (METHOD, "deleteData").
					put(ID, id).
					put(DATA_TYPE, dataType).
					instance ());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getData(String id, String dataType) {
		try {
			return callServer(new MapBuilder ().
					put (METHOD, "getData").
					put(ID, id).
					put(DATA_TYPE, dataType).
					instance ());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void storeData(String id, String dataType, String data) {
		try {
			callServer(new MapBuilder ().
					put (METHOD, "storeData").
					put(ID, id).
					put(DATA_TYPE, dataType).
					put(DATA, data).
					instance ());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String callServer(Map<String, String> parameters) throws Exception {
		HttpURLConnection httpConnection = null;
		String result = null;
		
		try {
			System.out.println("request-------------------");
			httpConnection = new HttpURLConnection(new URL(null,serverURL), null);
			httpConnection.setRequestMethod("POST");
			for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				String value = parameters.get(name);
				System.out.println(name + " : " + value);
				httpConnection.addRequestProperty(name, value);
			}
			if (serverURL.indexOf("localhost") == -1) httpConnection.setChunkedStreamingMode(-1);
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			//httpConnection.setUseCaches(false);
			//httpConnection.setFixedLengthStreamingMode(-1);
			//httpConnection.setReadTimeout(20*1000);
			httpConnection.connect();

			// request is sent
			System.out.println("received response status=" + httpConnection.getResponseMessage() 
					+ ", length=" + httpConnection.getContentLength());
			result = httpConnection.getHeaderField("result");
		} finally {
			if (httpConnection != null) httpConnection.disconnect ();
		}
		return result;
	}

}
