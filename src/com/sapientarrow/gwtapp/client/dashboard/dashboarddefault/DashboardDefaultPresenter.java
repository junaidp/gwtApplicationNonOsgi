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
package com.sapientarrow.gwtapp.client.dashboard.dashboarddefault;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountPanel;
import com.sapientarrow.gwtapp.client.event.MyAccountEvent;
import com.sapientarrow.gwtapp.client.presenter.Presenter;

// This class manages the main Dashboard
public class DashboardDefaultPresenter implements Presenter

{

    private final Display display;
    private final HandlerManager eventBus;

    public interface Display {
	Widget asWidget();

	MyAccountPanel getMyAccount();
    }

    public DashboardDefaultPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) {
	this.display = view;
	this.eventBus = eventBus;
    }

    public void go(HasWidgets container) {
	container.clear();
	container.add(display.asWidget());
	bind();
	setHandlers();
    }

    private void bind() {

    }

    @Override
    public void setHandlers() {

	display.getMyAccount().getPanel().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new MyAccountEvent());
	    }
	});
    }

}
