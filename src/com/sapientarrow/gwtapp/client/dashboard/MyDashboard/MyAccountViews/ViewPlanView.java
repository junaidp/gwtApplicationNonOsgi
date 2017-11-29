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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.ViewPlanPresenter.Display;
import com.sapientarrow.gwtapp.shared.entity.MyAccountEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountPreferencesEntity;

//This class is View for ViewPlan, Where user can view/edit their Plan Preferences 
//like vplan type, plan payment etc.
public class ViewPlanView extends Composite implements Display {

	private static ViewPlanViewUiBinder uiBinder = GWT
			.create(ViewPlanViewUiBinder.class);

	interface ViewPlanViewUiBinder extends UiBinder<Widget, ViewPlanView> {
	}


	@UiField
	FlowPanel planTypePanel;
	@UiField
	FlowPanel panelPaymentTerms;
	@UiField
	FlowPanel paymentDetailsPanel;
	@UiField
	RadioButton radioMonthlyPayments;
	@UiField
	RadioButton radioQuarterlyPayments;
	@UiField
	RadioButton radioYearlyPayments;
	@UiField
	Label lblPaymentTerms;
	@UiField
	Button btnUpdate;


	public ViewPlanView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	//Display only panels which Admin selected from Global preferences
	public void displaySelectedPanels(MyAccountPreferencesEntity myAccountPreferencesEntity){
		if(myAccountPreferencesEntity!=null){
			planTypePanel.setVisible(myAccountPreferencesEntity.isShowPaymentDetails());
			paymentDetailsPanel.setVisible(myAccountPreferencesEntity.isShowPlanType());
			panelPaymentTerms.setVisible(myAccountPreferencesEntity.isViewPaymentTerms());
			radioMonthlyPayments.setVisible(myAccountPreferencesEntity.isViewPlanMonthlyPayments());
			radioQuarterlyPayments.setVisible(myAccountPreferencesEntity.isViewPlanQuarterlyPayments());
			radioYearlyPayments.setVisible(myAccountPreferencesEntity.isViewPlanYearlyPayments());
			lblPaymentTerms.setVisible(myAccountPreferencesEntity.isViewPaymentTerms());
		}

	}

	public Button getBtnUpdate() {
		return btnUpdate;
	}

	//Update Fields in UI as per User Saved last time in View Plan section
	@SuppressWarnings("deprecation")
	@Override
	public void updateFields(MyAccountEntity myAccountEntity) {
		radioMonthlyPayments.setChecked(myAccountEntity.isMonthlyPaymentPlan());
		radioQuarterlyPayments.setChecked(myAccountEntity.isQuarterlyPaymentPlan());
		radioYearlyPayments.setChecked(myAccountEntity.isYearlyPaymentPlan());
		
	}
	
	//Update Entity for saving in db as per user selection in UI
	@SuppressWarnings("deprecation")
	@Override
	public void updateMyAccount(MyAccountEntity myAccountEntity){
		myAccountEntity.setMonthlyPaymentPlan(radioMonthlyPayments.isChecked());
		myAccountEntity.setQuarterlyPaymentPlan(radioQuarterlyPayments.isChecked());
		myAccountEntity.setYearlyPaymentPlan(radioYearlyPayments.isChecked());
	}

	
}
