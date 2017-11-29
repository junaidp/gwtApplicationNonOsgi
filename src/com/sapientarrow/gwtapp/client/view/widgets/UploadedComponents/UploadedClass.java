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
package com.sapientarrow.gwtapp.client.view.widgets.UploadedComponents;
import java.util.Date;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
public class UploadedClass extends ApplicationBean{
	private static UploadedClassUiBinder uiBinder = GWT
			.create(UploadedClassUiBinder.class);
	interface UploadedClassUiBinder extends UiBinder<Widget, UploadedClass> {
	}
	
	@UiField 
	ListBox listIds;
	@UiField TextBox textBoxName;
	@UiField TextBox textBoxUser_Name;
	@UiField RadioButton checkM;
	@UiField RadioButton checkF;
	@UiField CheckBox checkReceiveNotifications;
	@UiField TextBox textBoxUser_Id;
	@UiField TextBox textBoxUser_password;
	@UiField TextBox user_MyAccountEntity_myAccountId;
	@UiField DateBox user_MyAccountEntity_lastEdited;
	@UiField TextBox txtAddress;
		
	
	
	public UploadedClass() {
		initWidget(uiBinder.createAndBindUi(this));
		listIds.addItem("0");
		listIds.addItem("1");
		listIds.addItem("2");
		
		listIds.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				pcs.firePropertyChange(listIds.getName(), "", Integer.parseInt(listIds.getItemText(listIds.getSelectedIndex())));
			}});
		
		checkM.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(event.getValue() == true){
				pcs.firePropertyChange(checkM.getName(), "", "M");
				}
				
			}
		});
		
		checkF.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(event.getValue() == true){
				pcs.firePropertyChange(checkF.getName(), "", "F");
				}
				
			}
		});
		
		checkReceiveNotifications.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				pcs.firePropertyChange(checkReceiveNotifications.getName(), "", event.getValue());
			}
		});
		
		user_MyAccountEntity_lastEdited.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				pcs.firePropertyChange("userEntity_MyAccountEntity_lastEdited", "", event.getValue());
			}
		});
		
	}
		@UiHandler("textBoxName")
		void onTextBoxBlur(BlurEvent event) { 
			pcs.firePropertyChange(textBoxName.getName(),  "", textBoxName.getText());
			
		}
		
		@UiHandler("txtAddress")
		void onTextBoxAddBlur(BlurEvent event) {
			pcs.firePropertyChange(txtAddress.getName(),  "", txtAddress.getText());
		}
		
		@UiHandler("textBoxUser_Name")
		void onTextBoxUserBlur(BlurEvent event) {
			pcs.firePropertyChange(textBoxUser_Name.getName(), "", textBoxUser_Name.getText());
		}
		
		@UiHandler("textBoxUser_Id")
		void onTextBoxUserIdBlur(BlurEvent event) {
			pcs.firePropertyChange(textBoxUser_Id.getName(), "", Integer.parseInt(textBoxUser_Id.getText()));
		}
		
		@UiHandler("textBoxUser_password")
		void onTextBoxUserPasswordBlur(BlurEvent event) {
			pcs.firePropertyChange(textBoxUser_password.getName(), "", textBoxUser_password.getText());
		}
		
		@UiHandler("user_MyAccountEntity_myAccountId")
		void onTextBoxUserMyAcIdBlur(BlurEvent event) {
			pcs.firePropertyChange(user_MyAccountEntity_myAccountId.getName(), "", Integer.parseInt(user_MyAccountEntity_myAccountId.getText()));
		}
		
}
