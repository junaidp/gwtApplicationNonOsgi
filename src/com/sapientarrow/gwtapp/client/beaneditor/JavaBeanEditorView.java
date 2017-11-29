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
package com.sapientarrow.gwtapp.client.beaneditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.beaneditor.JavaBeanEditorPresenter.Display;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

//This class manages the VIEW of the javaBeanEditor , where user will be able to create a new Java Bean ,
//including variables, annotations for the bean and selecting the package where he wants to save the generated bean.
//Also here user can create a reflection of a selected class.
public class JavaBeanEditorView extends Composite implements Display {

    private static JavaBeanEditorViewUiBinder uiBinder = GWT.create(JavaBeanEditorViewUiBinder.class);

    interface JavaBeanEditorViewUiBinder extends UiBinder<Widget, JavaBeanEditorView> {
    }

    @UiField
    Button btnGenerate;
    @UiField
    Button btnAddProperty;
    @UiField
    Button btnCreate;
    @UiField
    TextBox txtBeanName;
    @UiField
    TextBox textPackageName;
    @UiField
    ListBox listBeans;
    // @UiField
    // HTMLPanel panelDynamicForm;
    @UiField
    AddBeanProportyWidget addBeanPropertyWidget;
    @UiField
    ListBox listAddObject;
    @UiField
    VerticalPanel vpnlObjectContainer;
    @UiField
    AddAnnotationsWidget addAnnotationsWidget;
    @UiField
    Anchor btnAssignEditors;
    private String beanType;

    // private ComboBoxItem listPackages = new ComboBoxItem();

    public JavaBeanEditorView(String beanType) {
	initWidget(uiBinder.createAndBindUi(this));
	this.beanType = beanType;
	txtBeanName.getElement().setPropertyString("placeholder", ApplicationConstants.ENTER_BEANNAME);
	listAddObject.addItem(ApplicationConstants.ADDOBJECT);
	listBeans.addItem(ApplicationConstants.SELECT_FOR_REFLECTION);
	textPackageName.setText(ApplicationConstants.DEFAULT_PACKAGE);
	// DynamicForm formPackages= new DynamicForm();
	// panelDynamicForm.add(formPackages);
	// formPackages.setItems(listPackages);
	// listPackages.setShowTitle(false);
	// listPackages.setHeight(20);
	// listPackages.setPickerIconHeight(40);
	// listPackages.setWidth(200);
	addAnnotationsWidget.getLblHeading().setText(ApplicationConstants.CLASS_LEVEL_ANNOTATIONS);

    }

    public void clearFields() {

    }

    public Button getBtnGenerate() {
	return btnGenerate;
    }

    public Button getBtnAddProperty() {
	return btnAddProperty;
    }

    public Button getBtnCreate() {
	return btnCreate;
    }

    public TextBox getTxtBeanName() {
	return txtBeanName;
    }

    public ListBox getListBeans() {
	return listBeans;
    }

    public AddBeanProportyWidget getAddBeanPropertyWidget() {
	return addBeanPropertyWidget;
    }

    public ListBox getListAddObject() {
	return listAddObject;
    }

    public VerticalPanel getVpnlObjectContainer() {
	return vpnlObjectContainer;
    }

    public AddAnnotationsWidget getAddAnnotationsWidget() {
	return addAnnotationsWidget;
    }

    public Anchor getBtnAssignEditors() {
	return btnAssignEditors;
    }

    public String getBeanType() {
	return beanType;
    }

    public TextBox getTextPackageName() {
	return textPackageName;
    }

}
