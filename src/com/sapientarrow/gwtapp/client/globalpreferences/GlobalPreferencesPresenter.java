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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.event.MainEvent;
import com.sapientarrow.gwtapp.client.globalpreferences.myaccountpreferences.EditRegistrationDetails;
import com.sapientarrow.gwtapp.client.globalpreferences.myaccountpreferences.ViewPlan;
import com.sapientarrow.gwtapp.client.globalpreferences.myaccountpreferences.ViewRegistrationDetails;
import com.sapientarrow.gwtapp.client.presenter.HeaderPresenter;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.HeaderView;
import com.sapientarrow.gwtapp.client.view.widgets.DisplayAlert;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

//This class manages the Functionality/logic for global preferences , where user can set up their preferences for the whole
//application, e.g change view/edit plan , view /edit Registration etc , which will then reflect through out the application.
@SuppressWarnings("deprecation")
public class GlobalPreferencesPresenter implements Presenter

{

    private final Display display;
    private ViewPlan viewPlan;
    private ViewRegistrationDetails viewRegistration;
    private EditRegistrationDetails editRegistration;
    private DashboardPreferences dashboardSelection;
    private LogoPreferences logoPreferences;
    private HeaderMenuPreferences headerMenuPreferences;
    private final HelloServiceAsync rpcService;
    private GlobalPreferencesEntity alreadySavedGlobalPreferencesEntity;
    private HandlerManager eventBus;
    private UserEntity user;

    public interface Display {
	Widget asWidget();

	Tree getTreePreferences();

	HorizontalSplitPanel getSplitPanel();

	HasClickHandlers getBtnCancel();

	Button getBtnSave();
    }

    public GlobalPreferencesPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, UserEntity loggedInUser,
	    Display view) {
	this.display = view;
	this.rpcService = rpcService;
	this.eventBus = eventBus;
	this.user = loggedInUser;
    }

    public void go(HasWidgets container) {
	container.clear();
	container.add(display.asWidget());
	bind();
	setHandlers();
    }

    private void bind() {

	fetchExistingGlobalPreferences();
    }

    @Override
    public void setHandlers() {

	display.getBtnCancel().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		History.back();
	    }
	});

	display.getBtnSave().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		savePreferences();

	    }

	});

	display.getTreePreferences().addSelectionHandler(new SelectionHandler<TreeItem>() {

	    @Override
	    public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem item = event.getSelectedItem();

		switch (item.getHTML()) {
		case ApplicationConstants.VIEW_PLAN:
		    if (viewPlan == null) {
			viewPlan = new ViewPlan();
			viewPlan.updateFieldsWithAlreadySavedPreferences(alreadySavedGlobalPreferencesEntity);
		    }

		    display.getSplitPanel().setRightWidget(viewPlan);
		    break;
		case ApplicationConstants.VIEW_REGISTRATION:
		    if (viewRegistration == null) {
			viewRegistration = new ViewRegistrationDetails();
			viewRegistration.updateFieldsWithAlreadySavedPreferences(alreadySavedGlobalPreferencesEntity);

		    }
		    display.getSplitPanel().setRightWidget(viewRegistration);
		    break;

		case ApplicationConstants.EDIT_REGISTRATION:
		    if (editRegistration == null) {
			editRegistration = new EditRegistrationDetails();
			editRegistration.updateFieldsWithAlreadySavedPreferences(alreadySavedGlobalPreferencesEntity);

		    }
		    display.getSplitPanel().setRightWidget(editRegistration);
		    break;

		case ApplicationConstants.DASHBOARD:
		    if (dashboardSelection == null) {
			dashboardSelection = new DashboardPreferences();
			dashboardSelection.updateFieldsWithAlreadySavedPreferences(alreadySavedGlobalPreferencesEntity);

		    }
		    display.getSplitPanel().setRightWidget(dashboardSelection);
		    break;

		case ApplicationConstants.LOGO:
		    if (logoPreferences == null) {
			logoPreferences = new LogoPreferences();
			logoPreferences.updateFieldsWithAlreadySavedPreferences(alreadySavedGlobalPreferencesEntity);

		    }
		    display.getSplitPanel().setRightWidget(logoPreferences);
		    break;

		case ApplicationConstants.MENUS:
		    if (headerMenuPreferences == null) {
			headerMenuPreferences = new HeaderMenuPreferences();
			headerMenuPreferences
				.updateFieldsWithAlreadySavedPreferences(alreadySavedGlobalPreferencesEntity);

		    }
		    display.getSplitPanel().setRightWidget(headerMenuPreferences);
		    break;
		}

	    }
	});
    }

    private void savePreferences() {
	GlobalPreferencesEntity globalPreferencesEntity;
	if (alreadySavedGlobalPreferencesEntity == null) {
	    globalPreferencesEntity = new GlobalPreferencesEntity();
	    MyAccountPreferencesEntity myAccountPreferencesEntity = new MyAccountPreferencesEntity();
	    globalPreferencesEntity.setMyAccountPreferencesId(myAccountPreferencesEntity);

	} else {
	    globalPreferencesEntity = alreadySavedGlobalPreferencesEntity;
	}
	saveGlobalPreferences(globalPreferencesEntity);

    }

    private void saveGlobalPreferences(final GlobalPreferencesEntity globalPreferencesEntity) {
	if (viewPlan != null) {
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setShowPaymentDetails(viewPlan.getPaymentDetails().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewPaymentTerms(viewPlan.getCheckBoxPaymentPlan().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewPlanMonthlyPayments(viewPlan.getCheckBoxMonthly().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewPlanQuarterlyPayments(viewPlan.getCheckBoxQuaterly().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewPlanYearlyPayments(viewPlan.getCheckBoxYearly().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewPlanShowPanel(viewPlan.getCheckShowThisPanel().isChecked());

	}
	if (viewRegistration != null) {
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewRegName(viewRegistration.getCheckName().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewRegLastEdited(viewRegistration.getCheckLastTimeEdited().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewRegUserName(viewRegistration.getCheckUserName().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewRegEmail(viewRegistration.getCheckEmail().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewRegRegisteredTo(viewRegistration.getCheckRegisteredTo().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setViewRegShowPanel(viewRegistration.getCheckShowThisPanel().isChecked());

	}
	if (editRegistration != null) {
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegName(editRegistration.getCheckName().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegUserName(editRegistration.getCheckUserName().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegEmail(editRegistration.getCheckEmail().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegRegisteredTo(editRegistration.getCheckRegisteredTo().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegShowPanel(editRegistration.getCheckShowThisPanel().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegPassword(editRegistration.getCheckPassword().isChecked());
	    globalPreferencesEntity.getMyAccountPreferencesId()
		    .setEditRegCloseAccount(editRegistration.getCheckCloseAccount().isChecked());

	}
	if (dashboardSelection != null) {
	    globalPreferencesEntity.setPanelTypeAccordion(dashboardSelection.getCheckAccordion().isChecked());
	    globalPreferencesEntity.setPanelTypeDashboard(dashboardSelection.getCheckDashboard().isChecked());
	    globalPreferencesEntity.setPanelTypePortlet(dashboardSelection.getCheckPortlet().isChecked());
	}
	if (logoPreferences != null) {
	    globalPreferencesEntity.setLogoUrl(logoPreferences.getAttachmentPanel().getLogoUrl());
	    try {
		globalPreferencesEntity.setLogoHeight(Integer.parseInt(logoPreferences.getTxtHeight().getText()));
		globalPreferencesEntity.setLogoWidth(Integer.parseInt(logoPreferences.getTxtWidth().getText()));
	    } catch (Exception ex) {
		Window.alert("Logo's Height/width should be a number");
	    }
	}
	if (headerMenuPreferences != null) {
	    // globalPreferencesEntity.getHeaderMenuPreferencesId().setAboutUs(headerMenuPreferences.getCheckAboutUs().isChecked());
	    // globalPreferencesEntity.getHeaderMenuPreferencesId().setContactUs(headerMenuPreferences.getCheckContactUs().isChecked());
	    // globalPreferencesEntity.getHeaderMenuPreferencesId().setHome(headerMenuPreferences.getCheckHome().isChecked());
	    //
	}

	display.getBtnSave().addStyleName("loading-pulse");
	rpcService.updateGlobalPreferences(globalPreferencesEntity, new AsyncCallback<String>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert(caught.getLocalizedMessage());
		display.getBtnSave().removeStyleName("loading-pulse");
	    }

	    @Override
	    public void onSuccess(String result) {
		new DisplayAlert(result);
		display.getBtnSave().removeStyleName("loading-pulse");
		eventBus.fireEvent(new MainEvent(user, globalPreferencesEntity));
		setHeader(user, globalPreferencesEntity);
	    }
	});

    }

    private void setHeader(UserEntity user, GlobalPreferencesEntity globalPreferences) {
	HeaderView headerView = new HeaderView();
	HeaderPresenter headerPresenter = new HeaderPresenter(rpcService, eventBus, headerView, user,
		globalPreferences);
	HasWidgets container = RootPanel.get("headerContainer");
	container.clear();
	headerPresenter.go(container);
    }

    private void fetchExistingGlobalPreferences() {

	rpcService.fetchGlobalPreferences(new AsyncCallback<GlobalPreferencesEntity>() {

	    @Override
	    public void onSuccess(GlobalPreferencesEntity result) {
		alreadySavedGlobalPreferencesEntity = result;

	    }

	    @Override
	    public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	    }
	});
    }

}
