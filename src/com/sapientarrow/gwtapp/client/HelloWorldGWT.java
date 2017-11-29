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
package com.sapientarrow.gwtapp.client;



import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloWorldGWT implements EntryPoint {

//	private HelloServiceAsync rpcService;

	@Override
	public void onModuleLoad() {
		Defaults.setDateFormat(null);
		HelloServiceAsync rpcService = GWT.create(HelloService.class);
		HandlerManager eventBus = new HandlerManager(null);
////				loadBeansJson();
		AppController appViewer = new AppController(rpcService, eventBus);
		appViewer.go(RootPanel.get("bodyContainer"));

	}

	public void loadBeansJson(){


//		rpcService.loadBeansJsonAndCreateDynamicBeans(new AsyncCallback<String>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				System.out.println("error in loadBeanJson from onModule"+ caught.getLocalizedMessage());
//			}
//
//			@Override
//			public void onSuccess(String result) {
//				System.out.println("success from loadBeansJsonAndCreateDynamicBeans from onModule "+ result);
//			}
//		});

	}

}
