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

// This class used to setup GlobalPreferences for Edit Registration View
public class EditRegistrationDetails extends Composite implements GlobalPreferencesPanels {

	private static EditRegistrationDetailsUiBinder uiBinder = GWT
			.create(EditRegistrationDetailsUiBinder.class);

	interface EditRegistrationDetailsUiBinder extends
			UiBinder<Widget, EditRegistrationDetails> {
	}

	public EditRegistrationDetails() {
		initWidget(uiBinder.createAndBindUi(this));
		setHandlers();
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
	CheckBox checkPassword;
	@UiField
	CheckBox checkCloseAccount;
	
	
	private void setHandlers() {
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void updateFieldsWithAlreadySavedPreferences(
			GlobalPreferencesEntity alreadySavedGlobalPreferencesEntity) {
		if(alreadySavedGlobalPreferencesEntity != null){
			checkName.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegName());
			checkUserName.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegUserName());
			checkEmail.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegEmail());
			checkRegisteredTo.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegRegisteredTo());
			checkShowThisPanel.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegShowPanel());
			checkPassword.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegPassword());
			checkCloseAccount.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isEditRegCloseAccount());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void disableAllFields() {
		checkRegisteredTo.setChecked(false);
		checkName.setChecked(false);
		checkUserName.setChecked(false);
		checkEmail.setChecked(false);
		checkPassword.setChecked(false);
		checkCloseAccount.setChecked(false);
		checkRegisteredTo.setEnabled(false);
		checkName.setEnabled(false);
		checkUserName.setEnabled(false);
		checkEmail.setEnabled(false);
		checkPassword.setEnabled(false);
		}

	@SuppressWarnings("deprecation")
	@Override
	public void enableAllFields() {
		checkRegisteredTo.setChecked(true);
		checkName.setChecked(true);
		checkUserName.setChecked(true);
		checkEmail.setChecked(true);
		checkPassword.setChecked(true);
		checkCloseAccount.setChecked(true);
		checkRegisteredTo.setEnabled(true);
		checkName.setEnabled(true);
		checkUserName.setEnabled(true);
		checkEmail.setEnabled(true);
		checkPassword.setEnabled(true);
	}

	public CheckBox getCheckShowThisPanel() {
		return checkShowThisPanel;
	}

	public CheckBox getCheckRegisteredTo() {
		return checkRegisteredTo;
	}

	public CheckBox getCheckName() {
		return checkName;
	}

	public CheckBox getCheckEmail() {
		return checkEmail;
	}

	public CheckBox getCheckUserName() {
		return checkUserName;
	}

	public CheckBox getCheckPassword() {
		return checkPassword;
	}

	public CheckBox getCheckCloseAccount() {
		return checkCloseAccount;
	}


}
