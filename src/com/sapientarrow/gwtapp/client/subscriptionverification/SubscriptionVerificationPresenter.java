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
package com.sapientarrow.gwtapp.client.subscriptionverification;


import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.event.RegistrationEvent;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.rest.RestClient;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

//This class manages the Functionality for SubscriptionVerification, where system checks if the entered email is 
//registered in the system
public class SubscriptionVerificationPresenter implements Presenter 

{
	
	private final Display display;
	@SuppressWarnings("unused")
	private final HelloServiceAsync rpcService;
	private final HandlerManager eventBus;

	public interface Display 
	{
		Widget asWidget();
		TextBox getEmail();
		Button getSubmit();
		Anchor getRegisterAccount();
	}  

	public SubscriptionVerificationPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.display = view;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		setHandlers();
		RootPanel.get("loadingMessage").setVisible(false);
	
	}
	
	

	@Override
	public void setHandlers() {
		
		display.getRegisterAccount().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new RegistrationEvent());
				
			}});
		
		display.getSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
//				final LoadingPopup loadingPopup = new LoadingPopup();
//				loadingPopup.display();
				display.getSubmit().addStyleName("loading-pulse");
				
				Defaults.setServiceRoot(GWT.getHostPageBaseURL());
				RestClient client = GWT.create(RestClient.class);
				
				client.verifySubscription(display.getEmail().getText(), new MethodCallback<Boolean>() {
					
					@Override
					public void onSuccess(Method method, Boolean result) {
						display.getSubmit().removeStyleName("loading-pulse");
						if(result){
						History.newItem(ApplicationConstants.TOKEN_LOGIN);
						}else{
							Window.alert(ApplicationConstants.SUBSCRIPTION_NOT_VERIFIED);
						}
					}
					
					@Override
					public void onFailure(Method method, Throwable exception) {
						display.getSubmit().removeStyleName("loading-pulse");
						Window.alert("Fail verifySubscription: "+ exception.getLocalizedMessage());
					}
				});
				
//				display.getSubmit().addStyleName("loading-pulse");
//				rpcService.verifySubscription(display.getEmail().getText(), new AsyncCallback<Boolean>() {
//					
//					@Override
//					public void onSuccess(Boolean result) {
//						display.getSubmit().removeStyleName("loading-pulse");
//						if(result){
//						History.newItem(ApplicationConstants.TOKEN_LOGIN);
//						}else{
//							Window.alert(ApplicationConstants.SUBSCRIPTION_NOT_VERIFIED);
//						}
//					}
//					
//					@Override
//					public void onFailure(Throwable caught) {
//						display.getSubmit().removeStyleName("loading-pulse");
//						Window.alert("Fail verifySubscription: "+ caught.getLocalizedMessage());
//					}
//				});
			}
			});
		
	}
	
	
	
	
	
	
}
