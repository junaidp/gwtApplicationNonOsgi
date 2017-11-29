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
package com.sapientarrow.gwtapp.client.downloadjson;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.smartgwt.client.util.SC;

// This class manages the Functionality for DownloadJson, which is used for downloading the json of the selected bean
public class DownloadJsonPresenter implements Presenter 

{
	private final HelloServiceAsync rpcService;
	private final Display display;

	public interface Display 
	{
		Widget asWidget();
		ListBox getListBeans();
		Button getBtnDowmload();
	
	}  

	public DownloadJsonPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.rpcService = rpcService;
		this.display = view;
		
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		RootPanel.get("loadingMessage").setVisible(false);
		setHandlers();
		fetchBeans();
	}

	public void fetchBeans(){
		rpcService.fetchClassesOfAllPackages(new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onSuccess(ArrayList<String> classes) {

				display.getListBeans().clear();
				display.getListBeans().addItem(ApplicationConstants.SELECT_BEAN);
				for(int i=0; i< classes.size(); i++){
					int separatorIndex = classes.get(i).indexOf("::"); 
					display.getListBeans().addItem(classes.get(i).substring(0, separatorIndex));
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchClassesOfAllPackages failed "+ caught.getLocalizedMessage());

			}
		});
	}


	@Override
	public void setHandlers() {
		
		display.getBtnDowmload().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
		
				rpcService.downloadBeanFieldsJson(display.getListBeans().getValue(display.getListBeans().getSelectedIndex()), new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.warn(caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(String result) {
//						Window.open("/downloadedJson", "_blank", "", "enabled");
						Window.open(GWT.getHostPageBaseURL() + ApplicationConstants.DOWNLOADED_BEAN_FIELDS_JSON, "_blank", "enabled");
				    
					}
				});
			}});
		
	}

	
}


