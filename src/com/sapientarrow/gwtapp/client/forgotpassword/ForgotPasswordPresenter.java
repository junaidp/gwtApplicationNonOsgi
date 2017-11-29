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
package com.sapientarrow.gwtapp.client.forgotpassword;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

//This class manages the Functionality for forgotpassword , when user of this application forgets his/her password 
// system will send a password change Url to user's email with which user will create their new password.
public class ForgotPasswordPresenter implements Presenter 

{
	private final Display display;
	private final HelloServiceAsync rpcService;
	
	public interface Display 
	{
		Widget asWidget();
		Button getBtnSubmit();
		void setHtmlError(SafeHtml safeHtml);
		TextBox getTxtUserName();
		TextBox getTxtEmail();
	}  

	public ForgotPasswordPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.display = view;
		this.rpcService = rpcService;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		setHandlers();
	}

	private void bind() {


	}

	@Override
	public void setHandlers() {
		
		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				display.setHtmlError(SafeHtmlUtils.fromTrustedString(""));
				forgotPassword();
			}

			});
		
		
	}
	
	private void forgotPassword() {
		checkUserStatus(display.getTxtUserName().getText(), display.getTxtEmail().getText());
	}
	
	public void checkUserStatus(final String username, String email){
		display.getBtnSubmit().addStyleName("loading-pulse");
		rpcService.fetchUserStatus(username, email , new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				display.getBtnSubmit().removeStyleName("loading-pulse");
				Window.alert("Fail: fetch user status");
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals(ApplicationConstants.CONTACT_ADMIN)){
					display.setHtmlError(SafeHtmlUtils.fromTrustedString(result));
				}else{
					if(result.contains("does not match")){
//						if(username.equals(worngPasswordUser)){
//							worngPasswordCount = worngPasswordCount+1;
//						}else{
//							worngPasswordCount = 0;
//						}
//						
//						if(username.equals(worngPasswordUser) && worngPasswordCount>=2 ){
//							inactiveAccount(username);
//						}
//						else{
							display.setHtmlError(SafeHtmlUtils.fromTrustedString(result));
//						}
//						worngPasswordUser = username;
					}else{
						display.setHtmlError(SafeHtmlUtils.fromTrustedString(result));
					}
				}
				display.getBtnSubmit().removeStyleName("loading-pulse");
			}});
	}

	
	
}
