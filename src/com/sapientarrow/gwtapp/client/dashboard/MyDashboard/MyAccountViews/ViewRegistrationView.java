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
package com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.ViewRegistrationPresenter.Display;
import com.sapientarrow.gwtapp.shared.entity.MyAccountPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

//This class is View for Registration, Where user can view/edit their Account Preferences 
//like view plan , view Reg etc.
public class ViewRegistrationView extends Composite implements Display{

	private static ViewRegistrationViewUiBinder uiBinder = GWT
			.create(ViewRegistrationViewUiBinder.class);

	interface ViewRegistrationViewUiBinder extends
			UiBinder<Widget, ViewRegistrationView> {
	}
	@UiField
	HTMLPanel panelName;
	@UiField
	HTMLPanel panelUserName;
	@UiField
	HTMLPanel panelEmail;
	@UiField
	HTMLPanel panelRegisteredTo;
	@UiField
	HTMLPanel panelLastEdited;
	@UiField
	Label lblName;
	@UiField
	Label lblUserName;
	@UiField
	Label lblEmail;
	@UiField
	Label lblRegisteredTo;
	@UiField
	Label lblLastEdited;

	public ViewRegistrationView() {
		initWidget(uiBinder.createAndBindUi(this));
		setHandlers();
		
	}

	private void setHandlers() {
		
	}

	@Override
	public void displaySelectedPanels(
			MyAccountPreferencesEntity myAccountPreferencesEntity) {
		if(myAccountPreferencesEntity!=null){
			panelName.setVisible(myAccountPreferencesEntity.isViewRegName());
			panelLastEdited.setVisible(myAccountPreferencesEntity.isViewRegLastEdited());
			panelUserName.setVisible(myAccountPreferencesEntity.isViewRegUserName());
			panelEmail.setVisible(myAccountPreferencesEntity.isViewRegEmail());
			panelRegisteredTo.setVisible(myAccountPreferencesEntity.isViewRegRegisteredTo());
		
		}
	}

	

	@Override
	public void updateUIFields(UserEntity userEntity) {
		lblName.setText(userEntity.getName());
		lblUserName.setText(userEntity.getUserName());
		lblEmail.setText(userEntity.getEmail());
		lblRegisteredTo.setText(userEntity.getMyAccountId().getRegisteredTo());
		lblLastEdited.setText(userEntity.getMyAccountId().getLastEdited()+"");
	}

	
	
	


}
