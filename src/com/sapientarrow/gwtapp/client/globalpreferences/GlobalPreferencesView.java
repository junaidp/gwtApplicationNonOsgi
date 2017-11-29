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
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.globalpreferences.GlobalPreferencesPresenter.Display;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

//This class manages the VIEW for global preferences , where user can set up their preferences for the whole
//application, e.g change view/edit plan , view /edit Registration etc , which will then reflect through out the application.
@SuppressWarnings("deprecation")
public class GlobalPreferencesView extends Composite implements Display {

    private static GlobalPreferencesViewUiBinder uiBinder = GWT.create(GlobalPreferencesViewUiBinder.class);

    interface GlobalPreferencesViewUiBinder extends UiBinder<Widget, GlobalPreferencesView> {
    }

    @UiField
    Tree treePreferences;
    @UiField
    TreeItem treeItemViewPlan;
    @UiField
    TreeItem treeItemViewRegistration;
    @UiField
    TreeItem treeItemEditRegistration;
    @UiField
    TreeItem treeItemUsage;
    @UiField
    TreeItem treeItemMyAccount;
    @UiField
    TreeItem treeItemLogo;
    @UiField
    TreeItem treeItemMain;
    @UiField
    TreeItem treeItemDashboard;
    @UiField
    TreeItem treeItemMenus;

    @UiField
    HorizontalSplitPanel splitPanel;
    @UiField
    Button btnCancel;
    @UiField
    Button btnSave;

    public GlobalPreferencesView() {
	initWidget(uiBinder.createAndBindUi(this));
	treeItemViewPlan.setHTML(ApplicationConstants.VIEW_PLAN);
	treeItemViewRegistration.setHTML(ApplicationConstants.VIEW_REGISTRATION);
	treeItemEditRegistration.setHTML(ApplicationConstants.EDIT_REGISTRATION);
	treeItemUsage.setHTML(ApplicationConstants.VIEW_USAGE);
	treeItemMyAccount.setHTML(ApplicationConstants.MY_ACCOUNT);
	treeItemLogo.setHTML(ApplicationConstants.LOGO);
	treeItemDashboard.setHTML(ApplicationConstants.DASHBOARD);
	treeItemMenus.setHTML(ApplicationConstants.MENUS);

	splitPanel.setHeight(Window.getClientHeight() - 150 + "px");
	splitPanel.setSplitPosition("200px");

	Window.addResizeHandler(new ResizeHandler() {

	    @Override
	    public void onResize(ResizeEvent event) {
		splitPanel.setHeight(Window.getClientHeight() - 150 + "px");

	    }
	});
    }

    public HorizontalSplitPanel getSplitPanel() {
	return splitPanel;
    }

    public Tree getTreePreferences() {
	return treePreferences;
    }

    public Button getBtnCancel() {
	return btnCancel;
    }

    public Button getBtnSave() {
	return btnSave;
    }

}
