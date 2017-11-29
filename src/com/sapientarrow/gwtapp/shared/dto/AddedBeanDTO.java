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

// This is a dto for the bean users is creating ,the information like bean name , package name
// imports required for the bean etc.. gets saved in this bean and used for creating this bean
public class AddedBeanDTO implements Serializable {
	
	public AddedBeanDTO(){}

	private String beanName;
	private String packageName;
	private ArrayList<AddedBeanPropertyDTO> listProperties = new ArrayList<AddedBeanPropertyDTO>();
	private ArrayList<String> listImports = new ArrayList<String>();
	private ArrayList<AnnotationsDTO> listAnnotationsDTO = new ArrayList<AnnotationsDTO>();
	
	public String getBeanName() {
		
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public ArrayList<AddedBeanPropertyDTO> getListProperties() {
		return listProperties;
	}
	public void setListProperties(ArrayList<AddedBeanPropertyDTO> listProperties) {
		this.listProperties = listProperties;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public ArrayList<String> getListImports() {
		return listImports;
	}
	public void setListImports(ArrayList<String> listImports) {
		this.listImports = listImports;
	}
	public ArrayList<AnnotationsDTO> getListAnnotationsDTO() {
		return listAnnotationsDTO;
	}
	public void setListAnnotationsDTO(ArrayList<AnnotationsDTO> listAnnotationsDTO) {
		this.listAnnotationsDTO = listAnnotationsDTO;
	}
	
}
