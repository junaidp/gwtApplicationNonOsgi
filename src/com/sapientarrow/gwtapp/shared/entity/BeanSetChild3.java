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
 
import java.util.*;

import com.sapientarrow.gwtapp.shared.entity.MyAccountEntity;

import java.io.Serializable;
public class BeanSetChild3 implements Serializable
{ 

public BeanSetChild3() {

}

final long serialVersionUID = 1;
private int userId;
private String userName;
private String password;
private String name;
private String email;
private int status;
private Date tokenGeneratedDate;
private MyAccountEntity myAccountId;

private boolean admin;
private String token;
private String userFetchStatus;

public  String getName(){ 
return name;
 }
public  void setName(String name){ 
this.name = name;
 }
public  String getEmail(){ 
return email;
 }
public  int getUserId(){ 
return userId;
 }
public  void setPassword(String password){ 
this.password = password;
 }
public  int getStatus(){ 
return status;
 }
public  String getPassword(){ 
return password;
 }
public  String getUserName(){ 
return userName;
 }
public  String getToken(){ 
return token;
 }
public  boolean isAdmin(){ 
return admin;
 }
public  void setUserId(int userId){ 
this.userId = userId;
 }
public  void setUserName(String userName){ 
this.userName = userName;
 }
public  void setEmail(String email){ 
this.email = email;
 }
public  void setAdmin(boolean admin){ 
this.admin = admin;
 }
public  void setToken(String token){ 
this.token = token;
 }
public  Date getTokenGeneratedDate(){ 
return tokenGeneratedDate;
 }
public  void setTokenGeneratedDate(Date tokenGeneratedDate){ 
this.tokenGeneratedDate = tokenGeneratedDate;
 }
public  MyAccountEntity getMyAccountId(){ 
return myAccountId;
 }
public  void setStatus(int status){ 
this.status = status;
 }
public  void setMyAccountId(MyAccountEntity myAccountId){ 
this.myAccountId = myAccountId;
 }
public  String getUserFetchStatus(){ 
return userFetchStatus;
 }
public  void setUserFetchStatus(String userFetchStatus){ 
this.userFetchStatus = userFetchStatus;
 }
}