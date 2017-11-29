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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.dashboard.controlpanels.ControlPanelsContainer;
import com.sapientarrow.gwtapp.client.presenter.MainPresenter.Display;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

//This class manages the VIEW of Main Page of this application , where user have the option to switch to different 
//dashboards and global preferences and also have the option to go to Any of the application's functionality like file uploaded
//bean creation, download json etc..
public class MainView extends Composite implements Display {

    private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

    interface MainViewUiBinder extends UiBinder<Widget, MainView> {
    }

    @UiField
    VerticalPanel center;
    @UiField
    ControlPanelsContainer controlPanelContainer;
    @UiField
    Label lblWelcome;
    @UiField
    DisclosurePanel discPanel;
    private UserEntity loggedInUser;
    @UiField
    Anchor ancFileUpload;
    @UiField
    Anchor ancJavaBeanEditor;
    @UiField
    Anchor ancGlobalPreferences;
    @UiField
    Anchor ancDownloadJson;
    @UiField
    Anchor ancBeanData;
    @UiField
    Anchor ancDynamicBeanEditor;
    @UiField
    Anchor ancDynamicBeanData;
    @UiField
    Anchor ancBindings;

    public MainView(UserEntity loggedInUser) {
	initWidget(uiBinder.createAndBindUi(this));
	this.loggedInUser = loggedInUser;
	lblWelcome.setText(loggedInUser.getUserName());

    }

    public ControlPanelsContainer getControlPanelContainer() {
	return controlPanelContainer;
    }

    public void setControlPanelContainer(ControlPanelsContainer controlPanelContainer) {
	this.controlPanelContainer = controlPanelContainer;
    }

    public VerticalPanel getCenter() {
	return center;
    }

    public void setCenter(VerticalPanel center) {
	this.center = center;
    }

    public UserEntity getLoggedInUser() {
	return loggedInUser;
    }

    public Anchor getFileUpload() {
	return ancFileUpload;
    }

    public Anchor getAncJavaBeanEditor() {
	return ancJavaBeanEditor;
    }

    public Anchor getAncGlobalPreferences() {
	return ancGlobalPreferences;
    }

    public Anchor getAncDownloadJson() {
	return ancDownloadJson;
    }

    public void setAncDownloadJson(Anchor ancDownloadJson) {
	this.ancDownloadJson = ancDownloadJson;
    }

    public Anchor getAncBeanData() {
	return ancBeanData;
    }

    public Anchor getAncDynamicBeanEditor() {
	return ancDynamicBeanEditor;
    }

    public Anchor getAncDynamicBeanData() {
	return ancDynamicBeanData;
    }

    public Anchor getAncBindings() {
	return ancBindings;
    }

}
