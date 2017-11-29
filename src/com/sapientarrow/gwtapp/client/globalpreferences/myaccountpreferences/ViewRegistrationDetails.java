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
package com.sapientarrow.gwtapp.client.globalpreferences.myaccountpreferences;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.globalpreferences.GlobalPreferencesPanels;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;

//This class used to setup GlobalPreferences for ViewRegistration View
public class ViewRegistrationDetails extends Composite implements GlobalPreferencesPanels{

	private static ViewRegistrationDetailsUiBinder uiBinder = GWT
			.create(ViewRegistrationDetailsUiBinder.class);

	interface ViewRegistrationDetailsUiBinder extends
			UiBinder<Widget, ViewRegistrationDetails> {
	}
	
	@UiField
	CheckBox checkShowThisPanel;
	@UiField
	CheckBox checkRegisteredTo;
	@UiField
	CheckBox checkName;
	@UiField
	CheckBox checkEmail;
	@UiField
	CheckBox checkUserName;
	@UiField
	CheckBox checkLastTimeEdited;
	@UiField
	CheckBox checkAllowEditing;
	

	public ViewRegistrationDetails() {
		initWidget(uiBinder.createAndBindUi(this));
		setHandlers();
	}


	private void setHandlers() {
		
//		checkRegisteredTo.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> event) {
//				if(event.getValue()== true){
//					checkShowThisPanel.setChecked(true);
//				}
//			}
//		});
//		checkFirstName.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> event) {
//				if(event.getValue()== true){
//					checkShowThisPanel.setChecked(true);
//				}
//			}
//		});
//		checkLastName.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> event) {
//				if(event.getValue()== true){
//					checkShowThisPanel.setChecked(true);
//				}
//			}
//		});
//		checkPassword.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> event) {
//				if(event.getValue()== true){
//					checkShowThisPanel.setChecked(true);
//				}
//			}
//		});
//		checkLastTimeEdited.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> event) {
//				if(event.getValue()== true){
//					checkShowThisPanel.setChecked(true);
//				}
//			}
//		});
//		checkAllowEditing.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> event) {
//				if(event.getValue()== true){
//					checkShowThisPanel.setChecked(true);
//				}
//			}
//		});
		
		checkShowThisPanel.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(event.getValue()== true){
					enableAllFields();
				}else{
					disableAllFields();
				}
			}
		});
	}


	public CheckBox getCheckShowThisPanel() {
		return checkShowThisPanel;
	}


	public void setCheckShowThisPanel(CheckBox checkShowThisPanel) {
		this.checkShowThisPanel = checkShowThisPanel;
	}


	public CheckBox getCheckRegisteredTo() {
		return checkRegisteredTo;
	}


	public void setCheckRegisteredTo(CheckBox checkRegisteredTo) {
		this.checkRegisteredTo = checkRegisteredTo;
	}


	public CheckBox getCheckLastTimeEdited() {
		return checkLastTimeEdited;
	}


	public void setCheckLastTimeEdited(CheckBox checkLastTimeEdited) {
		this.checkLastTimeEdited = checkLastTimeEdited;
	}


	@SuppressWarnings("deprecation")
	@Override
	public void updateFieldsWithAlreadySavedPreferences(
			GlobalPreferencesEntity alreadySavedGlobalPreferencesEntity) {
		if(alreadySavedGlobalPreferencesEntity != null){
			checkName.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewRegName());
			checkUserName.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewRegUserName());
			checkEmail.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewRegEmail());
			checkLastTimeEdited.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewRegLastEdited());
			checkRegisteredTo.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewRegRegisteredTo());
			checkShowThisPanel.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewRegShowPanel());
			
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void disableAllFields() {
		checkRegisteredTo.setChecked(false);
		checkName.setChecked(false);
		checkUserName.setChecked(false);
		checkEmail.setChecked(false);
		checkLastTimeEdited.setChecked(false);
		checkAllowEditing.setChecked(false);
		
		checkRegisteredTo.setEnabled(false);
		checkName.setEnabled(false);
		checkUserName.setEnabled(false);
		checkEmail.setEnabled(false);
		checkLastTimeEdited.setEnabled(false);
		checkAllowEditing.setEnabled(false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void enableAllFields() {
		checkRegisteredTo.setChecked(true);
		checkName.setChecked(true);
		checkUserName.setChecked(true);
		checkEmail.setChecked(true);
		checkLastTimeEdited.setChecked(true);
		checkAllowEditing.setChecked(true);
		
		checkRegisteredTo.setEnabled(true);
		checkName.setEnabled(true);
		checkUserName.setEnabled(true);
		checkEmail.setEnabled(true);
		checkLastTimeEdited.setEnabled(true);
		checkAllowEditing.setEnabled(true);
	}




	public CheckBox getCheckAllowEditing() {
		return checkAllowEditing;
	}


	public CheckBox getCheckName() {
		return checkName;
	}


	public void setCheckName(CheckBox checkName) {
		this.checkName = checkName;
	}


	public CheckBox getCheckUserName() {
		return checkUserName;
	}


	public void setCheckUserName(CheckBox checkUserName) {
		this.checkUserName = checkUserName;
	}


	public CheckBox getCheckEmail() {
		return checkEmail;
	}


	public void setCheckEmail(CheckBox checkEmail) {
		this.checkEmail = checkEmail;
	}

}
