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
package com.sapientarrow.gwtapp.client.createpassword;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.widgets.DisplayAlert;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;
import com.sapientarrow.gwtapp.shared.utility.CreatePasswordFieldVerifier;

// This class manages the Functionality for CreatePassword which is use to create password for user, user can create/edit their password 
public class CreatePasswordPresenter implements Presenter 

{

	private final Display display;
	private final HelloServiceAsync rpcService;
	private UserEntity user;

	public interface Display 
	{
		Widget asWidget();
		PasswordTextBox getTxtNewPassword();
		PasswordTextBox getTxtConfirmPassword();
		Button getBtnSubmit();
		String getCreatePasswordToken();
		Label getLblError();
		Label getConfirmPasswordError();
		Label getNewPasswordError();

	}  

	public CreatePasswordPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) 
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
		fetchUser();
	}

	public void fetchUser(){

		rpcService.fetchNewUser(display.getCreatePasswordToken(), new AsyncCallback<UserEntity>(){

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(UserEntity user1) {
				user = user1;
				isLoggedInWithIn20Mins();
			}

		});
	}

	private void isLoggedInWithIn20Mins() {
		rpcService.isLoggedInWithin20Mins(user, new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Boolean result) {
				if(! result){
					Window.alert("Your activation link expired ,Please request a new link from your Administrator");
					History.newItem(ApplicationConstants.TOKEN_LOGIN);
				}
			}});
	}


	private void bind() {


	}

	@Override
	public void setHandlers() {

		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {

				CreatePasswordFieldVerifier fieldVerifier = new CreatePasswordFieldVerifier();
				if(fieldVerifier.registratonFieldsVerifid(display)){
					user.setPassword(display.getTxtNewPassword().getText());
					updatePassword();
				}

			}});

	}

	public void updatePassword(){
		display.getBtnSubmit().addStyleName("loading-pulse");
		rpcService.updatePassword(user, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				display.getBtnSubmit().removeStyleName("loading-pulse");
				Window.alert("Update password failed:"+ caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				display.getBtnSubmit().removeStyleName("loading-pulse");
				History.newItem(ApplicationConstants.TOKEN_LOGIN);
			}
		});

	}

}
