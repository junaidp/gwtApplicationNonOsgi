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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

// This class is not in Use currently , May be used as global preferences work goes on
public class HeaderItemsView extends FlowPanel {

	private static HeaderItemsViewUiBinder uiBinder = GWT
			.create(HeaderItemsViewUiBinder.class);

	interface HeaderItemsViewUiBinder extends UiBinder<Widget, HeaderItemsView> {
	}
	@UiField
	Tree tree;
	@UiField
	Button btnPreview;
	@UiField
	MenuBar menuBar;
	
	public HeaderItemsView() {
		Command command = new Command() {
			@Override
			public void execute() {
	                     Window.alert("test");
			}
		};
//		
		add(uiBinder.createAndBindUi(this));
		btnPreview.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}});
		
		menuBar.addItem("tes", command);
		MenuBar menuBar1 = new MenuBar();
		menuBar1.addItem("tst2", command);
		menuBar.addItem(new MenuItem("created", menuBar1));
		
	
	final TreeItem mainTree = new TreeItem();
	mainTree.setHTML("Main Menu");
	
	final AddMenuItem addMenu = new AddMenuItem();
	mainTree.addItem(addMenu);
//	Tree tree1 = new Tree();
	tree.addItem(mainTree);
	addMenu.getBtnCreate().addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			addChild(mainTree, addMenu);
		}

		});
	}
	
	@SuppressWarnings("deprecation")
	private void addChild(final TreeItem mainTree, final AddMenuItem addMenu) {
		if(addMenu.checkHasSubMenu.isChecked()){
		final TreeItem treeItemNew = new TreeItem();
		treeItemNew.setHTML(addMenu.getTxtMenuName().getText());
		mainTree.addItem(treeItemNew);
		final AddMenuItem addMenuNew = new AddMenuItem();
		treeItemNew.addItem(addMenuNew);
		addMenu.getTxtMenuName().setText("");
		addMenu.getCheckHasSubMenu().setChecked(false);
		addMenuNew.getBtnCreate().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				addChild(treeItemNew, addMenuNew);
			}

			});
		}else{
			Label lbl = new Label(addMenu.getTxtMenuName().getText());
			mainTree.addItem(lbl);
		}
	}

}
