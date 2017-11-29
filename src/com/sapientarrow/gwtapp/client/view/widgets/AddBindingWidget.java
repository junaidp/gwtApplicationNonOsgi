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
package com.sapientarrow.gwtapp.client.view.widgets;

import java.util.LinkedHashMap;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;

// This class used as a widget for Binding creation , been used under BindingsView
public class AddBindingWidget extends Composite {

	private static AddBindingWidgetUiBinder uiBinder = GWT
			.create(AddBindingWidgetUiBinder.class);

	interface AddBindingWidgetUiBinder extends
			UiBinder<Widget, AddBindingWidget> {
	}
	@UiField
	HTMLPanel panelBindingType;
	private ComboBoxItem listBindings = new ComboBoxItem();
	private ComboBoxItem listNameSpace = new ComboBoxItem();
	@UiField
	Image imgClose;
	@UiField 
	TextBox txtBindingName;
	@UiField
	TextBox txtBindingValue;
	@UiField 
	Button btnSave;
	@UiField
	HTMLPanel htmlError;
	@UiField
	Anchor ancEditBean;
	private LinkedHashMap<String, String> valueMapDataType = new LinkedHashMap<String, String>();  
	

	public AddBindingWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		txtBindingName.getElement().setPropertyString("placeholder", ApplicationConstants.ENTER_BINDING_NAME);
		txtBindingValue.getElement().setPropertyString("placeholder", ApplicationConstants.ENTER_BINDING_VALUE);
		txtBindingValue.getElement().setPropertyString("placeholder", ApplicationConstants.ENTER_BINDING_VALUE);
		
		DynamicForm formPackages= new DynamicForm();
		panelBindingType.add(formPackages);
		formPackages.setItems(listBindings, listNameSpace);
		listBindings.setShowTitle(false);
		listBindings.setHeight(20);
		listBindings.setPickerIconHeight(40);
		listBindings.setWidth(226);
		listNameSpace.setShowTitle(false);
		listNameSpace.setHeight(20);
		listNameSpace.setPickerIconHeight(40);
		listNameSpace.setWidth(226);
	
		txtBindingName.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				listBindings.focusInItem();
			}
		});
		
		listBindings.addBlurHandler(new com.smartgwt.client.widgets.form.fields.events.BlurHandler() {
			
			@Override
			public void onBlur(
					com.smartgwt.client.widgets.form.fields.events.BlurEvent event) {
				listNameSpace.focusInItem();
			}
		});
		listNameSpace.addBlurHandler(new com.smartgwt.client.widgets.form.fields.events.BlurHandler() {
			
			@Override
			public void onBlur(
					com.smartgwt.client.widgets.form.fields.events.BlurEvent event) {
				txtBindingValue.setFocus(true);
			}
		});
		
		
	}

	public ComboBoxItem getListBindings() {
		return listBindings;
	}

	public void setListBindings(ComboBoxItem listBindings) {
		this.listBindings = listBindings;
	}

	public Image getImgClose() {
		return imgClose;
	}

	public TextBox getTxtBindingName() {
		return txtBindingName;
	}

	public TextBox getTxtBindingValue() {
		return txtBindingValue;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public ComboBoxItem getListNameSpace() {
		return listNameSpace;
	}

	public HTMLPanel getHtmlError() {
		return htmlError;
	}

	public Anchor getAncEditBean() {
		return ancEditBean;
	}

	
}
