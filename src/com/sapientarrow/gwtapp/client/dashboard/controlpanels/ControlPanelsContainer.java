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
package com.sapientarrow.gwtapp.client.dashboard.controlpanels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;

// This class is a container View for the panels at the left on which user click to display Panels at the right.(Because of the changes structure this is currently not in use)
public class ControlPanelsContainer extends Composite {

	private static ControlPanelsContainerUiBinder uiBinder = GWT
			.create(ControlPanelsContainerUiBinder.class);

	interface ControlPanelsContainerUiBinder extends
			UiBinder<Widget, ControlPanelsContainer> {
	}
	
	@UiField
	DashboardPanelView dashboardPanel;
	@UiField
	SearchDataPanelView searchPanel;
	@UiField
	IntelligencePackPanelView intelligencePanel;
	@UiField
	DashboardAccordionPanelView dashboardAccordionPanel;
	@UiField
	DashboardPortalPanelView dashboardPortalPanel;
	@UiField
	SimplePanel portalContainer;
	@UiField
	SimplePanel dashboardContainer;
	@UiField
	SimplePanel intelligenceContainer;
	@UiField
	SimplePanel searchContainer;
	@UiField
	SimplePanel accordionContainer;

	public ControlPanelsContainer() {
		initWidget(uiBinder.createAndBindUi(this));
		portalContainer.setVisible(false);
		dashboardContainer.setVisible(false);
		accordionContainer.setVisible(false);
	}
	
	public void setGlobalPreferences(GlobalPreferencesEntity globalPreferencesEntity){
		dashboardContainer.setVisible(globalPreferencesEntity.isPanelTypeDashboard());
		accordionContainer.setVisible(globalPreferencesEntity.isPanelTypeAccordion());
		portalContainer.setVisible(globalPreferencesEntity.isPanelTypePortlet());
	}

	public DashboardPanelView getDashboardPanel() {
		return dashboardPanel;
	}

	public SearchDataPanelView getSearchPanel() {
		return searchPanel;
	}

	public IntelligencePackPanelView getIntelligencePanel() {
		return intelligencePanel;
	}

	public DashboardAccordionPanelView getDashboardAccordionPanel() {
		return dashboardAccordionPanel;
	}

	public DashboardPortalPanelView getDashboardPortalPanel() {
		return dashboardPortalPanel;
	}

}
