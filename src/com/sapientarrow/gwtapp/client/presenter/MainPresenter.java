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
package com.sapientarrow.gwtapp.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.dashboard.controlpanels.ControlPanelsContainer;
import com.sapientarrow.gwtapp.client.event.DashboardAccordionEvent;
import com.sapientarrow.gwtapp.client.event.DashboardEvent;
import com.sapientarrow.gwtapp.client.event.DynamicBeanEditorEvent;
import com.sapientarrow.gwtapp.client.event.GlobalPreferencesEvent;
import com.sapientarrow.gwtapp.client.event.IntelligencePackEvent;
import com.sapientarrow.gwtapp.client.event.JavaBeanEditorEvent;
import com.sapientarrow.gwtapp.client.event.SearchDataEvent;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

// This class manages the Functionality of Main Page of this application , where user have the option to switch to different 
// dashboards and global preferences and also have the option to go to Any of the application's functionality like file uploaded
// bean creation, download json etc..
public class MainPresenter implements Presenter 

{
	
	private final Display display;
	private HandlerManager eventBus;
	private GlobalPreferencesEntity globalPreferencesEntity;

	public interface Display 
	{
		Widget asWidget();
		ControlPanelsContainer getControlPanelContainer();
		VerticalPanel getCenter();
		UserEntity getLoggedInUser();
		Anchor getFileUpload();
		Anchor getAncJavaBeanEditor();
		Anchor getAncGlobalPreferences();
		Anchor getAncDownloadJson();
		Anchor getAncBeanData();
		Anchor getAncDynamicBeanEditor();
		Anchor getAncDynamicBeanData();
		Anchor getAncBindings();
	}  

	public MainPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, GlobalPreferencesEntity globalPreferencesEntity, Display view) 
	{
		this.display = view;
		this.eventBus= eventBus;
		this.globalPreferencesEntity = globalPreferencesEntity;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		setHandlers();
	}

	private void bind() {
		
		display.getControlPanelContainer().setGlobalPreferences(globalPreferencesEntity);
	}

	@Override
	public void setHandlers() {
		
		display.getControlPanelContainer().getDashboardPanel().getFocusPanel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new DashboardEvent());
			}});
		
		display.getControlPanelContainer().getIntelligencePanel().getFocusPanel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new IntelligencePackEvent(display.getCenter()));
			}});
		
		display.getControlPanelContainer().getSearchPanel().getFocusPanel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new SearchDataEvent(display.getCenter()));
			}});
		
		display.getFileUpload().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_FILE_UPLOAD);
			}});
		
		display.getControlPanelContainer().getDashboardAccordionPanel().getFocusPanel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new DashboardAccordionEvent(display.getCenter()));
			}});
		
		display.getControlPanelContainer().getDashboardPortalPanel().getFocusPanel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_DASHBOARD_PORTAL);
			}});
		
		display.getAncJavaBeanEditor().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new JavaBeanEditorEvent());
			}
		});
		
		display.getAncGlobalPreferences().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new GlobalPreferencesEvent());
			}
		});	
		
		display.getAncDownloadJson().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_DOWNLOAD_JSON);
			}
		});	
		
		
		display.getAncBeanData().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_UPLOAD_COMPONENTS);
			}
		});
		
		display.getAncDynamicBeanData().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_UPLOAD_COMPONENTS_DYNAMICBEAN);
			}
		});
		
		display.getAncDynamicBeanEditor().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new DynamicBeanEditorEvent());
			}
		});
		
		display.getAncBindings().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_BINDINGS);
			}
		});
		
		
	}


}


