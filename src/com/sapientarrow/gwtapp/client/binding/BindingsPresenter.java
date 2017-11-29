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
package com.sapientarrow.gwtapp.client.binding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.generateuploadedui.BeanFieldsEditorView;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.widgets.AddBindingWidget;
import com.sapientarrow.gwtapp.client.view.widgets.DisplayAlert;
import com.sapientarrow.gwtapp.client.view.widgets.PopupsView;
import com.sapientarrow.gwtapp.client.view.widgets.UploadedComponents.UploadedBindingClass;
import com.sapientarrow.gwtapp.shared.BindingsFieldVerifier;
import com.sapientarrow.gwtapp.shared.dto.BeanObjectDTO;
import com.sapientarrow.gwtapp.shared.dto.BindingsDTO;
import com.sapientarrow.gwtapp.shared.entity.NameSpaceEntity;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

// This class manages the Functionality for Binding which is used for Bindings, create/edit/delete binding.
public class BindingsPresenter implements Presenter 

{

	private final Display display;
	private final HelloServiceAsync rpcService;
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	private LinkedHashMap<String, String> valueMapBindingsList = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> valueMapNameSpaceList = new LinkedHashMap<String, String>();
	private BindingsDTO selectedBindingsDTO; // For Editing
	private ArrayList<Integer> selectedBindings = new ArrayList<Integer>(); // For deletion
	private ArrayList<BeanObjectDTO> beanObjects = new ArrayList<BeanObjectDTO>();
	@SuppressWarnings("rawtypes")
	private TreeMap beanPropertiesMap = new TreeMap();
	private AddBindingWidget addBindingWidget;

	public interface Display 
	{
		Widget asWidget();
		Button getBtnAddBinding();
		void popuplateTable(ArrayList<BindingsDTO> result);
		Column<BindingsDTO, String> getEditColumn();
		Column<BindingsDTO, String> getRemoveColumn();
		Column<BindingsDTO, Boolean> getCbColumn();
		Button getBtnDeleteBindings();
		CellTable<BindingsDTO> getTableBindings();
		TextBox getTxtSearch();
	}  

	public BindingsPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.display = view;
		this.eventBus = eventBus;
		this.rpcService = rpcService;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		setHandlers();
		addPremetiveTypes();
		fetchBindings("");

	}

	private void fetchBindings(String keyword) {
		rpcService.fetchAllBindings(keyword, new AsyncCallback<ArrayList<BindingsDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchAllBindings Failed :" + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(ArrayList<BindingsDTO> result) {
				display.popuplateTable(result);
			}
		});

	}

	private void fetchNameSpaces() {
		rpcService.fetchNameSpaces(new AsyncCallback<ArrayList<NameSpaceEntity>>() {

			@Override
			public void onSuccess(ArrayList<NameSpaceEntity> result) {
				addBindingWidget.getListBindings().clearValue();

				for(int i=0; i< result.size(); i++){

					valueMapNameSpaceList.put(result.get(i).getNameSpaceId()+"", result.get(i).getNameSpaceName());

				}
				addBindingWidget.getListNameSpace().setValueMap(valueMapNameSpaceList);
				//				addBindingWidget.getListBindings().setDefaultValue("int");

				if(selectedBindingsDTO != null){
//					addBindingWidget.getTxtBindingName().setText(selectedBindingsDTO.getBindingName());
//					addBindingWidget.getTxtBindingValue().setText(selectedBindingsDTO.getBindingValue());
//					addBindingWidget.getListBindings().setValue(selectedBindingsDTO.getBindingType());
//					addBindingWidget.getListNameSpace().setValue(selectedBindingsDTO.getNameSpaceId().getNameSpaceId());
					
					addBindingWidget.getTxtBindingName().setText(selectedBindingsDTO.getBindingName());
					addBindingWidget.getTxtBindingValue().setText(selectedBindingsDTO.getBindingValue());
					addBindingWidget.getListBindings().setValue(selectedBindingsDTO.getBeanId()+"");
					addBindingWidget.getListBindings().setDisplayField(selectedBindingsDTO.getBindingType());
					addBindingWidget.getListNameSpace().setValue(selectedBindingsDTO.getNameSpaceId().getNameSpaceId());
					addBindingWidget.getListNameSpace().setDisplayField(selectedBindingsDTO.getNameSpaceId().getNameSpaceName());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				SC.warn("fetch namespaces failed : "+ caught.getLocalizedMessage());
			}
		});

	}

	private void fetchBeans() {
		rpcService.fetchAllBeansInDb(new AsyncCallback<ArrayList<BeanObjectDTO>>() {

			@Override
			public void onSuccess(ArrayList<BeanObjectDTO> result) {

				addBindingWidget.getListBindings().clearValue();

				for(int i=0; i< result.size(); i++){
					if(! beanObjects.contains(result.get(i))){
						beanObjects.add(result.get(i));
					}
					valueMapBindingsList.put(result.get(i).getBeanId()+"", result.get(i).getBeanName());

				}
				addBindingWidget.getListBindings().setValueMap(valueMapBindingsList);
				addBindingWidget.getListBindings().setDefaultValue("int");

				if(selectedBindingsDTO != null){
//					addBindingWidget.getTxtBindingName().setText(selectedBindingsDTO.getBindingName());
//					addBindingWidget.getTxtBindingValue().setText(selectedBindingsDTO.getBindingValue());
//					addBindingWidget.getListBindings().setValue(selectedBindingsDTO.getBeanId()+"");
//					addBindingWidget.getListBindings().setDisplayField(selectedBindingsDTO.getBindingType());
					//here	
				}
				fetchNameSpaces();
			}

			@Override
			public void onFailure(Throwable caught) {
				SC.warn("fetch beans failed : "+ caught.getLocalizedMessage());
			}
		});
	}

	private void addPremetiveTypes() {
		valueMapBindingsList.put("short"   , "short");  
		valueMapBindingsList.put("int"     , "int");  
		valueMapBindingsList.put("long"    , "long");  
		valueMapBindingsList.put("float"   , "float");  
		valueMapBindingsList.put("double"  , "double");  
		valueMapBindingsList.put("boolean" , "boolean");  
		valueMapBindingsList.put("char"    , "char");
		valueMapBindingsList.put("string"  , "String");
		valueMapBindingsList.put("byte"    , "byte");
		valueMapBindingsList.put("date"    , "date");
	}

	private boolean isPremetiveType(String input){

		if(input.equals("int")|| input.equals("short")|| input.equals("long")|| input.equals("float")
				|| input.equals("double") || input.equals("boolean") || input.equals("char") || input.equals("String")
				|| input.equals("byte") || input.equals("date")){
			return true;
		}
		return false;
	}

	private void bind() {

		display.getTxtSearch().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				fetchBindings(display.getTxtSearch().getText());
			}
		});

		display.getCbColumn().setFieldUpdater(new FieldUpdater<BindingsDTO, Boolean>() {

			@Override
			public void update(int index, BindingsDTO object, Boolean value) {
				if(value == true){
					selectedBindings.add(object.getBindingId());

					display.getTableBindings().getRowElement(index).addClassName("bluebg");

				}else{
					if(selectedBindings.contains(object.getBindingId())){
						selectedBindings.remove(selectedBindings.indexOf(object.getBindingId()));
						display.getTableBindings().getRowElement(index).removeClassName("bluebg");
					}
				}
				if(selectedBindings.size()>0){
					display.getBtnDeleteBindings().setEnabled(true);
				}else{
					display.getBtnDeleteBindings().setEnabled(false);
				}
			}
		});


		display.getBtnDeleteBindings().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SC.ask("Are you sure to delete the selected Bindings " , new BooleanCallback() {  
					public void execute(Boolean value) {  
						if (value != null && value) {
							deleteBindigs();

						}
					}
				});
			}
		});

		display.getEditColumn().setFieldUpdater(new FieldUpdater<BindingsDTO, String>() {

			@Override
			public void update(int index, BindingsDTO bindingsDTO, String value) {
				selectedBindingsDTO = bindingsDTO;
				editBinding();

			}
		});

		display.getRemoveColumn().setFieldUpdater(new FieldUpdater<BindingsDTO, String>() {

			@Override
			public void update(int index, BindingsDTO BindingsDTO, String value) {

				final int bindingId = BindingsDTO.getBindingId();
				SC.ask("Are you sure to delete binding named " + BindingsDTO.getBindingName(), new BooleanCallback() {  
					public void execute(Boolean value) {  
						if (value != null && value) {
							deleteBinding(bindingId);

						}

					}

				});
			}
		});

		display.getBtnAddBinding().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectedBindingsDTO = null;
				openAddBindingWidget();
			}
		});
	}

	private void editBinding() {

		openAddBindingWidget();

	}

	private void deleteBindigs() {
		rpcService.deleteMultipleBindings(selectedBindings, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				fetchBindings("");
				display.getBtnDeleteBindings().setEnabled(false);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
			}
		});

	}

	private void openAddBindingWidget() {
		addBindingWidget = new AddBindingWidget();

		if(selectedBindingsDTO!=null  && !isPremetiveType(selectedBindingsDTO.getBindingValue())){
			addBindingWidget.getAncEditBean().setVisible(true);
		}else{
			addBindingWidget.getAncEditBean().setVisible(false);
		}

		final DecoratedPopupPanel popup = new DecoratedPopupPanel();
		popup.setStyleName("whitebg");
		popup.setWidget(addBindingWidget);
		popup.center();
		addBindingWidget.getTxtBindingName().setFocus(true);


		addBindingWidget.getImgClose().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				popup.removeFromParent();
			}
		});
		populateAddBindingWidget();
		setAddBindingHandlers(popup);
	}

	private void deleteBinding(int bindingId) {

		rpcService.deleteBinding(bindingId, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				fetchBindings("");

			}
		});
	}

	private void setAddBindingHandlers(final PopupPanel popup) {

		addBindingWidget.getBtnSave().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveBinding(addBindingWidget, popup);
			}});

		addBindingWidget.getTxtBindingValue().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(event.getNativeKeyCode() == 13){
					saveBinding(addBindingWidget, popup);
				}
			}
		});

		addBindingWidget.getListBindings().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if(isPremetiveType(addBindingWidget.getListBindings().getDisplayValue())){
					addBindingWidget.getAncEditBean().setVisible(false);
				}else{
					addBindingWidget.getAncEditBean().setVisible(true);
				}
			}
		});

		addBindingWidget.getAncEditBean().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				int beanId = Integer.parseInt(addBindingWidget.getListBindings().getValueAsString());
				
				overriteBeansLayoutClassWithAlreadyAvailableUI(beanId, popup);
			}
		});
	}

	private void uploadNewBeanUi(final PopupPanel popup, int beanId) {
		final BeanFieldsEditorView beanFieldsEditorView = new BeanFieldsEditorView(ApplicationConstants.BEAN_CREATION_FOR_BINDING+":"+beanId);
		beanFieldsEditorView.getListBeans().setVisible(false);
		beanFieldsEditorView.getImgClose().setVisible(true);
		popup.setWidget(beanFieldsEditorView);
		popup.center();

		beanFieldsEditorSetHandlers(popup, beanFieldsEditorView);
	}

	//	private void createBeanLayoutWithJson(String beansJson) {
	//		JSONValue value = JSONParser.parse(beansJson);
	//		JSONObject productsObj = value.isObject();
	//		Set<String> jsonSet = productsObj.keySet();
	//		
	//		Object[] array = jsonSet.toArray();
	//		for(int i=0; i<array.length; i++){
	//			   Object o = array[i];
	//			   Object b = productsObj.get(array[i].toString());
	//		}
	//		
	//	}

	public String fetchBeansJson(int beanId){
		String beansJson = "" ;
		for(int i=0; i< beanObjects.size(); i++){
			if(beanObjects.get(i).getBeanId() == beanId){
				beansJson =  beanObjects.get(i).getBeanJson();
				break;
			}
		}
		return beansJson;
	}	

	private void beanFieldsEditorSetHandlers(final PopupPanel popup,
			final BeanFieldsEditorView beanFieldsEditorView) {
		beanFieldsEditorView.getImgClose().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				popup.removeFromParent();
			}
		});

		beanFieldsEditorView.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try{
					String xmlFileName = beanFieldsEditorView.getXmlComponentAttachment().getDefaultUploader().getFilename();
					String javaFileName = beanFieldsEditorView.getJavaComponentAttachment().getDefaultUploader().getFilename();
					int indXml = xmlFileName.indexOf(".");
					String xmlFileExt = xmlFileName.substring(indXml);
					int indJava = javaFileName.indexOf(".");
					String javaFileExt = javaFileName.substring(indJava);
					if(!xmlFileExt.equals(".ui.xml") && !xmlFileExt.equals("")){
						SC.warn(ApplicationConstants.INVALID_UIXML_FILE);
					}
					else if(!javaFileExt.equals(".java")){
						SC.warn(ApplicationConstants.INVALID_UIJAVA_FILE);
					}
					else{
						beanFieldsEditorView.getXmlComponentAttachment().getForm().submit();
						beanFieldsEditorView.getJavaComponentAttachment().getForm().submit();
					}
				}catch(Exception ex){
					SC.warn(ApplicationConstants.INVALID_UIXML_AND_JAVA_FILE);
				}
			}
		});

		beanFieldsEditorView.getJavaComponentAttachment().getForm().addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				int startInd = event.getResults().indexOf(">");
				int endInd = event.getResults().lastIndexOf("<");
				String bean = event.getResults().substring(startInd+2, endInd);

				int beanId = Integer.parseInt(bean);
				popup.removeFromParent();
				overriteBeansLayoutClass(beanId, popup);

			}


		});
	}

	private void overriteBeansLayoutClass(final int beanId, final PopupPanel popupBinding) {
		rpcService.overriteBeansLayoutClass(beanId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.warn(caught.getLocalizedMessage());

			}

			@Override
			public void onSuccess(String result) {
//				UploadedClass uploaded = new UploadedClass();
//				new PopupsView(uploaded);
				displayLoadedUI(beanId, popupBinding);

			}
		});

	}

	private void overriteBeansLayoutClassWithAlreadyAvailableUI(final int beanId, final PopupPanel popupBinding) {
		rpcService.overriteBeansLayoutClass(beanId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				if(caught.getLocalizedMessage().equals(ApplicationConstants.NO_UI_CLASS_FOUND)){
					uploadNewBeanUi(popupBinding, beanId);
				}else{
					SC.warn(caught.getLocalizedMessage());
				}
			}

			@Override
			public void onSuccess(String result) {
				displayLoadedUI(beanId, popupBinding);

			}

			
		});

	}
	
	private void displayLoadedUI(final int beanId,
			final PopupPanel popupBinding) {
		popupBinding.hide();
		UploadedBindingClass uploadedClass = new UploadedBindingClass();
//		String json = fetchBeansJson(beanId);
		VerticalPanel vpnlPopup = new VerticalPanel();
		Button btnSave = new Button("Save");
		btnSave.addStyleName("primary button");
		vpnlPopup.add(uploadedClass);
		vpnlPopup.add(btnSave);
		final PopupsView popupBean = new PopupsView(vpnlPopup);
		popupBinding.setWidget(addBindingWidget);
		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				popupBean.getPopup().removeFromParent();
				popupBinding.center();
			}
		});
		
		popupBean.getClose().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupBinding.center();
			}
		});

		uploadedClass.pcs.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				editBeanOnPropertyChange(addBindingWidget.getListBindings().getValueAsString(), evt.getPropertyName(), evt.getNewValue());
			}


		});
	}

	@SuppressWarnings("unchecked")
	private void editBeanOnPropertyChange(String selectedBeanName,
			String propertyName, Object object) {

		beanPropertiesMap.put(propertyName, object);
	}

	private void populateAddBindingWidget() {

		fetchBeans();
	}

	@Override
	public void setHandlers() {

	}

	private void saveBinding(final AddBindingWidget addBindingWidget, final PopupPanel popup) {

		addBindingWidget.getHtmlError().clear();
		BindingsFieldVerifier bindingsFieldVerifier = new BindingsFieldVerifier();
		bindingsFieldVerifier.validate(addBindingWidget);

		if(bindingsFieldVerifier.getErrors().getErrors().size()<=0){
			BindingsDTO bindingsDTO = null;
			if(selectedBindingsDTO == null){
				bindingsDTO = new BindingsDTO();
			}else{
				bindingsDTO = selectedBindingsDTO;	
			}
			bindingsDTO.setBindingName(addBindingWidget.getTxtBindingName().getText());
			bindingsDTO.setBindingType( addBindingWidget.getListBindings().getDisplayValue());
			bindingsDTO.setBindingValue(addBindingWidget.getTxtBindingValue().getText());
			if(isPremetiveType(bindingsDTO.getBindingType())){
				char type = 'S';
				bindingsDTO.setType(type);
			}else{
				char type = 'B';
				bindingsDTO.setType(type);
				bindingsDTO.setBeanId(Integer.parseInt(addBindingWidget.getListBindings().getValueAsString()));

			}
			NameSpaceEntity nameSpace = new NameSpaceEntity();
			try{
				int nameSpaceId = Integer.parseInt(addBindingWidget.getListNameSpace().getValueAsString());
				String name = addBindingWidget.getListNameSpace().getDisplayValue();
				nameSpace.setNameSpaceId(nameSpaceId);
				nameSpace.setNameSpaceName(name);
			}catch(Exception ex){
				nameSpace.setNameSpaceName(addBindingWidget.getListNameSpace().getValueAsString());
			}
			bindingsDTO.setBeanPropertiesMap(beanPropertiesMap);
			bindingsDTO.setNameSpaceId(nameSpace);
			saveBindingInDb(popup, bindingsDTO);
		}
		else{
			addBindingWidget.getHtmlError().add(new HTML(bindingsFieldVerifier.getErrorMessages()));
		}
	}

	private void saveBindingInDb(final PopupPanel popup,
			BindingsDTO BindingsDTO) {
		rpcService.saveBinding(BindingsDTO, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("binding saved")){
					new DisplayAlert(result);
					fetchBindings("");
					popup.removeFromParent();
				}else{
					SC.warn(result);
				}
			}
		});
	}

}
