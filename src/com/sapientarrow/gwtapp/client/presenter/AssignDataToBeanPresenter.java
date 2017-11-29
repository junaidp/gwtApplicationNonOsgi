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
package com.sapientarrow.gwtapp.client.presenter;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.TreeMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.widgets.DisplayAlert;
import com.sapientarrow.gwtapp.client.view.widgets.ErrorPanelWidget;
import com.sapientarrow.gwtapp.client.view.widgets.UploadedComponents.UploadedClass;
import com.smartgwt.client.util.SC;

// This class manages the Functionality for AssignDataToBean which is used  to Assign data to the bean we created before.
// user uploads the ui.xml/java class to get the widget , enters the value to edit the bean 
// This feature not working completely as we couldnt get DynamicBeans to work
public class AssignDataToBeanPresenter implements Presenter 

{
	private final HelloServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private String selectedBeanName;
//	private Class selectedBean = null;
	private TreeMap beanPropertiesMap = new TreeMap();
	private String beanType;

	public interface Display 
	{
		Widget asWidget();
		HTMLPanel getBeanUiContainer();
		Button getBtnSubmit();
		ErrorPanelWidget getErrorPanelWidget();

	}  

	public AssignDataToBeanPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view, String beanName, String beanType) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.selectedBeanName = beanName;
		this.beanType = beanType;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {

		setHandlers();
		uploadLayout();
	}

	private void uploadLayout() {

	}

	private void editBeanOnPropertyChange(String selectedBeanName,
			String propertyName, Object object) {

		beanPropertiesMap.put(propertyName, object);
	}


	@Override
	public void setHandlers() {

		UploadedClass uploadedClass = new UploadedClass();
		display.getBeanUiContainer().add(uploadedClass);


		uploadedClass.pcs.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				editBeanOnPropertyChange(selectedBeanName, evt.getPropertyName(), evt.getNewValue());
			}


		});

		display.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.getBtnSubmit().addStyleName("loading-pulse");
				if(beanType.equals(ApplicationConstants.DYNAMIC_BEAN)){
					editDynamicBean();
				}else {
					editBean();
				}
			}
		});

	}

	private void editBean() {
		rpcService.editBeanOnPropertyChange(selectedBeanName, beanPropertiesMap, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable exceptionJson) {
				display.getBtnSubmit().removeStyleName("loading-pulse");
				int ind = exceptionJson.getMessage().indexOf("{");
				if(ind!=-1){
					JSONValue jsonValue = JSONParser.parse(exceptionJson.getMessage().substring(ind));
					JSONObject object = jsonValue.isObject();
					Set<String> keys =  object.keySet();



					FlexTable flex = new FlexTable();
					Object[] keyArray = keys.toArray();
					//				for (String s : keys) {
					for(int i=0; i< keyArray.length; i++){
						Label lbl = new Label((String) keyArray[i]);
						JSONValue s = object.get(keyArray[i].toString());
						s.toString();

						flex.setWidget(i, 0, lbl);
						flex.setWidget(i, 1, new Label(s.toString()));
					}
					//				new PopupsView(flex);
					display.getErrorPanelWidget().add(flex);
					display.getErrorPanelWidget().setVisible(true);
				}
				new DisplayAlert("Bean saved");
				display.getBtnSubmit().removeStyleName("loading-pulse");
			}

			@Override
			public void onSuccess(String result) {
				display.getErrorPanelWidget().setVisible(false);
				fetchBeanObject(selectedBeanName);
			}
		});
	}


	private void editDynamicBean() {
		rpcService.editDynamicBeanOnPropertyChange(selectedBeanName, beanPropertiesMap, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable exceptionJson) {
				display.getBtnSubmit().removeStyleName("loading-pulse");
				int ind = exceptionJson.getMessage().indexOf("{");
				if(ind!=-1){
					JSONValue jsonValue = JSONParser.parse(exceptionJson.getMessage().substring(ind));
					JSONObject object = jsonValue.isObject();
					Set<String> keys =  object.keySet();



					FlexTable flex = new FlexTable();
					Object[] keyArray = keys.toArray();
					//				for (String s : keys) {
					for(int i=0; i< keyArray.length; i++){
						Label lbl = new Label((String) keyArray[i]);
						JSONValue s = object.get(keyArray[i].toString());
						s.toString();

						flex.setWidget(i, 0, lbl);
						flex.setWidget(i, 1, new Label(s.toString()));
					}
					//				new PopupsView(flex);
					display.getErrorPanelWidget().add(flex);
					display.getErrorPanelWidget().setVisible(true);
				}
				new DisplayAlert("Bean saved");
				display.getBtnSubmit().removeStyleName("loading-pulse");
			}

			@Override
			public void onSuccess(String result) {
				display.getErrorPanelWidget().setVisible(false);
				fetchBeanObject(selectedBeanName);
			}
		});
	}

	private void fetchBeanObject(String selectedBeanName) {

		rpcService.fetchBeanObject(selectedBeanName, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				display.getBtnSubmit().removeStyleName("loading-pulse");
				SC.say("Bean Saved");
//				Window.alert(result);
			}

			@Override
			public void onFailure(Throwable exceptionJson) {
				SC.warn("Error in fetchBeanObject: " + exceptionJson.getLocalizedMessage());
				display.getBtnSubmit().removeStyleName("loading-pulse");
			}
		});

	}

	public String getBeanType() {
		return beanType;
	}
}


