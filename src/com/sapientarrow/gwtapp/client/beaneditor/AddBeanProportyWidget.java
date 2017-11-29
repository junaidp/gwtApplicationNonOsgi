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
package com.sapientarrow.gwtapp.client.beaneditor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.widgets.ButtonImageCell;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanPropertyDTO;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

//This class acts as a celltable widget , which is being used in JavaBeanEditor to display all the beans 
// user is creating at run time.
public class AddBeanProportyWidget extends Composite {

	private static AddBeanProportyWidgetUiBinder uiBinder = GWT
			.create(AddBeanProportyWidgetUiBinder.class);

	interface AddBeanProportyWidgetUiBinder extends
			UiBinder<Widget, AddBeanProportyWidget> {
	}
	
	@UiField
	HTMLPanel comboContainer;
	@UiField
	CellTable<AddedBeanPropertyDTO> table;
	@UiField
	SimplePager pager;
	private Column<AddedBeanPropertyDTO, String> nameColumn;
	private Column<AddedBeanPropertyDTO, String> modifierColumn;
	private Column<AddedBeanPropertyDTO, String> dataTypeColumn;
	private Column<AddedBeanPropertyDTO, String> getterSettersColumn;
	private Column<AddedBeanPropertyDTO, String> editColumn;
	private Column<AddedBeanPropertyDTO, String> removeColumn;
	private LinkedHashMap<String, String> valueMapDataType = new LinkedHashMap<String, String>();  
	
	ComboBoxItem listDataType = new ComboBoxItem("dataType");  
	private SelectItem listAccessModifiers = new SelectItem();
	private SelectItem listHaveGetterSetters = new SelectItem();
	private SelectItem listNonAccessModifiers = new SelectItem();
	@UiField
	Button btnSave;
	@UiField
	Image imgClose;
	private TextItem textPropertyName = new TextItem();
	@UiField
	AddAnnotationsWidget addAnnotationsWidget;

	public AddBeanProportyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		listDataType.setTitle("Data Type");  
		listAccessModifiers.setTitle("Access Modifiers");
		listNonAccessModifiers.setTitle("Non Access Modifiers");
		listHaveGetterSetters.setTitle("Getters/Setters");
		
		listDataType.setWidth(300);
		listAccessModifiers.setWidth(300);
		listHaveGetterSetters.setWidth(300);
		listNonAccessModifiers.setWidth(300);
		listDataType.setWrapTitle(false);
		listAccessModifiers.setWrapTitle(false);
		listHaveGetterSetters.setWrapTitle(false);
		listNonAccessModifiers.setWrapTitle(false);
		textPropertyName.setWidth(300);
		textPropertyName.setTitle(ApplicationConstants.PROPERTY_NAME);
		
		listAccessModifiers.setDefaultValue("private");
		listDataType.setDefaultValue("int");
		listHaveGetterSetters.setDefaultValue("both");
		addAnnotationsWidget.getLblHeading().setText(ApplicationConstants.FIELD_LEVEL_ANNOTATIONS);
		
		
		//TODO: Populate lists from database....
		LinkedHashMap<String, String> valueMapGetterSetters = new LinkedHashMap<String, String>();  
		   
		valueMapGetterSetters.put("getters", "getters");
		valueMapGetterSetters.put("setters", "setters");
		valueMapGetterSetters.put("both", "both");
		
		valueMapDataType.put("short", "short");  
        valueMapDataType.put("int", "int");  
        valueMapDataType.put("long", "long");  
        valueMapDataType.put("float", "float");  
        valueMapDataType.put("double", "double");  
        valueMapDataType.put("boolean", "boolean");  
        valueMapDataType.put("char", "char");
        valueMapDataType.put("string", "String");
        valueMapDataType.put("byte", "byte");
        valueMapDataType.put("date", "date");
       
        DynamicForm form = new DynamicForm();
        comboContainer.add(form);
        btnSave.setTitle(ApplicationConstants.SAVE);
        listDataType.setValueMap(valueMapDataType);
        listHaveGetterSetters.setValueMap(valueMapGetterSetters);
      
        form.setItems(textPropertyName, listDataType, listAccessModifiers, listHaveGetterSetters);
        form.setHeight(207);
        
        populateList(listAccessModifiers);
        
        setTable();
        
        listAccessModifiers.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				
			}
		});
       
	}
	
	public void populateAddedBeanPropertyDTO(ArrayList<AddedBeanPropertyDTO> addedBeanFields) {
		table.setRowData(0, addedBeanFields);
		table.setRowCount(addedBeanFields.size());
		pager.setDisplay(table);
		sortTableGroup(addedBeanFields);
	}
	
	private void setTable() {
		
		table.setEmptyTableWidget(new HTML("No Data"));
		table.setRowCount(0);

		nameColumn = new Column<AddedBeanPropertyDTO, String>(new TextCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return object.getName();
			}
		};
		
		
		modifierColumn = new Column<AddedBeanPropertyDTO, String>(new TextCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return object.getModifier();
			}
		};
		
		getterSettersColumn = new Column<AddedBeanPropertyDTO, String>(new TextCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return object.getGetterSetter();
			}
		};
		
		dataTypeColumn = new Column<AddedBeanPropertyDTO, String>(new TextCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return object.getDataType();
			}
		};
		
		removeColumn = new Column<AddedBeanPropertyDTO, String>(new ButtonImageCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return "images/delete.png";
			}
		};
		
		editColumn = new Column<AddedBeanPropertyDTO, String>(new ButtonImageCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return "images/edit.png";
			}
		};
		
		
		
		dataTypeColumn = new Column<AddedBeanPropertyDTO, String>(new TextCell()) {
			@Override
			public String getValue(AddedBeanPropertyDTO object) {
				return object.getDataType();
			}
		};
		
		nameColumn.setSortable(true);
		table.addColumn(nameColumn,"Field Name");
		
		modifierColumn.setSortable(true);
		table.addColumn(modifierColumn,"Modifier");
		
		dataTypeColumn.setSortable(true);
		table.addColumn(dataTypeColumn,"Data Type");
		
		getterSettersColumn.setSortable(true);
		table.addColumn(getterSettersColumn,"Getter/Setters");
		
		table.addColumn(removeColumn,"");
		table.addColumn(editColumn,"");
		

	}
	
	public void populateList(SelectItem listAccessModifiers){
		
		LinkedHashMap<String, String> valueMapAccessModifiers = new LinkedHashMap<String, String>();  
		valueMapAccessModifiers.put("public"   , "public");  
		valueMapAccessModifiers.put("protected", "protected"); 
		valueMapAccessModifiers.put("private"  , "private"); 
		
		listAccessModifiers.setValueMap(valueMapAccessModifiers); 
			
	}
	
	private void sortTableGroup(List<AddedBeanPropertyDTO> groupList){
		ListDataProvider<AddedBeanPropertyDTO> dataProvider = new ListDataProvider<AddedBeanPropertyDTO>();
		dataProvider.addDataDisplay(table);

		List<AddedBeanPropertyDTO> list = dataProvider.getList();

		for (AddedBeanPropertyDTO group : groupList) {
		list.add(group);
		}
		final ListHandler<AddedBeanPropertyDTO> columnSortHandler = new ListHandler<AddedBeanPropertyDTO>(list);

		columnSortHandler.setComparator(nameColumn,new Comparator<AddedBeanPropertyDTO>() {

		public int compare(AddedBeanPropertyDTO o1,AddedBeanPropertyDTO o2) {
		if (o1 == o2) {
		return 0;
		}

		// Compare the groupname columns.
		if (o1 != null) {

		return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
		}
		return -1;
		}
		});
		
		columnSortHandler.setComparator(modifierColumn,new Comparator<AddedBeanPropertyDTO>() {

			public int compare(AddedBeanPropertyDTO o1,AddedBeanPropertyDTO o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the groupname columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getModifier().compareTo(o2.getModifier()) : 1;
			}
			return -1;
			}
			});
		
		columnSortHandler.setComparator(dataTypeColumn,new Comparator<AddedBeanPropertyDTO>() {

			public int compare(AddedBeanPropertyDTO o1,AddedBeanPropertyDTO o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the groupname columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getDataType().compareTo(o2.getDataType()) : 1;
			}
			return -1;
			}
			});
		
		columnSortHandler.setComparator(getterSettersColumn,new Comparator<AddedBeanPropertyDTO>() {

			public int compare(AddedBeanPropertyDTO o1,AddedBeanPropertyDTO o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the groupname columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getGetterSetter().compareTo(o2.getGetterSetter()) : 1;
			}
			return -1;
			}
			});
		
				
		
		table.addColumnSortHandler(columnSortHandler);
		table.getColumnSortList().push(getterSettersColumn);
		table.getColumnSortList().push(dataTypeColumn);
		table.getColumnSortList().push(modifierColumn);
		table.getColumnSortList().push(nameColumn);
		
		
	}

	public HTMLPanel getComboContainer() {
		return comboContainer;
	}

	public ComboBoxItem getListDataType() {
		return listDataType;
	}

	public SelectItem getListAccessModifiers() {
		return listAccessModifiers;
	}

	public SelectItem getListHaveGetterSetters() {
		return listHaveGetterSetters;
	}

	public SelectItem getListNonAccessModifiers() {
		return listNonAccessModifiers;
	}

	public TextItem getTextPropertyName() {
		return textPropertyName;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void clearFields() {
		textPropertyName.setValue("");
	}

	public Image getImgClose() {
		return imgClose;
	}

	public Column<AddedBeanPropertyDTO, String> getEditColumn() {
		return editColumn;
	}

	public Column<AddedBeanPropertyDTO, String> getRemoveColumn() {
		return removeColumn;
	}

	public LinkedHashMap<String, String> getValueMapDataType() {
		return valueMapDataType;
	}

	public AddAnnotationsWidget getAddAnnotationsWidget() {
		return addAnnotationsWidget;
	}

}
