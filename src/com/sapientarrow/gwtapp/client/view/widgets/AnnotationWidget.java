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
package com.sapientarrow.gwtapp.client.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanPropertyDTO;

//This class used as a widget to display The Annotations user added at run time.
public class AnnotationWidget extends Composite {

	private static AnnotationWidgetUiBinder uiBinder = GWT
			.create(AnnotationWidgetUiBinder.class);

	interface AnnotationWidgetUiBinder extends
			UiBinder<Widget, AnnotationWidget> {
	}

	public AnnotationWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	DataObjectAnchor customAnchor;
	@UiField
	Image imgDelete;
	@UiField
	Anchor annotaionText;
	
	private AddedBeanPropertyDTO AddedBeanPropertyDTO;
		
	public DataObjectAnchor getCustomAnchor() {
		return customAnchor;
	}

	public Image getImgDelete() {
		return imgDelete;
	}

	public AddedBeanPropertyDTO getAddedBeanPropertyDTO() {
		return AddedBeanPropertyDTO;
	}

	public void setAddedBeanPropertyDTO(AddedBeanPropertyDTO addedBeanPropertyDTO) {
		AddedBeanPropertyDTO = addedBeanPropertyDTO;
	}

	public Anchor getAnnotaionText() {
		return annotaionText;
	}

	public void setAnnotaionText(Anchor annotaionText) {
		this.annotaionText = annotaionText;
	}


}
