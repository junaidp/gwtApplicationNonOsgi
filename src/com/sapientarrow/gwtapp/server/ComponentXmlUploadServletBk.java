/*******************************************************************************
 * Copyright (c) 2017  * Copyright (c) 2015 Sapient Arrow Technologies.
 * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Affero GNU Public License
 * which accompanies this distribution, and is available at
 * https://en.wikipedia.org/wiki/Affero_General_Public_License
 *  
 * Copyright:
 *    Sapient Arrow Technologies llc
 *  
 *  This file is part of the Business Suite software of Sapient Arrowpro.net.
 *  Copyright (C) 2012-2020 Sapient Arrowpro.net
 *
 *  The primary contact email is support@Sapient Arrowpro.net
 *
 *  Version: AGPL
 *
 *  Sapient Arrow Technologies, Sapient Arrow Information Systems, Sapient Arrow along with their domain names,  
 *  etc and the names Acuity, Ingenuity, Derivo, Colander etc are copyright of
 *  Sapient Arrow llc and usage of these without prior permission of the owner is strictly prohibited
 *   
 * The contents of this file may be used under the terms of
 *  the Affero GNU General Public License Version (the "AGPL"),
 *  A copy of the AGPL v2.1 can be obtained from https://en.wikipedia.org/wiki/Affero_General_Public_License
 *  
 *  AGPL, in essence, means that this software requires a commercial license for use in or as a commercial application
 *******************************************************************************/
package com.sapientarrow.gwtapp.server;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.shared.UConsts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

public class ComponentXmlUploadServletBk extends UploadAction {
	
	
	
	
	 // Create a factory for disk-based file items
//    FileItemFactory factory = new DiskFileItemFactory();
//
//    // Create a new file upload handler
//    ServletFileUpload upload = new ServletFileUpload(factory);
//
//         // Parse the request
//	try {
//		String name = null;
//		String videoTitle = "";
//		String videoDesc="";
//		String requestType;
//	    String tmpValue = "";
//		List<FileItem> items = upload.parseRequest(req);
//	    for (FileItem item : items) {
//	    	name = item.getFieldName();
//	    	// process only file upload - discard other form item types
//	        if (item.isFormField()){ 
//	        	 	//tmpValue = Streams.asString(stream);
//			    	tmpValue = item.getString();
//					if (log.isDebugEnabled()) {
//						log.debug("Form field " + name + " with value " + tmpValue + " detected.");
//					}
//			        if (name.equalsIgnoreCase("hdn_PicturesDir")){
//			        	videoDir = tmpValue;
//			        }else if (name.equalsIgnoreCase("videoTitleElement")){
//			        	videoTitle = tmpValue;
//			        }else if (name.equalsIgnoreCase("videoDescElement")){
//			        	videoDesc = tmpValue;
//			        }else if (name.equalsIgnoreCase("requestType")){
//			        	requestType = tmpValue; //teamPage, PlayerPage
//			        }
//	         }else {
//	        	 inputFileItem = item;
//		         fileName = item.getName();
//		         // get only the file name not whole path
//                 if (fileName != null) {
//                     fileName = FilenameUtils.getName(fileName);
//                 }
//		     }
//	    }
//
//		
////		uploadVideoFile(inputFileItem, (System.getProperty("user.dir") + videoDir).toLowerCase(), fileName, req);
//	    PrintWriter responseHtml = resp.getWriter();
//	    if(videoDesc == null){
//	    	videoDesc = "";
//	    }
//	    if(videoTitle.trim().length() < 1){
//	    	responseHtml.write("Video title not provided, please try again");
//	    }else if(inputFileItem.getName().length() < 1) {
//	    	responseHtml.write("Please upload a valid video file");
//	    }else{
//	    	String youtubeUrl = uploadVideoFile(inputFileItem, videoTitle, videoDesc );
//	    
//	    	responseHtml.write(youtubeUrl);
//	    }
//	}catch(Exception ex){
//		log.debug("Exception in UploadServlet.getFormFields : " + ex.getCause() + ex.getMessage());
//		throw new Exception(PersistenceExceptionConstants.UPLOADPICTURE_EXCEPTION);
//	}
//}	


	
	
	
	
	
	
	
	
	
	
	
	
	

	  private static final long serialVersionUID = 1L;
	  
	  Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	  /**
	   * Maintain a list with received files and their content types. 
	   */
	  Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

	  /**
	   * Override executeAction to save the received files in a custom place
	   * and delete this items from session.  
	   */
	  @Override
	  public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	    String response = "";
	    FileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    
	    try {
	    List<FileItem> items = upload.parseRequest(request);
//	    for (FileItem item : items) {
//	    	name = item.getFieldName();
	    
	    for (FileItem item : items) {
//	    for (FileItem item : sessionFiles) {
	      if (false == item.isFormField()) {
	    	 String name = item.getFieldName();
	    	 String tmpValue = item.getString();
	         	String fileName = item.getName();
	         	String mypath = "com.helloworld.client.view.widgets";
	            String root = getServletContext().getRealPath("/");
	            if (name.equalsIgnoreCase("beanName")){
	            	System.out.println(tmpValue);
	            }
	            
//              File path = new File(root + "/fileuploads1");
	            File path = createFilePath(mypath);
              if (!path.exists()) {
                  boolean status = path.mkdirs();
              }

              File file = new File(path + "/" + fileName);
              item.write(file);

	          receivedFiles.put(item.getFieldName(), file);
	          receivedContentTypes.put(item.getFieldName(), item.getContentType());
	          response += "File saved as " + file.getAbsolutePath();

	        
	      }else{
	    	  String name = item.getFieldName();
	    	    String value = item.getString();
	      }
	    }
	    } catch (Exception e) {
	          throw new UploadActionException(e);
	        }
	    /// Remove files from session because we have a copy of them
	    removeSessionFileItems(request);
	    
	    /// Send your customized message to the client.
	    return response;
	  }

	private File createFilePath(String myPath) {
		
		String dir = System.getProperty("user.dir");
		int slashIndex = dir.lastIndexOf("\\");
		dir = dir.substring(0, slashIndex+1);
		String packageDir = myPath.replace(".","\\");
//		dir =dir +"src\\"+ packageDir;
		dir =dir +ApplicationConstants.TOMCAT_PATH+ packageDir;
		
		File myPackage    = new File(dir);
		return myPackage;
	}
	  
	  /**
	   * Get the content of an uploaded file.
	   */
	  @Override
	  public void getUploadedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String fieldName = request.getParameter(UConsts.PARAM_SHOW);
	    File f = receivedFiles.get(fieldName);
	    if (f != null) {
	      response.setContentType(receivedContentTypes.get(fieldName));
	      FileInputStream is = new FileInputStream(f);
	      copyFromInputStreamToOutputStream(is, response.getOutputStream());
	    } else {
	      renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
	   }
	  }
	  
	  /**
	   * Remove a file when the user sends a delete request.
	   */
	  @Override
	  public void removeItem(HttpServletRequest request, String fieldName)  throws UploadActionException {
	    File file = receivedFiles.get(fieldName);
	    receivedFiles.remove(fieldName);
	    receivedContentTypes.remove(fieldName);
	    if (file != null) {
	      file.delete();
	    }
	  }
	}