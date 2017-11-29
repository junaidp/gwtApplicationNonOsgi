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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

// This class used to Define the Menus for the Views, User can select if the subject module will have multiple menus etc.
public class AddMenuItem extends HorizontalPanel {
	
	TextBox txtMenuName = new TextBox(); 
	CheckBox checkHasSubMenu = new  CheckBox("this item will have sub menus");
	Button btnCreate = new Button("create");
	
	public AddMenuItem(){
		
		add(txtMenuName);
		add(checkHasSubMenu);
		add(btnCreate);
		setSpacing(4);
	}

	public TextBox getTxtMenuName() {
		return txtMenuName;
	}

	public void setTxtMenuName(TextBox txtMenuName) {
		this.txtMenuName = txtMenuName;
	}

	public CheckBox getCheckHasSubMenu() {
		return checkHasSubMenu;
	}

	public void setCheckHasSubMenu(CheckBox checkHasSubMenu) {
		this.checkHasSubMenu = checkHasSubMenu;
	}

	public Button getBtnCreate() {
		return btnCreate;
	}

}
