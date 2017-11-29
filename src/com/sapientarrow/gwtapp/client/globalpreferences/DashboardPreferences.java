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
package com.sapientarrow.gwtapp.client.globalpreferences;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;

public class DashboardPreferences extends Composite {

	private static EditDashboardDetailsUiBinder uiBinder = GWT
			.create(EditDashboardDetailsUiBinder.class);

	interface EditDashboardDetailsUiBinder extends
			UiBinder<Widget, DashboardPreferences> {
	}

	public DashboardPreferences() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	RadioButton checkDashboard;
	@UiField
	RadioButton checkAccordion;
	@UiField
	RadioButton checkPortlet;

	public void updateFieldsWithAlreadySavedPreferences(
			GlobalPreferencesEntity alreadySavedGlobalPreferencesEntity) {
		
		 DOM.setElementAttribute(checkDashboard.getElement(), "id", "w3-radio");
		
		if(alreadySavedGlobalPreferencesEntity != null){
			checkDashboard.setChecked(alreadySavedGlobalPreferencesEntity.isPanelTypeDashboard());
			checkAccordion.setChecked(alreadySavedGlobalPreferencesEntity.isPanelTypeAccordion());
			checkPortlet.setChecked(alreadySavedGlobalPreferencesEntity.isPanelTypePortlet());
		}
		
	}

	public RadioButton getCheckDashboard() {
		return checkDashboard;
	}

	public void setCheckDashboard(RadioButton checkDashboard) {
		this.checkDashboard = checkDashboard;
	}

	public RadioButton getCheckAccordion() {
		return checkAccordion;
	}

	public void setCheckAccordion(RadioButton checkAccordion) {
		this.checkAccordion = checkAccordion;
	}

	public RadioButton getCheckPortlet() {
		return checkPortlet;
	}

	public void setCheckPortlet(RadioButton checkPortlet) {
		this.checkPortlet = checkPortlet;
	}

}
