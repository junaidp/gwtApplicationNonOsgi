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
import java.util.TreeMap;

import com.sapientarrow.gwtapp.shared.entity.NameSpaceEntity;

//This Dto used to store the information for Bindings
public class BindingsDTO implements Serializable{

	
    private int bindingId;
	private String bindingName;
	private String bindingType;
	private String bindingValue;
	private NameSpaceEntity nameSpaceId;
	private String bindingValue_ext;
	private char type;
	private int beanId;
	private TreeMap beanPropertiesMap;
	
	public int getBindingId() {
		return bindingId;
	}
	public void setBindingId(int bindingId) {
		this.bindingId = bindingId;
	}
	public String getBindingName() {
		return bindingName;
	}
	public void setBindingName(String bindingName) {
		this.bindingName = bindingName;
	}
	public String getBindingType() {
		return bindingType;
	}
	public void setBindingType(String bindingType) {
		this.bindingType = bindingType;
	}
	public String getBindingValue() {
		return bindingValue;
	}
	public void setBindingValue(String bindingValue) {
		this.bindingValue = bindingValue;
	}
	public NameSpaceEntity getNameSpaceId() {
		return nameSpaceId;
	}
	public void setNameSpaceId(NameSpaceEntity nameSpaceId) {
		this.nameSpaceId = nameSpaceId;
	}
	public String getBindingValue_ext() {
		return bindingValue_ext;
	}
	public void setBindingValue_ext(String bindingValue_ext) {
		this.bindingValue_ext = bindingValue_ext;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public int getBeanId() {
		return beanId;
	}
	public void setBeanId(int beanId) {
		this.beanId = beanId;
	}
	public TreeMap getBeanPropertiesMap() {
		return beanPropertiesMap;
	}
	public void setBeanPropertiesMap(TreeMap beanPropertiesMap) {
		this.beanPropertiesMap = beanPropertiesMap;
	}
	
}
