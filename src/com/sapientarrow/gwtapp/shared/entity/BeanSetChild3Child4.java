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
import java.io.Serializable;
public class BeanSetChild3Child4 implements Serializable
{ 

public BeanSetChild3Child4() {

}

final long serialVersionUID = 1;
private int myAccountId;
private boolean monthlyPaymentPlan;
private boolean quarterlyPaymentPlan;
private boolean yearlyPaymentPlan;
private String registeredTo;
private Date lastEdited;

public  void setRegisteredTo(String registeredTo){ 
this.registeredTo = registeredTo;
 }
public  int getMyAccountId(){ 
return myAccountId;
 }
public  void setMyAccountId(int myAccountId){ 
this.myAccountId = myAccountId;
 }
public  Date getLastEdited(){ 
return lastEdited;
 }
public  void setLastEdited(Date lastEdited){ 
this.lastEdited = lastEdited;
 }
public  boolean isMonthlyPaymentPlan(){ 
return monthlyPaymentPlan;
 }
public  void setMonthlyPaymentPlan(boolean monthlyPaymentPlan){ 
this.monthlyPaymentPlan = monthlyPaymentPlan;
 }
public  boolean isQuarterlyPaymentPlan(){ 
return quarterlyPaymentPlan;
 }
public  void setQuarterlyPaymentPlan(boolean quarterlyPaymentPlan){ 
this.quarterlyPaymentPlan = quarterlyPaymentPlan;
 }
public  boolean isYearlyPaymentPlan(){ 
return yearlyPaymentPlan;
 }
public  void setYearlyPaymentPlan(boolean yearlyPaymentPlan){ 
this.yearlyPaymentPlan = yearlyPaymentPlan;
 }
public  String getRegisteredTo(){ 
return registeredTo;
 }
}