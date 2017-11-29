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
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.client.HelloServiceAsync;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.widgets.AnnotationWidget;
import com.sapientarrow.gwtapp.client.view.widgets.DataObjectWidget;
import com.sapientarrow.gwtapp.client.view.widgets.DisplayAlert;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanDTO;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanPropertyDTO;
import com.sapientarrow.gwtapp.shared.dto.AnnotationsDTO;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

// This class manages the Functionality of the javaBeanEditor , where user will be able to create a new Java Bean ,
// including variables, annotations for the bean and selecting the package where he wants to save the generated bean.
// Also here user can create a reflection of a selected class.
public class JavaBeanEditorPresenter implements Presenter 

{

	private final Display display;
	private final HelloServiceAsync rpcService;
	private ArrayList<AddedBeanPropertyDTO> listAddedBeanPropertyDTO = new ArrayList<AddedBeanPropertyDTO>();
	private AddedBeanPropertyDTO selectedBeanProprtyDTO ;
	private ArrayList<String> listImports = new ArrayList<String>();
	private AnnotationsDTO selectedAnnotationDTO;
	private AnnotationWidget selectedAnnotationWidget;
	private boolean annotationWithImport;

	public interface Display 
	{
		Widget asWidget();
		Button getBtnGenerate();
		Button getBtnAddProperty();
		Button getBtnCreate();
		TextBox getTxtBeanName();
//		ComboBoxItem getListPackages();
		TextBox getTextPackageName();
		ListBox getListBeans();
		void clearFields();
		AddBeanProportyWidget getAddBeanPropertyWidget();
		ListBox getListAddObject();
		VerticalPanel getVpnlObjectContainer();
		AddAnnotationsWidget getAddAnnotationsWidget();
		Anchor getBtnAssignEditors();
		String getBeanType();

	}  

	public JavaBeanEditorPresenter(HelloServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.display = view;
		this.rpcService = rpcService;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		setHandlers();
		addBeanProportyWidgetHandlers();
	}

	private void bind() {
		populatePackagesList();
		populateDataObjectList();
		fetchAnnotations();
		//		display.getListBeans().addItem("com.helloworld.shared.beans.MyFirstBean");
	}

	private void fetchAnnotations() {

		rpcService.fetchAnnotations(new AsyncCallback<ArrayList<AnnotationsDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(final ArrayList<AnnotationsDTO> listAnnotations) {

				populateClassLeveAnnotations(ApplicationConstants.ANNOTATIONS_JPA, listAnnotations);

				display.getAddAnnotationsWidget().getListImports().addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						populateClassLeveAnnotations(display.getAddAnnotationsWidget().getListImports().getValue(
								display.getAddAnnotationsWidget().getListImports().getSelectedIndex()), listAnnotations);
					}
				});


				populateFieldLevelAnnotations(ApplicationConstants.ANNOTATIONS_JPA, listAnnotations);

				display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListImports().addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						populateFieldLevelAnnotations(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListImports().getValue(
								display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListImports().getSelectedIndex()), listAnnotations);
					}
				});
			}
		});

	}

	public void populateClassLeveAnnotations(String annotationClass, ArrayList<AnnotationsDTO> listAnnotations){
		display.getAddAnnotationsWidget().getValueMapAnnotations().clear();
		display.getAddAnnotationsWidget().getListAnnotations().clearValue();
		for(int i=0; i< listAnnotations.size(); i++){
			if(annotationClass.equals("all")){
				display.getAddAnnotationsWidget().getValueMapAnnotations().put(listAnnotations.get(i).getImportClass(), listAnnotations.get(i).getName());
			}else{
				if(listAnnotations.get(i).getImportClass().contains(annotationClass)){
					display.getAddAnnotationsWidget().getValueMapAnnotations().put(listAnnotations.get(i).getImportClass(), listAnnotations.get(i).getName());	
				}
			}

		}

		display.getAddAnnotationsWidget().getListAnnotations().setValueMap(display.getAddAnnotationsWidget().getValueMapAnnotations());
		display.getAddAnnotationsWidget().getListAnnotations().setDefaultValue("");
		display.getAddAnnotationsWidget().getListAnnotations().setTooltip("Select Annotation");

	}

	public void populateFieldLevelAnnotations(String annotationClass, ArrayList<AnnotationsDTO> listAnnotations){
		display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getValueMapAnnotations().clear();
		for(int i=0; i< listAnnotations.size(); i++){
			if(annotationClass.equals("all")){
				display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getValueMapAnnotations().put(listAnnotations.get(i).getImportClass(), listAnnotations.get(i).getName());
			}else{
				if(listAnnotations.get(i).getImportClass().contains(annotationClass)){
					display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getValueMapAnnotations().put(listAnnotations.get(i).getImportClass(), listAnnotations.get(i).getName());	
				}
			}
		}

		display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotations().setValueMap(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getValueMapAnnotations());
		display.getAddAnnotationsWidget().getListAnnotations().setDefaultValue("");
		display.getAddAnnotationsWidget().getListAnnotations().setTooltip("Select Annotation");
	}

	public void populateDataObjectList(){
		rpcService.fetchClassesOfAllPackages(new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.warn(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(ArrayList<String> classes) {
				display.getListAddObject().clear();
				display.getListBeans().clear();
				display.getListAddObject().addItem(ApplicationConstants.ADDOBJECT);
				display.getListBeans().addItem(ApplicationConstants.SELECT_FOR_REFLECTION);
				//				display.getListBeans().addItem("com.helloworld.shared.beans.MyFirstBean");
				for(int i=0; i< classes.size(); i++){
					int separatorIndex = classes.get(i).indexOf("::"); 
					display.getListAddObject().addItem(classes.get(i).substring(separatorIndex+2), classes.get(i).substring(0, separatorIndex));
					display.getListBeans().addItem(classes.get(i).substring(0, separatorIndex));
				}
			}
		});
	}

	public void populatePackagesList(){

		final LinkedHashMap<String, String> valueMapPackagesList = new LinkedHashMap<String, String>();  

		rpcService.fetchPackages(new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.warn("fetchPackages failed : "+ caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
//				display.getListPackages().clearValue();

				for(int i=0; i< result.size(); i++){
					if(result.get(i).startsWith(ApplicationConstants.DEFAULT_PACKAGE)){
						valueMapPackagesList.put(result.get(i), result.get(i));
					}
				}
//				display.getListPackages().setValueMap(valueMapPackagesList);
//				display.getListPackages().setDefaultValue("Select Package");

			}
		});

	}

	private void saveEditAnnotation(AddAnnotationsWidget addAnnotationsWidget, LinkedHashMap<String, String> valueMapAnnotations) {
		if(addAnnotationsWidget.getBtnSaveAnnotation().getText().equals(ApplicationConstants.EDIT)){
			updateAnnotation("@"+addAnnotationsWidget.getListAnnotations().getDisplayValue()
					, addAnnotationsWidget.getListAnnotations().getValueAsString(),
					addAnnotationsWidget.getTxtAnnotationText().getText());

			addAnnotationsWidget.getBtnSaveAnnotation().setText(ApplicationConstants.SAVE);
		}
		else{
			AnnotationsDTO annotationsDTO = new AnnotationsDTO();
			addAnnotationAnchor("@"+addAnnotationsWidget.getListAnnotations().getDisplayValue()
					, addAnnotationsWidget.getListAnnotations().getValueAsString(),
					addAnnotationsWidget.getTxtAnnotationText().getText(), addAnnotationsWidget, annotationsDTO, valueMapAnnotations);
		}

		if(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getTxtAnnotationText().getText().contains("FetchType")){
			if(!listImports.contains("javax.persistence.FetchType")){
				listImports.add("javax.persistence.FetchType");
			}
		}
	}

	@Override
	public void setHandlers() {

		display.getBtnAssignEditors().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem(ApplicationConstants.TOKEN_UPLOAD_COMPONENTS);
			}
		});

		display.getAddAnnotationsWidget().getBtnSaveAnnotation().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {


				saveFieldClassLevelAnnotation(display.getAddAnnotationsWidget(), display.getAddAnnotationsWidget().getValueMapAnnotations() );
			}


		});

		display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getBtnSaveAnnotation().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveFieldClassLevelAnnotation(display.getAddBeanPropertyWidget().getAddAnnotationsWidget(), display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getValueMapAnnotations());

				//				AnnotationsDTO annotationsDTO = new AnnotationsDTO();
				//				annotationsDTO.setImportClass(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotations().getValueAsString());
				//				annotationsDTO.setName(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotations().getDisplayValue());
				//				display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotationsDTOs().add(annotationsDTO);
				//				display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotations().setValue("");

			}
		});

//		display.getTextPackageName().addKeyUpHandler(new KeyUpHandler() {
//
//			@Override
//			public void onKeyUp(KeyUpEvent event) {
//
//				//				if(!display.getListPackages().getDisplayValue().startsWith(ApplicationConstants.DEFAULT_PACKAGE)){
//				if(!validDefaultPackage(display.getListPackages().getDisplayValue())){
//					event.cancel();
//					try{
//						String previousValue = display.getListPackages().getDisplayValue().substring(0, display.getListPackages().getDisplayValue().length()-1);
//						display.getListPackages().setValue(previousValue);
//					}catch(Exception ex){}
//				}
//			}
//		});
		
		
		display.getTextPackageName().addKeyUpHandler(new com.google.gwt.event.dom.client.KeyUpHandler() {
			
			@Override
			public void onKeyUp(com.google.gwt.event.dom.client.KeyUpEvent event) {
				if(!validDefaultPackage(display.getTextPackageName().getText())){
					event.preventDefault();
					try{
						String previousValue = display.getTextPackageName().getText().substring(0, display.getTextPackageName().getText().length()-1);
						display.getTextPackageName().setValue(previousValue);
					}catch(Exception ex){}
				}
			}
		});
		
		display.getListAddObject().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if(!display.getListAddObject().getItemText(display.getListAddObject().getSelectedIndex()).equals(ApplicationConstants.ADDOBJECT)){
					addObjectAnchor(display.getListAddObject().getItemText(display.getListAddObject().getSelectedIndex()), display.getListAddObject().getValue(display.getListAddObject().getSelectedIndex()));
					display.getListAddObject().setSelectedIndex(0);
				}
			}


		});

		display.getBtnGenerate().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.getBtnGenerate().addStyleName("loading-pulse");
				fetchBeanjson();
			}
		});



		display.getBtnCreate().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(display.getTextPackageName().getText().equals(ApplicationConstants.SELECT_PACKAGE)
						|| ! display.getTextPackageName().getText().startsWith(ApplicationConstants.DEFAULT_PACKAGE)||
						!validPackage(display.getTextPackageName().getText())){
					SC.warn(ApplicationConstants.PACKAGE_NOT_SELECTED);
				}else if(display.getTxtBeanName().getText().trim().length()<1 ||
						!validClass(display.getTxtBeanName().getText())){
					SC.warn(ApplicationConstants.BEANNAME_REQUIRED);
				}else{
					if(listAddedBeanPropertyDTO.size()<1){
						alertNoProperties();
					}else{
						generateBean();
					}
				}
			}
		});


		display.getBtnAddProperty().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				initializeFields();
			}
		});
	}

	private void saveFieldClassLevelAnnotation(final AddAnnotationsWidget addAnnotationsWidget, final LinkedHashMap<String, String> valueMapAnnotations) {
		if(addAnnotationsWidget.getListAnnotations().getDisplayValue().trim().length()>0){
			if(annotationImportAvailable(addAnnotationsWidget.getListAnnotations().getValueAsString())){
				annotationWithImport = true;
				saveEditAnnotation(addAnnotationsWidget, valueMapAnnotations);
				initiateAddAnnotationFields(addAnnotationsWidget);
			}else{
				SC.ask(ApplicationConstants.IMPORT_NOT_AVAILABLE, new BooleanCallback() {  
					public void execute(Boolean value) {  
						if (value != null && value) {
							annotationWithImport = false;
							saveEditAnnotation(addAnnotationsWidget, valueMapAnnotations);
							initiateAddAnnotationFields(addAnnotationsWidget);
						}  
					}
				});  
			}

		}

	}

	private void updateAnnotation(final String annotationName, String annotationPackage, String annotationText) {
		selectedAnnotationDTO.setName(annotationName);
		selectedAnnotationDTO.setAnnotationText(annotationText);
		selectedAnnotationDTO.setImportClass(annotationWithImport?  annotationPackage: "");
		selectedAnnotationWidget.getCustomAnchor().setPackageName(annotationWithImport?  annotationPackage: "");
		selectedAnnotationWidget.getCustomAnchor().setText(annotationName);
		selectedAnnotationWidget.getAnnotaionText().setText(annotationText);

	}

	private boolean annotationImportAvailable(String importName) {
		if(importName.trim().length()>0 &&(
				importName.contains(ApplicationConstants.ANNOTATIONS_CDI)||
				importName.contains(ApplicationConstants.ANNOTATIONS_HIBERNATE)||
				importName.contains(ApplicationConstants.ANNOTATIONS_JPA)||
				importName.contains(ApplicationConstants.ANNOTATIONS_SPRING))){

			return true;
		}
		return false;
	}

	public boolean validDefaultPackage(String input){
		for(int i=0; i< ApplicationConstants.DEFAULT_PACKAGE.length(); i++){
			if(input.length()> i){
				if(!(input.charAt(i) == ApplicationConstants.DEFAULT_PACKAGE.charAt(i))){
					return false;
				}
			}
		}
		return true;

	}

	public boolean validPackage(String input){
		if(input.endsWith(".")){
			return false;
		}
		if(!Character.isLetter(input.charAt(0))){
			return false;
		}

		for(int i=0; i< input.length(); i++){

			if(!Character.isLetterOrDigit(input.charAt(i))){
				if(!(input.charAt(i) =='.'))
					return false;
			}
		}
		return true;
	}

	public boolean validClass(String input){

		if(!Character.isLetter(input.charAt(0))){
			return false;
		}

		for(int i=0; i< input.length(); i++){

			if(!Character.isLetterOrDigit(input.charAt(i))){

				return false;
			}
		}
		return true;
	}

	private void initializeFields() {
		display.getAddBeanPropertyWidget().setVisible(true);
		display.getAddBeanPropertyWidget().getListDataType().setDisabled(false);
		display.getAddBeanPropertyWidget().getListDataType().setValue("int");
		display.getAddBeanPropertyWidget().getTextPropertyName().setValue("");
		display.getAddBeanPropertyWidget().getBtnSave().setText(ApplicationConstants.SAVE);
	}

	public void addCustomDataTypesinList(){
		if(!display.getAddBeanPropertyWidget().getValueMapDataType().containsValue(
				display.getAddBeanPropertyWidget().getListDataType().getDisplayValue())){
			display.getAddBeanPropertyWidget().getValueMapDataType().put(display.getAddBeanPropertyWidget().getListDataType().getDisplayValue(), display.getAddBeanPropertyWidget().getListDataType().getDisplayValue());
			display.getAddBeanPropertyWidget().getListDataType().setValueMap(display.getAddBeanPropertyWidget().getValueMapDataType());
		}

	}

	private void addAnnotationAnchor(final String annotationName, String annotationPackage, String annotationText, 
			final AddAnnotationsWidget addAnnotationsWidget, final AnnotationsDTO annotationsDTO, final LinkedHashMap<String, String> valueMapAnnotations) {
		final AnnotationWidget annotationWidget = new AnnotationWidget();
		annotationWidget.getCustomAnchor().setText(annotationName);
		annotationWidget.getCustomAnchor().setPackageName(annotationWithImport?  annotationPackage: "");
		annotationWidget.getAnnotaionText().setText(annotationText);

		//		final AnnotationsDTO annotationsDTO = new AnnotationsDTO();
		annotationsDTO.setImportClass(annotationWithImport?  annotationPackage: "");
		annotationsDTO.setName(annotationName);
		annotationsDTO.setAnnotationText(annotationText);
		addAnnotationsWidget.getListAnnotationsDTOs().add(annotationsDTO);
		addAnnotationsWidget.getVpnlContainer().add(annotationWidget);

		annotationWidget.getCustomAnchor().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean importMatchFound= false;
				for(int i=0; i< addAnnotationsWidget.getListImports().getItemCount(); i++){
					if(annotationWidget.getCustomAnchor().getPackageName().contains(addAnnotationsWidget.getListImports().getValue(i))){
						addAnnotationsWidget.getListImports().setSelectedIndex(i);

						break;
					}
				}
				System.out.println(annotationWidget.getCustomAnchor().getText());
				for (Entry<String, String> entry : valueMapAnnotations.entrySet()) {
					String key = entry.getKey();
					if(annotationWidget.getCustomAnchor().getPackageName().equals(key)){
						addAnnotationsWidget.getListAnnotations().setValue(key);
						importMatchFound = true;
						break;
					}
				}
				if(!importMatchFound){
					addAnnotationsWidget.getListAnnotations().setValue(annotationWidget.getCustomAnchor().getText().substring(1));
				}	
				addAnnotationsWidget.getTxtAnnotationText().setText(annotationWidget.getAnnotaionText().getText());
				addAnnotationsWidget.getBtnSaveAnnotation().setText(ApplicationConstants.EDIT);
				selectedAnnotationDTO = annotationsDTO;
				selectedAnnotationWidget = annotationWidget;

			}


		});

		annotationWidget.getImgDelete().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SC.ask("Are you sure you want to Remove this Annotation ? ", new BooleanCallback() {  
					public void execute(Boolean value) {  
						if (value != null && value) {  
							initiateAnnotaionsContainer(addAnnotationsWidget,
									annotationsDTO, annotationWidget);
							initiateAddAnnotationFields(addAnnotationsWidget);
						}  
					}



				});  
			}
		});

	}

	private void initiateAnnotaionsContainer(
			final AddAnnotationsWidget addAnnotationsWidget,
			final AnnotationsDTO annotationsDTO,
			final AnnotationWidget annotationWidget) {
		addAnnotationsWidget.getVpnlContainer().remove(annotationWidget);
		addAnnotationsWidget.getListAnnotationsDTOs().remove(annotationsDTO);
	}

	private void initiateAddAnnotationFields(AddAnnotationsWidget addAnnotationsWidget) {
		addAnnotationsWidget.getListAnnotations().setValue("");
		addAnnotationsWidget.getTxtAnnotationText().setText("");
		addAnnotationsWidget.getBtnSaveAnnotation().setText(ApplicationConstants.SAVE);
	}

	private void addObjectAnchor(final String className, String objectPackage) {
		final DataObjectWidget dataObjectWidget = new DataObjectWidget();
		dataObjectWidget.getCustomAnchor().setText(className);
		dataObjectWidget.getCustomAnchor().setPackageName(objectPackage);
		AddedBeanPropertyDTO addedBeanPropertyDTO = new AddedBeanPropertyDTO();

		int random=0;
		while(! isNameAvailable(dataObjectWidget.getCustomAnchor().getText())){
			random = random+1;
			dataObjectWidget.getCustomAnchor().setText(className+random);
		}
		display.getAddBeanPropertyWidget().getListDataType().setValue(className);
		display.getAddBeanPropertyWidget().getTextPropertyName().setValue(getObjectName(dataObjectWidget.getCustomAnchor().getText()));
		AddedBeanPropertyDTO dto = saveBeanProperties(display.getAddBeanPropertyWidget(), addedBeanPropertyDTO);
		dto.setName(getObjectName(dataObjectWidget.getCustomAnchor().getText()));
		dataObjectWidget.setAddedBeanPropertyDTO(dto);


		display.getVpnlObjectContainer().add(dataObjectWidget);
		initializeFields();
		initiateAddAnnotationFields(display.getAddBeanPropertyWidget().getAddAnnotationsWidget());

		dataObjectWidget.getCustomAnchor().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.getAddBeanPropertyWidget().setVisible(true);
				updateFieldsWithSelectedRow(dataObjectWidget.getAddedBeanPropertyDTO());
				display.getAddBeanPropertyWidget().getListDataType().setValue(className);
				display.getAddBeanPropertyWidget().getListDataType().setDisabled(true);

			}


		});

		dataObjectWidget.getImgDelete().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SC.ask("Are you sure you want to Remove this Object ? ", new BooleanCallback() {  
					public void execute(Boolean value) {  
						if (value != null && value) {  
							display.getVpnlObjectContainer().remove(dataObjectWidget);

						}  
					}

				});  
			}
		});

	}

	public String getObjectName(String className){
		String fieldName = className;
		fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1); 
		return fieldName;
	}

	private void addBeanProportyWidgetHandlers() {
		display.getAddBeanPropertyWidget().getImgClose().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.getAddBeanPropertyWidget().setVisible(false);
				display.getAddBeanPropertyWidget().clearFields();
			}
		});

		display.getAddBeanPropertyWidget().getBtnSave().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(fieldsValid()){
					if(display.getAddBeanPropertyWidget().getBtnSave().getText().equals(ApplicationConstants.SAVE)){

						boolean nameAvailable = true;
						for(int i=0; i< listAddedBeanPropertyDTO.size(); i++){
							if(listAddedBeanPropertyDTO.get(i).getName().equals(display.getAddBeanPropertyWidget().getTextPropertyName().getDisplayValue())){
								nameAvailable = false;
								break;
							}
						}
						if(nameAvailable){
							AddedBeanPropertyDTO addedBeanPropertyDTO = new AddedBeanPropertyDTO();
							saveBeanProperties(display.getAddBeanPropertyWidget(), addedBeanPropertyDTO);
						}else{
							SC.warn("Field name: ' " + display.getAddBeanPropertyWidget().getTextPropertyName().getDisplayValue()+ " ' Already added , Please try again with a different name ");

						}

					}
					else{
						updateBeanProperties(display.getAddBeanPropertyWidget(), selectedBeanProprtyDTO);
					}
					initializeFields();

					for(int i=0; i< display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getVpnlContainer().getWidgetCount(); i++){
						AnnotationWidget existingAnnotationWidget = (AnnotationWidget)display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getVpnlContainer().getWidget(i);
						if(!(existingAnnotationWidget.getCustomAnchor().getPackageName().trim().length()<1)){
							String importObject = existingAnnotationWidget.getCustomAnchor().getPackageName();
							if(!listImports.contains(importObject)){
								listImports.add(importObject);
							}
						}
					}


					initiateAnnotationsContainer();

				}
			}



		});


		display.getAddBeanPropertyWidget().getRemoveColumn().setFieldUpdater(new FieldUpdater<AddedBeanPropertyDTO, String>() {

			@Override
			public void update(int index, final AddedBeanPropertyDTO object, String value) {
				SC.ask("Are you sure you want to Remove field:"+ object.getName() +" ? ", new BooleanCallback() {  
					public void execute(Boolean value) {  
						if (value != null && value) {  
							removeField(object);
						}  
					}

				});  
			}
		});

		display.getAddBeanPropertyWidget().getEditColumn().setFieldUpdater(new FieldUpdater<AddedBeanPropertyDTO, String>() {

			@Override
			public void update(int index, final AddedBeanPropertyDTO object, String value) {
				updateFieldsWithSelectedRow(object);
			}

		});

	}

	private void initiateAnnotationsContainer() {
		display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getVpnlContainer().clear();
		initiateAddAnnotationFields(display.getAddBeanPropertyWidget().getAddAnnotationsWidget());
		display.getAddBeanPropertyWidget().getAddAnnotationsWidget().listAnnotationsDTOs = new ArrayList<AnnotationsDTO>();
	}


	private boolean fieldsValid() {
		if(display.getAddBeanPropertyWidget().getTextPropertyName().getDisplayValue().trim().length()>0
				&& display.getAddBeanPropertyWidget().getListAccessModifiers().getDisplayValue().trim().length()>0
				&& display.getAddBeanPropertyWidget().getListDataType().getDisplayValue().trim().length()>0
				&& display.getAddBeanPropertyWidget().getListHaveGetterSetters().getDisplayValue().trim().length()>0
				)
		{
			return true;
		}else{
			return false;
		}
	}

	private void updateFieldsWithSelectedRow(final AddedBeanPropertyDTO addedBeanPropertyDTO) {
		display.getAddBeanPropertyWidget().getTextPropertyName().setValue(addedBeanPropertyDTO.getName());
		display.getAddBeanPropertyWidget().getListAccessModifiers().setValue(addedBeanPropertyDTO.getModifier());
		display.getAddBeanPropertyWidget().getListDataType().setValue(addedBeanPropertyDTO.getDataType());
		display.getAddBeanPropertyWidget().getListHaveGetterSetters().setValue(addedBeanPropertyDTO.getGetterSetter());

		initiateAnnotationsContainer();
		for(int i=0; i<addedBeanPropertyDTO.getListAnnotationsDTO().size(); i++ ){
			addAnnotationAnchor(addedBeanPropertyDTO.getListAnnotationsDTO().get(i).getName()
					,addedBeanPropertyDTO.getListAnnotationsDTO().get(i).getImportClass(),
					addedBeanPropertyDTO.getListAnnotationsDTO().get(i).getAnnotationText(), display.getAddBeanPropertyWidget().getAddAnnotationsWidget(),
					addedBeanPropertyDTO.getListAnnotationsDTO().get(i), display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getValueMapAnnotations());
		}
		selectedBeanProprtyDTO =  addedBeanPropertyDTO;		
		display.getAddBeanPropertyWidget().getBtnSave().setText(ApplicationConstants.UPDATE);

	}

	private void removeField(AddedBeanPropertyDTO addedBeanPropertyDTO) {
		for(int i=0; i< listAddedBeanPropertyDTO.size(); i++){
			if(listAddedBeanPropertyDTO.get(i) == addedBeanPropertyDTO){
				listAddedBeanPropertyDTO.remove(i);
				removeObjectfromContainer(addedBeanPropertyDTO);
				break;
			}
		}
		display.getAddBeanPropertyWidget().populateAddedBeanPropertyDTO(listAddedBeanPropertyDTO);

	}

	private void removeObjectfromContainer(
			AddedBeanPropertyDTO addedBeanPropertyDTO) {


		for(int i=0; i< display.getVpnlObjectContainer().getWidgetCount(); i++){
			DataObjectWidget dataObjectWidget = (DataObjectWidget)display.getVpnlObjectContainer().getWidget(i);
			if(dataObjectWidget.getAddedBeanPropertyDTO() == addedBeanPropertyDTO){
				display.getVpnlObjectContainer().remove(i);
				break;
			}
		}

	}

	private void alertNoProperties() {
		SC.ask("You have not added any property to this bean , You like to proceed with empty Bean ? ", new BooleanCallback() {  
			public void execute(Boolean value) {  
				if (value != null && value) {  
					generateBean();
				}  
			}
		});  
	}

	private void generateBean() {
		AddedBeanDTO addedBeanDTO = new AddedBeanDTO();
		addedBeanDTO.setListProperties(listAddedBeanPropertyDTO);
		addedBeanDTO.setBeanName(display.getTxtBeanName().getText());
//		addedBeanDTO.setPackageName(display.getListPackages().getDisplayValue());
		addedBeanDTO.setPackageName(display.getTextPackageName().getText());
		addedBeanDTO.setListAnnotationsDTO(display.getAddAnnotationsWidget().getListAnnotationsDTOs());
		addImports();
		addedBeanDTO.setListImports(listImports);

		if(display.getBeanType().equals(ApplicationConstants.JAVA_BEAN)){
			generateJavaBean(addedBeanDTO);
		}
		else if(display.getBeanType().equals(ApplicationConstants.DYNAMIC_BEAN)){
			generateDynamicBean(addedBeanDTO);
		}
	}

	private void generateJavaBean(AddedBeanDTO addedBeanDTO) {
		rpcService.generateJavaBean(addedBeanDTO, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.warn(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(String result) {
				SC.say(result);
				populatePackagesList();
				populateDataObjectList();
			}
		});
	}  

	private void generateDynamicBean(AddedBeanDTO addedBeanDTO) {
		rpcService.generateDynamicBean(addedBeanDTO, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.warn(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(String result) {
				SC.say(result);
				populatePackagesList();
				populateDataObjectList();
			}
		});
	}  

	private AddedBeanPropertyDTO saveBeanProperties(
			AddBeanProportyWidget addBeanProportyWidget,AddedBeanPropertyDTO addedBeanPropertyDTO) {

		addedBeanPropertyDTO.setDataType(addBeanProportyWidget.getListDataType().getDisplayValue());
		addedBeanPropertyDTO.setGetterSetter(addBeanProportyWidget.getListHaveGetterSetters().getDisplayValue());
		addedBeanPropertyDTO.setModifier(addBeanProportyWidget.getListAccessModifiers().getDisplayValue());
		addedBeanPropertyDTO.setName(addBeanProportyWidget.getTextPropertyName().getDisplayValue());
		addedBeanPropertyDTO.setListAnnotationsDTO(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotationsDTOs());
		addCustomDataTypesinList();
		listAddedBeanPropertyDTO.add(addedBeanPropertyDTO);


		display.getAddBeanPropertyWidget().populateAddedBeanPropertyDTO(listAddedBeanPropertyDTO);
		display.clearFields();
		return addedBeanPropertyDTO;

	}

	public void addImports(){
		for(int i=0; i< display.getVpnlObjectContainer().getWidgetCount(); i++){
			DataObjectWidget existingDataObjectWidget = (DataObjectWidget)display.getVpnlObjectContainer().getWidget(i);
			String importObject = existingDataObjectWidget.getCustomAnchor().getPackageName();
			if(!listImports.contains(importObject)){
				listImports.add(importObject);
			}

		}

		for(int i=0; i< display.getAddAnnotationsWidget().getVpnlContainer().getWidgetCount(); i++){
			AnnotationWidget existingAnnotationWidget = (AnnotationWidget)display.getAddAnnotationsWidget().getVpnlContainer().getWidget(i);
			if(!(existingAnnotationWidget.getCustomAnchor().getPackageName().trim().length()<1)){
				String importObject = existingAnnotationWidget.getCustomAnchor().getPackageName();
				if(!listImports.contains(importObject)){
					listImports.add(importObject);
				}
			}
		}


	}

	public boolean isNameAvailable(String name){

		for(int i=0; i< display.getVpnlObjectContainer().getWidgetCount(); i++){
			DataObjectWidget existingDataObjectWidget = (DataObjectWidget)display.getVpnlObjectContainer().getWidget(i);
			if(existingDataObjectWidget.getCustomAnchor().getText().equals(name)){
				return false;
			}
		}
		return true;

	}

	private AddedBeanPropertyDTO updateBeanProperties(
			AddBeanProportyWidget addBeanProportyWidget,AddedBeanPropertyDTO addedBeanPropertyDTO) {


		addedBeanPropertyDTO.setDataType(addBeanProportyWidget.getListDataType().getDisplayValue());
		addedBeanPropertyDTO.setGetterSetter(addBeanProportyWidget.getListHaveGetterSetters().getDisplayValue());
		addedBeanPropertyDTO.setModifier(addBeanProportyWidget.getListAccessModifiers().getDisplayValue());
		addedBeanPropertyDTO.setName(addBeanProportyWidget.getTextPropertyName().getDisplayValue());
		addedBeanPropertyDTO.setListAnnotationsDTO(display.getAddBeanPropertyWidget().getAddAnnotationsWidget().getListAnnotationsDTOs());

		display.getAddBeanPropertyWidget().populateAddedBeanPropertyDTO(listAddedBeanPropertyDTO);
		return addedBeanPropertyDTO;

	}

	private void fetchBeanjson() {
		rpcService.fetchBeanJSON(display.getListBeans().getValue(display.getListBeans().getSelectedIndex()),"BeanSet", "reflection", new AsyncCallback<String>() {

			@Override
			public void onSuccess(String json) {
				new DisplayAlert("Reflection generated: Json retrieved");
				display.getBtnGenerate().removeStyleName("loading-pulse");
			}

			@Override
			public void onFailure(Throwable caught) {
				new DisplayAlert("fail fetchBeanJson: "+ caught.getLocalizedMessage());
				display.getBtnGenerate().removeStyleName("loading-pulse");
			}
		});
	}

}
