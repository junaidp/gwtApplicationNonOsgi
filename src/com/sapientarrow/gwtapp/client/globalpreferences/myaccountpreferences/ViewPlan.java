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

//This class used to setup GlobalPreferences for ViewPlan View
public class ViewPlan extends Composite implements GlobalPreferencesPanels {

	private static ViewPlanUiBinder uiBinder = GWT
			.create(ViewPlanUiBinder.class);

	interface ViewPlanUiBinder extends UiBinder<Widget, ViewPlan> {
	}
	@UiField CheckBox paymentDetails;
	@UiField CheckBox checkBoxPaymentPlan;
	@UiField CheckBox checkBoxMonthly;
	@UiField CheckBox checkBoxQuaterly;
	@UiField CheckBox checkBoxYearly;
	@UiField CheckBox checkShowThisPanel;
	
	public ViewPlan() {
		initWidget(uiBinder.createAndBindUi(this));
		setPaymentTermsLayout();
	}
	
	@SuppressWarnings("deprecation")
	public void updateFieldsWithAlreadySavedPreferences(GlobalPreferencesEntity alreadySavedGlobalPreferencesEntity){
		if(alreadySavedGlobalPreferencesEntity != null){
			paymentDetails.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isShowPaymentDetails());
			checkBoxPaymentPlan.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewPaymentTerms());
			checkBoxMonthly.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewPlanMonthlyPayments());
			checkBoxQuaterly.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewPlanQuarterlyPayments());
			checkBoxYearly.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewPlanYearlyPayments());
			checkShowThisPanel.setChecked(alreadySavedGlobalPreferencesEntity.getMyAccountPreferencesId().isViewPlanShowPanel());
		}
	}
	
	public void setPaymentTermsLayout(){
		checkShowThisPanel.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(event.getValue() == true){
					enableAllFields();
				}else{
					disableAllFields();
				}
			}
		});
		
		checkBoxPaymentPlan.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(event.getValue() == true){
					enablePaymentFields();
				}else{
					disablePaymentFields();
				}
			}

			
		});
	}


	public CheckBox getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(CheckBox paymentDetails) {
		this.paymentDetails = paymentDetails;
	}



	public CheckBox getCheckBoxPaymentPlan() {
		return checkBoxPaymentPlan;
	}

	public CheckBox getCheckBoxMonthly() {
		return checkBoxMonthly;
	}

	public CheckBox getCheckBoxQuaterly() {
		return checkBoxQuaterly;
	}

	public CheckBox getCheckBoxYearly() {
		return checkBoxYearly;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void disableAllFields() {
		paymentDetails.setChecked(false);
		paymentDetails.setEnabled(false);
		checkBoxPaymentPlan.setChecked(false);
		checkBoxPaymentPlan.setEnabled(false);
		checkBoxMonthly.setChecked(false);
		checkBoxMonthly.setEnabled(false);
		checkBoxQuaterly.setChecked(false);
		checkBoxQuaterly.setEnabled(false);
		checkBoxYearly.setChecked(false);
		checkBoxYearly.setEnabled(false);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void enableAllFields() {
		paymentDetails.setChecked(true);
		paymentDetails.setEnabled(true);
		checkBoxPaymentPlan.setChecked(true);
		checkBoxPaymentPlan.setEnabled(true);
		checkBoxMonthly.setChecked(true);
		checkBoxMonthly.setEnabled(true);
		checkBoxQuaterly.setChecked(true);
		checkBoxQuaterly.setEnabled(true);
		checkBoxYearly.setChecked(true);
		checkBoxYearly.setEnabled(true);
	}
	
	@SuppressWarnings("deprecation")
	private void disablePaymentFields() {
		
		checkBoxMonthly.setChecked(false);
		checkBoxMonthly.setEnabled(false);
		checkBoxQuaterly.setChecked(false);
		checkBoxQuaterly.setEnabled(false);
		checkBoxYearly.setChecked(false);
		checkBoxYearly.setEnabled(false);
	}

	@SuppressWarnings("deprecation")
	private void enablePaymentFields() {
		checkBoxMonthly.setChecked(true);
		checkBoxMonthly.setEnabled(true);
		checkBoxQuaterly.setChecked(true);
		checkBoxQuaterly.setEnabled(true);
		checkBoxYearly.setChecked(true);
		checkBoxYearly.setEnabled(true);
	}

	public CheckBox getCheckShowThisPanel() {
		return checkShowThisPanel;
	}

}
