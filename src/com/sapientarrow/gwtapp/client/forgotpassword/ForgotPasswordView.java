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

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.forgotpassword.ForgotPasswordPresenter.Display;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

//This class manages the VIEW for forgotpassword , when user of this application forgets his/her password 
//system will send a password change Url to user's email with which user will create their new password.
public class ForgotPasswordView extends Composite implements Display{

	private static ForgotPasswordViewUiBinder uiBinder = GWT
			.create(ForgotPasswordViewUiBinder.class);

	interface ForgotPasswordViewUiBinder extends
			UiBinder<Widget, ForgotPasswordView> {
	}
	
	
	@UiField
	Button btnSubmit;
	@UiField
	HTML htmlError;
	@UiField
	TextBox txtUserName;
	@UiField
	TextBox txtEmail;
	
	

	public ForgotPasswordView() {
		initWidget(uiBinder.createAndBindUi(this));
		txtUserName.getElement().setPropertyString("placeholder", ApplicationConstants.ENTER_USERNAME);
		txtEmail.getElement().setPropertyString("placeholder", ApplicationConstants.ENTER_REGISTERED_EMAIL);
	}

	
	public Button getBtnSubmit() {
		return btnSubmit;
	}



	public TextBox getTxtUserName() {
		return txtUserName;
	}

	public TextBox getTxtEmail() {
		return txtEmail;
	}

	public HTML getHtmlError() {
		return htmlError;
	}

	public void setHtmlError(SafeHtml safeHtml) {
		this.htmlError.setHTML(safeHtml);
	}
//
//	@Override
//	public HTML setHtmlError(SafeHtml safeHtml) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
