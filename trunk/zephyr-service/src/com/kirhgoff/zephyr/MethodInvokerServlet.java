package org.kirhgoff.http.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kirhgoff.conf.PropertyLoader;
import org.kirhgoff.http.client.Command;
import org.kirhgoff.http.client.json.JSONUtils;
import org.kirhgoff.http.client.model.FilesList;

public class MethodInvokerServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(MethodInvokerServlet.class);
	private static String usernameFilesRoot;
	static {
		usernameFilesRoot = (String) System.getProperties().get(PropertyLoader.SERVER_USERNAME_FILES_ROOT);
		log.info("Using following path to get media files: " + usernameFilesRoot);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		
		log.info ("=======================================================");
		String methodName = request.getHeader("methodName");
		log.info ("Received request, methodName=" + methodName);
		log.debug("REQUEST: \n" + request.toString());
		String result = null;
		try {
		if (methodName.equals(Command.GET_LIST_OF_FILES)) {
			result = getListOfFiles (request);
		}//All other methods go here
		else {
			//TODO error processing
			throw new Exception ("Cannot find such method:" + methodName);
		}
		}
		catch (Exception e) {
			result = "ERROR: " + e.getMessage();
		}
		response.setHeader("result", result);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(result.getBytes());
		response.flushBuffer();
		log.info("Sent response: " + response);
	  }

	private String getListOfFiles(HttpServletRequest request) throws Exception {
		String username = request.getHeader(Command.USER_ID);
		if (username == null) throw new Exception ("Missing username.");
		if (usernameFilesRoot == null) throw new Exception ("Incorrect server startup, cant find file root, properties were not loaded");

		FilesList list = new FilesList ();
		list.setNames(getFilesForUser(username));
		
		return JSONUtils.fromJavaToJSON(list).toString();
	}

	//Business logic
	
	private List<String> getFilesForUser(String username) {
		File usernameRoot = new File (usernameFilesRoot, username);
		log.info("User root=" + usernameRoot.getAbsolutePath());
		String[] files = usernameRoot.list();
		
		List<String> filesToSend = new ArrayList<String> ();
		for (String fileName: files) {
			File file = new File (usernameRoot, fileName);
			if (file.exists() && !file.isDirectory()) {
				log.info("Adding file:" + fileName);				
				filesToSend.add (fileName);
			}
		}
		return filesToSend;
	}
	
	
}
