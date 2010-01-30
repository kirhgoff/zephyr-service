package com.kirhgoff.zephyr;
import java.io.IOException;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class ZephyrServiceServlet extends HttpServlet {

	private static final String RESULT = "result";
	
	private static final String GET_DATA = "getData";
	private static final String DELETE_DATA = "deleteData";
	private static final String STORE_DATA = "storeData";
	private static final String CREATE_DATA_SET = "createDataSet";

	private static final String DATA = "data";
	private static final String DATA_TYPE = "dataType";
	private static final String ID = "id";
	private static final String METHOD = "method";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = null;
		
		String method = request.getHeader(METHOD);
		if (method.equals(CREATE_DATA_SET)) {
			result = generateID ();
		} 
		else if (method.equals(STORE_DATA)) {
			String id = request.getHeader(ID);
			String dataType = request.getHeader(DATA_TYPE);
			String data= request.getHeader(DATA);
			storeData (id, dataType, data);
		}
		else if (method.equals(DELETE_DATA)) {
			String id = request.getHeader(ID);
			String dataType = request.getHeader(DATA_TYPE);
			deleteData (id, dataType);
		}
		else if (method.equals(GET_DATA)) {
			String id = request.getHeader(ID);
			String dataType = request.getHeader(DATA_TYPE);
			result = getData (id, dataType);
		}
		response.setHeader(RESULT, result);
		ServletOutputStream outputStream = response.getOutputStream();
		if (result != null) outputStream.write(result.getBytes());
		response.flushBuffer();

	}

	private String getData(String id, String dataType) {
		String key = getKey(id, dataType);
        PersistenceManager pm = PMF.get().getPersistenceManager();
        DataSet data = null;
        try {
            data = (DataSet) pm.getObjectById(DataSet.class, key);
        } finally {
            pm.close();
        }
        return data.getData();
	}

	private void storeData(String id, String dataType, String data) {
		String key = getKey(id, dataType);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		DataSet dataset = new DataSet(id, dataType, data);
		dataset.setKey(key);
		DataSet result = pm.makePersistent(dataset);
		System.out.println(result);
	}

	private void deleteData(String id, String dataType) {
		String key = getKey(id, dataType);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		DataSet object = pm.getObjectById(DataSet.class, key);
		pm.deletePersistent(object);
	}

	private String generateID() {
		return UUID.randomUUID().toString();
	}

	private String getKey(String id, String dataType) {
		//Key key = KeyFactory.createKey(DataSet.class.getSimpleName(), id + dataType);
		String key = id + dataType;
		return key;
	}

}
