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
package com.sapientarrow.gwtapp.client.view;
//package com.helloworld.client.view;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.uibinder.client.UiBinder;
//import com.google.gwt.uibinder.client.UiField;
//import com.google.gwt.user.client.ui.Composite;
//import com.google.gwt.user.client.ui.HTMLPanel;
//import com.google.gwt.user.client.ui.Widget;
//import com.helloworld.client.presenter.JavaBeanEditorPresenter.Display;
//import com.smartgwt.client.widgets.Button;
//import com.smartgwt.client.widgets.form.DynamicForm;
//import com.smartgwt.client.widgets.form.fields.ButtonItem;
//import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
//import com.smartgwt.client.widgets.form.fields.SelectItem;
//import com.smartgwt.client.widgets.form.fields.TextItem;
//
//public class JavaBeanEditorViewBk_SmartGWT extends Composite implements Display {
//
//	private static JavaBeanEditorViewUiBinder uiBinder = GWT.create(JavaBeanEditorViewUiBinder.class);
//
//	interface JavaBeanEditorViewUiBinder extends UiBinder<Widget, JavaBeanEditorViewBk_SmartGWT> {
//	}
//	
//	@UiField
//	HTMLPanel panelBeanGenerator;
//	@UiField
//	HTMLPanel panelReflection;
//	private ButtonItem btnGenerate = new ButtonItem();
//	private ButtonItem btnAddProperty = new ButtonItem();
//	private TextItem txtBeanName = new TextItem();
//	private Button btnCreate = new Button();
//	private SelectItem listBeans = new SelectItem();
//	private ComboBoxItem listPackages = new ComboBoxItem();
//	
//	
//	public JavaBeanEditorViewBk_SmartGWT() {
//		initWidget(uiBinder.createAndBindUi(this));
////		listPackages.setTitle(ApplicationConstants.SELECT_PACKAGE);
////		txtBeanName.setEmptyDisplayValue(ApplicationConstants.ENTER_BEANNAME);
//		DynamicForm formBeanGenerator = new DynamicForm();
////		DynamicForm formBeanGeneratorButtons = new DynamicForm();
//		formBeanGenerator.setIsGroup(true);  
//		formBeanGenerator.setGroupTitle("Create Bean");  
//		DynamicForm formReflection = new DynamicForm();
//		formReflection.setNumCols(2);
////		formBeanGeneratorButtons.setNumCols(2);
//		
////		panelBeanGenerator.add(formBeanGenerator);
////		panelBeanGenerator.add(formBeanGeneratorButtons);
//		
//		
////		formBeanGeneratorButtons.setNumCols(2);
////		formBeanGeneratorButtons.setWidth(500);
//		
//		
//		
////		panelReflection.add(formReflection);
//		formBeanGenerator.setItems(listPackages, txtBeanName, btnAddProperty);
//		formBeanGenerator.setWidth("350px");
//		formReflection.setWidth("350px");
//		formReflection.setItems(listBeans, btnGenerate);
//		btnGenerate.setTitle("Generate Reflection");
//		btnCreate.setTitle("Create Bean");
//		btnAddProperty.setTitle("Add Property");
//		listPackages.setTitle("Select Package");
//		txtBeanName.setTitle("Enter Bean Name");
//		listBeans.setTitle("Select Bean For Reflection");
//		listBeans.setWrapTitle(false);
//		listPackages.setWrapTitle(false);
//		txtBeanName.setWrapTitle(false);
//		formReflection.setIsGroup(true);
//		formReflection.setGroupTitle("Generate Reflection");
//		formBeanGenerator.moveTo(100, 50);
//		btnCreate.moveTo(200, 200);
//		formReflection.moveTo(100, 250);
//		formBeanGenerator.draw();
//		btnCreate.draw();
//		formReflection.draw();
//	}
//	
//	public void clearFields(){
//		txtBeanName.hide();
//	
//	}
//	
//	public ButtonItem getBtnGenerate() {
//		return btnGenerate;
//	}
//	public void setBtnGenerate(ButtonItem btnGenerate) {
//		this.btnGenerate = btnGenerate;
//	}
//	
//	public TextItem getTxtBeanName() {
//		return txtBeanName;
//	}
//	public void setTxtBeanName(TextItem txtBeanName) {
//		this.txtBeanName = txtBeanName;
//	}
//
//	public ComboBoxItem getListPackages() {
//		return listPackages;
//	}
//
//
//
//	public Button getBtnCreate() {
//		return btnCreate;
//	}
//
//	public ButtonItem getBtnAddProperty() {
//		return btnAddProperty;
//	}
//
//	
//}
