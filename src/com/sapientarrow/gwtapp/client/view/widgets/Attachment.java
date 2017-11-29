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
package com.sapientarrow.gwtapp.client.view.widgets;

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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sapientarrow.gwtapp.client.HelloService;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;

import gwtupload.client.IUploadStatus.Status;

// This class used as a widget for Attachment , which user can used to upload attachments 
public class Attachment extends VerticalPanel {
	VerticalPanel vpnlFileNames = new VerticalPanel();
	FormPanel form;

	public Attachment(){

		VerticalPanel panel = new VerticalPanel();
		MultiUploader defaultUploader = new MultiUploader();
		panel.add(defaultUploader);
		HorizontalPanel hpnl = new HorizontalPanel();
		hpnl.add(panel);
		form = new FormPanel();
		hpnl.setSpacing(10);
		add(form);
		form.setWidget(hpnl);
		form.setAction("uploadServlet");
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
	
//	public void displayUploadedFiles(){
//		HelloServiceAsync rpcService = GWT.create(HelloService.class);
//		final LoadingPopup loadingPopup = new LoadingPopup();
//		loadingPopup.display();
//		rpcService.readUploadedFiles(new AsyncCallback<ArrayList<String>>() {
//			
//			@Override
//			public void onSuccess(ArrayList<String> result) {
//				if(loadingPopup!=null){
//					loadingPopup.remove();
//				}
//				Label lblFile = new Label("Already Uploaded files");
//				vpnlFileNames.add(lblFile);
//				for(int i=0; i< result.size(); i++){
//					final Anchor lbl = new Anchor();
//					lbl.setText(result.get(i));
//					vpnlFileNames.add(lbl);
//					lbl.addStyleName("hover");
//					
//					lbl.addClickHandler(new ClickHandler() {
//						
//						@Override
//						public void onClick(ClickEvent event) {
//							Window.open(GWT.getHostPageBaseURL() + "fileuploads/"+lbl.getText(), "_blank", null);
//
//						}
//					});
//				}
//				
//				add(vpnlFileNames);
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				if(loadingPopup!=null){
//					loadingPopup.remove();
//				}
//				Window.alert("reading uploaded files Failed:"+ caught.getLocalizedMessage());
//			}
//		});
//	}
	
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
	
				HorizontalPanel contentRow = new HorizontalPanel();
				
				vpnlFileNames.setWidth("100%");
				vpnlFileNames.setSpacing(50);
				setWidth("100%");
				vpnlFileNames.clear();
				vpnlFileNames.add(lblFile);
				vpnlFileNames.add(new HTML("&nbsp"));
				vpnlFileNames.add(contentRow);
				contentRow.setWidth("100%");
				contentRow.setSpacing(10);
				contentRow.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
				ArrayList<Image> images = new ArrayList<Image>();
				ArrayList<Label> files = new ArrayList<Label>();

				
				for (int x = 0; x < result.size(); x++) {
					if(isTypeImage(result.get(x))){
						final Image img = new Image();
						img.setSize("75px", "75px");
						img.setUrl("fileuploads/"+result.get(x));
						img.addStyleName("hover");
						images.add(img);
					
					}
					else{
						final Label lbl = new Label();
						lbl.setText(result.get(x));
						lbl.addStyleName("hover");
						files.add(lbl);
						
					}
				
				
				
			}
				for(int i=0; i< images.size(); i++){
					contentRow = addImageThumbNail(images.get(i), contentRow);
				}
				for(int i=0; i< files.size(); i++){
					if(i==0){
					contentRow = new HorizontalPanel();
					contentRow.setWidth("100%");
					contentRow.setSpacing(10);
					contentRow.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
					vpnlFileNames.add(new HTML("&nbsp"));
					vpnlFileNames.add(contentRow);
					}
					contentRow = addFileName(files.get(i), contentRow);
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
	
	private HorizontalPanel addImageThumbNail(final Image image,
			HorizontalPanel contentRow) {
		
		final String url = image.getUrl();
		
			contentRow.add(image);
			
			if (contentRow.getWidgetCount() > 4) {// displaying not more than 3 images in a row.
				contentRow = new HorizontalPanel();
				contentRow.setWidth("100%");
				contentRow.setSpacing(10);
				contentRow.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
				vpnlFileNames.add(new HTML("&nbsp"));
				vpnlFileNames.add(contentRow);
			}
			
			image.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
//					System.out.println(img.getUrl());
					Window.open(image.getUrl(), "_blank", null);
				
					

				}
			});
		return contentRow;
	}
	
	private HorizontalPanel addFileName(final Label file,
			HorizontalPanel contentRow) {
		
		
		
			contentRow.add(file);
		
			if (contentRow.getWidgetCount() > 4) {// displaying not more than 3 images in a row.
				contentRow = new HorizontalPanel();
				contentRow.setWidth("100%");
				contentRow.setSpacing(10);
				contentRow.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
				vpnlFileNames.add(new HTML("&nbsp"));
				vpnlFileNames.add(contentRow);
			}
			
			file.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					Window.open(GWT.getHostPageBaseURL() + "fileuploads/"+file.getText(), "_blank", null);
				
				}
			});
		return contentRow;
	}
	
	
	
	private boolean isTypeImage(String lowerFileName){
		if (lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".gif") || lowerFileName.endsWith(".png") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".tiff") || lowerFileName.endsWith(".bmp") || lowerFileName.endsWith(".svg")) {
			return true;
		}else{
			return false;
		}
	}
	

}
