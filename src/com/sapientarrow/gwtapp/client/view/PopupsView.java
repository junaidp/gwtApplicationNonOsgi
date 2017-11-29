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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

// This class acts as a PopupPanel which can be used anywhere
public class PopupsView {
	
	DecoratedPopupPanel popup ;
	VerticalPanel vpnlMain;
	private HorizontalPanel hpnlSPace;
	
	public HorizontalPanel getHpnlSPace() {
		return hpnlSPace;
	}

	public void setHpnlSPace(HorizontalPanel hpnlSPace) {
		this.hpnlSPace = hpnlSPace;
	}

	Image close = new Image("images/close.jpg");
	
	public PopupsView(Widget widget) {
		HorizontalPanel hpnlClose = new HorizontalPanel();
		hpnlSPace = new HorizontalPanel();
		hpnlSPace.setWidth("500px");
		hpnlClose.add(hpnlSPace);
		hpnlClose.add(close);
		close.setStyleName("hover");
		popup = new DecoratedPopupPanel();
		vpnlMain = new VerticalPanel();
		vpnlMain.add(hpnlClose);
		vpnlMain.add(widget);
//		vpnlMain.setSize("800px","425px");
		//vpnlMain.
		popup.setWidget(vpnlMain);
		popup.setStyleName("whitebg");
		popup.setGlassEnabled(true);
		popup.center();
		
		close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent arg0) {
				popup.removeFromParent();
			}});
	}

	public DecoratedPopupPanel getPopup() {
		return popup;
	}


	public void setPopup(DecoratedPopupPanel popup) {
		this.popup = popup;
	}


	public Image getClose() {
		return close;
	}


	public void setClose(Image close) {
		this.close = close;
	}

	public VerticalPanel getVpnlMain() {
		return vpnlMain;
	}

	public void setVpnlMain(VerticalPanel vpnlMain) {
		this.vpnlMain = vpnlMain;
	}

	
	


}
