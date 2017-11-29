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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.eclipse.persistence.internal.dynamic.DynamicEntityImpl;
import org.eclipse.persistence.internal.dynamic.DynamicPropertiesManager;



// Not in use currently
public class DummyBean /*extends DynamicEntityImpl*/ implements Serializable 
{ 

	public DummyBean() {

	}
	public static DynamicPropertiesManager DPM = new DynamicPropertiesManager();
	@Id 
	private int id;
	private String name;
	private String address;
//	private UserEntity user;
	private String gender;
	private boolean notifications;


	public  String getAddress(){ 
		return address;
	}
	public  String getName(){ 
		return name;
	}
	public  int getId(){ 
		return id;
	}
	public  void setName(String arg0){ 
		this.name = arg0;
	}
	public  void setAddress(String arg0){ 
		this.address = arg0;
	}
//	public  UserEntity getUser(){ 
//		return user;
//	}
//	public  void setUser(UserEntity arg0){ 
//		this.user = arg0;
//	}
	public  void setId(int arg0){ 
		this.id = arg0;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isNotifications() {
		return notifications;
	}
	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}
//	@Override
//	public DynamicPropertiesManager fetchPropertiesManager() {
//		return DPM;
//	}
}