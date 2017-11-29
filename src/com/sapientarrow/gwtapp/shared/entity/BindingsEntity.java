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
package com.sapientarrow.gwtapp.shared.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// This class is an entity for Bindings which is saving Bindings 
@Table(name="bindings")

@Entity
public class BindingsEntity   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bindingId")
	private int bindingId;
	
	@Column(name="bindingName")
	private String bindingName;
	
	@Column(name="bindingType")
	private String bindingType;
	
	@Column(name="bindingValue")
	private String bindingValue;
	
	@JoinColumn(name="nameSpaceId")
	@ManyToOne( fetch = FetchType.LAZY )
	private NameSpaceEntity nameSpaceId;
	
	@Column(name="bindingValue_ext")
	@Lob
	private Blob bindingValue_ext;
	
	@Column(name="type")
	private char type;
	
	@Column(name="beanId")
	private int beanId;

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

	public Blob getBindingValue_ext() {
		return bindingValue_ext;
	}

	public void setBindingValue_ext(Blob bindingValue_ext) {
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
	

	
	

}
