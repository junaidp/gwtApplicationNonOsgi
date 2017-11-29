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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//Entity class for MyAccountEntity
	@Entity

	@Table(name="myaccount")
	public class MyAccountEntity   implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5102552410931111137L;

		public MyAccountEntity(){}
		
		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="myAccountId")
		private int myAccountId;
		
		@Column(name="monthlyPaymentPlan")
		private boolean monthlyPaymentPlan;
		
		@Column(name="quarterlyPaymentPlan")
		private boolean quarterlyPaymentPlan;
		
		@Column(name="yearlyPaymentPlan")
		private boolean yearlyPaymentPlan;
		
		@Column(name="registeredTo")
		private String registeredTo;
		
		@Temporal(TemporalType.DATE)
		@Column(name="lastEdited")
		private Date lastEdited;
		
		public int getMyAccountId() {
			return myAccountId;
		}

		public void setMyAccountId(int myAccountId) {
			this.myAccountId = myAccountId;
		}

		public boolean isMonthlyPaymentPlan() {
			return monthlyPaymentPlan;
		}

		public void setMonthlyPaymentPlan(boolean monthlyPaymentPlan) {
			this.monthlyPaymentPlan = monthlyPaymentPlan;
		}

		public boolean isQuarterlyPaymentPlan() {
			return quarterlyPaymentPlan;
		}

		public void setQuarterlyPaymentPlan(boolean quarterlyPaymentPlan) {
			this.quarterlyPaymentPlan = quarterlyPaymentPlan;
		}

		public boolean isYearlyPaymentPlan() {
			return yearlyPaymentPlan;
		}

		public void setYearlyPaymentPlan(boolean yearlyPaymentPlan) {
			this.yearlyPaymentPlan = yearlyPaymentPlan;
		}

	
		public String getRegisteredTo() {
			return registeredTo;
		}

		public void setRegisteredTo(String registeredTo) {
			this.registeredTo = registeredTo;
		}

		public Date getLastEdited() {
			return lastEdited;
		}

		public void setLastEdited(Date lastEdited) {
			this.lastEdited = lastEdited;
		}

	
	

	
}
