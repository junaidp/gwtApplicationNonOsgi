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
package com.sapientarrow.gwtapp.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;

// This dto use to store the bean properties 
public class AddedBeanPropertyDTO implements Serializable{
	
	public AddedBeanPropertyDTO(){}
	
	private String modifier;
	private String name;
	private String dataType;
	private String getterSetter;
	private ArrayList<AnnotationsDTO> listAnnotationsDTO = new ArrayList<AnnotationsDTO>();
	
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getGetterSetter() {
		return getterSetter;
	}
	public void setGetterSetter(String getterSetter) {
		this.getterSetter = getterSetter;
	}
	public ArrayList<AnnotationsDTO> getListAnnotationsDTO() {
		return listAnnotationsDTO;
	}
	public void setListAnnotationsDTO(ArrayList<AnnotationsDTO> listAnnotationsDTO) {
		this.listAnnotationsDTO = listAnnotationsDTO;
	}
	
}
