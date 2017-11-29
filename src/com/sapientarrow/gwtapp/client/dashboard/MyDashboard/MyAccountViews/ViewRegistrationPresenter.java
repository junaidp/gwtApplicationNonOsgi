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
package com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

// This class manages the functionality of view Registration , where user view/edit their registration's details
// like registered name , date,email etc.
public class ViewRegistrationPresenter implements Presenter 

{

	private final Display display;
	private GlobalPreferencesEntity globalPreferencesEntity;
	private UserEntity loggedInUser;
	
	public interface Display 
	{
		Widget asWidget();
		void displaySelectedPanels(MyAccountPreferencesEntity myAccountPreferencesEntity);
		void updateUIFields(UserEntity userEntity);
	}  

	public ViewRegistrationPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view, GlobalPreferencesEntity globalPreferencesEntity, UserEntity loggedInUser) 
	{
		this.display = view;
		this.globalPreferencesEntity = globalPreferencesEntity;
		this.loggedInUser = loggedInUser;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		setHandlers();
		display.updateUIFields(loggedInUser);
		
	}

	private void bind() {
		if(globalPreferencesEntity!=null){
			display.displaySelectedPanels(globalPreferencesEntity.getMyAccountPreferencesId());
		}

	}

	@Override
	public void setHandlers() {
		
		
		
	
	}
	


}
