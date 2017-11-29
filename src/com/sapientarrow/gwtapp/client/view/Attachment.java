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
package com.sapientarrow.gwtapp.client.view;

import java.util.ArrayList;

import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sapientarrow.gwtapp.client.HelloService;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.view.widgets.LoadingPopup;

import gwtupload.client.IUploadStatus.Status;

// This class used for the Attachments , it provides the attachment panel which can be used by any class.
public class Attachment extends VerticalPanel {
	VerticalPanel vpnlFileNames = new VerticalPanel();
	FormPanel form;

	public Attachment(){

		VerticalPanel panel = new VerticalPanel();
		MultiUploader defaultUploader = new MultiUploader();
		panel.add(defaultUploader);
		// Add a 'submit' button.
		HorizontalPanel hpnl = new HorizontalPanel();
		hpnl.add(panel);
		hpnl.setSpacing(10);
		add(hpnl);
		vpnlFileNames.clear();
		displayUploadedFiles();
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	  }

	  // Load the image in the document and in the case of success attach it to the viewer
	  private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
	    @SuppressWarnings("deprecation")
		public void onFinish(IUploader uploader) {
	      if (uploader.getStatus() == Status.SUCCESS) {

	        new PreloadedImage(uploader.fileUrl(), showImage);
	        UploadedInfo info = uploader.getServerInfo();
	        System.out.println("File name " + info.name);
	        System.out.println("File content-type " + info.ctype);
	        System.out.println("File size " + info.size);
	        System.out.println("Server message " + info.message);
	        vpnlFileNames.clear();
	        displayUploadedFiles();
	      }
	    }
	  };
	  
	  private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
		    public void onLoad(PreloadedImage image) {
		      image.setWidth("75px");
		      add(image);
		    }
		  };
	
	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}
	
	public void displayUploadedFiles(){
		HelloServiceAsync rpcService = GWT.create(HelloService.class);
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.readUploadedFiles(new AsyncCallback<ArrayList<String>>() {
			
			@Override
			public void onSuccess(ArrayList<String> result) {
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				Label lblFile = new Label("Already Uploaded files");
				vpnlFileNames.add(lblFile);
				for(int i=0; i< result.size(); i++){
					final Anchor lbl = new Anchor();
					lbl.setText(result.get(i));
					vpnlFileNames.add(lbl);
					lbl.addStyleName("hover");
					
					lbl.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							Window.open(GWT.getHostPageBaseURL() + "/fileuploads/"+lbl.getText(), "name", "enabled");

						}
					});
				}
				
				add(vpnlFileNames);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				Window.alert("reading uploaded files Failed:"+ caught.getLocalizedMessage());
			}
		});
	}
}
