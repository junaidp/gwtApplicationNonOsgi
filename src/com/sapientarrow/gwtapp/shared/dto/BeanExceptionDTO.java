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

// This dto stores information for beans requirements which being created from Reflection
public class BeanExceptionDTO implements Serializable {
	
	private String bean;
	private String field;
	private String expecting;
	private String receiving;
	
	public BeanExceptionDTO(){
			
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getExpecting() {
		return expecting;
	}

	public void setExpecting(String expecting) {
		this.expecting = expecting;
	}

	public String getReceiving() {
		return receiving;
	}

	public void setReceiving(String receiving) {
		this.receiving = receiving;
	}

}
