package com.kirhgoff.zephyr;
import java.io.IOException;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		outputStream.write(result.getBytes());
		response.flushBuffer();

	}

	private String getData(String id, String dataType) {
		KeyFactory.crea
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(greeting);
        } finally {
            pm.close();
        }

	}

	private void storeData(String id, String dataType, String data2) {
		// TODO Auto-generated method stub
		
	}

	private void deleteData(String id, String dataType) {
		// TODO Auto-generated method stub
		
	}

	private void storeData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	private String generateID() {
		return UUID.randomUUID().toString();
	}
}
