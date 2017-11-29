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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity class for MyAccountPreferencesEntity
	@Entity

	@Table(name="myaccountpreferences")
	public class MyAccountPreferencesEntity   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="myAccountPreferencesId")
		private int myAccountPreferencesId;
		
		@Column(name="showPlanType")
		private boolean showPlanType;
		
		@Column(name="showPaymentDetails")
		private boolean showPaymentDetails;
		
		@Column(name="viewPlanPaymentTerms")
		private boolean viewPaymentTerms;
		
		@Column(name="viewPlanMonthlyPayments")
		private boolean viewPlanMonthlyPayments;
		
		@Column(name="viewPlanQuarterlyPayments")
		private boolean viewPlanQuarterlyPayments;
		
		@Column(name="viewPlanYearlyPayments")
		private boolean viewPlanYearlyPayments;
		
		@Column(name="viewPlanShowPanel")
		private boolean viewPlanShowPanel;
		
		@Column(name="viewRegName")
		private boolean viewRegName;
		
		@Column(name="viewRegUserName")
		private boolean viewRegUserName;
		
		@Column(name="viewRegEmail")
		private boolean viewRegEmail;
		
		@Column(name="viewRegShowPanel")
		private boolean viewRegShowPanel;
		
		@Column(name="viewRegRegisteredTo")
		private boolean viewRegRegisteredTo;
		
		@Column(name="editRegPassword")
		private boolean editRegPassword;
		
		@Column(name="viewRegLastEdited")
		private boolean viewRegLastEdited;
		
		
		
		
		
		@Column(name="editRegName")
		private boolean editRegName;
		
		@Column(name="editRegUserName")
		private boolean editRegUserName;
		
		@Column(name="editRegEmail")
		private boolean editRegEmail;
		
		@Column(name="editRegShowPanel")
		private boolean editRegShowPanel;
		
		@Column(name="editRegRegisteredTo")
		private boolean editRegRegisteredTo;
		
		@Column(name="editRegCloseAccount")
		private boolean editRegCloseAccount;
		

		public int getMyAccountPreferencesId() {
			return myAccountPreferencesId;
		}

		public void setMyAccountPreferencesId(int myAccountPreferencesId) {
			this.myAccountPreferencesId = myAccountPreferencesId;
		}

		public boolean isShowPlanType() {
			return showPlanType;
		}

		public void setShowPlanType(boolean showPlanType) {
			this.showPlanType = showPlanType;
		}

		public boolean isShowPaymentDetails() {
			return showPaymentDetails;
		}

		public void setShowPaymentDetails(boolean showPaymentDetails) {
			this.showPaymentDetails = showPaymentDetails;
		}

		public boolean isViewPaymentTerms() {
			return viewPaymentTerms;
		}

		public void setViewPaymentTerms(boolean viewPaymentTerms) {
			this.viewPaymentTerms = viewPaymentTerms;
		}

		public boolean isViewPlanMonthlyPayments() {
			return viewPlanMonthlyPayments;
		}

		public void setViewPlanMonthlyPayments(boolean viewPlanMonthlyPayments) {
			this.viewPlanMonthlyPayments = viewPlanMonthlyPayments;
		}

		public boolean isViewPlanQuarterlyPayments() {
			return viewPlanQuarterlyPayments;
		}

		public void setViewPlanQuarterlyPayments(boolean viewPlanQuarterlyPayments) {
			this.viewPlanQuarterlyPayments = viewPlanQuarterlyPayments;
		}

		public boolean isViewPlanYearlyPayments() {
			return viewPlanYearlyPayments;
		}

		public void setViewPlanYearlyPayments(boolean viewPlanYearlyPayments) {
			this.viewPlanYearlyPayments = viewPlanYearlyPayments;
		}

	
		public boolean isViewRegShowPanel() {
			return viewRegShowPanel;
		}

		public void setViewRegShowPanel(boolean viewRegShowPanel) {
			this.viewRegShowPanel = viewRegShowPanel;
		}

		public boolean isViewRegRegisteredTo() {
			return viewRegRegisteredTo;
		}

		public void setViewRegRegisteredTo(boolean viewRegRegisteredTo) {
			this.viewRegRegisteredTo = viewRegRegisteredTo;
		}

		public boolean isViewRegLastEdited() {
			return viewRegLastEdited;
		}

		public void setViewRegLastEdited(boolean viewRegLastEdited) {
			this.viewRegLastEdited = viewRegLastEdited;
		}

		public boolean isViewRegName() {
			return viewRegName;
		}

		public void setViewRegName(boolean viewRegName) {
			this.viewRegName = viewRegName;
		}

		public boolean isViewRegUserName() {
			return viewRegUserName;
		}

		public void setViewRegUserName(boolean viewRegUserName) {
			this.viewRegUserName = viewRegUserName;
		}

		public boolean isViewRegEmail() {
			return viewRegEmail;
		}

		public void setViewRegEmail(boolean viewRegEmail) {
			this.viewRegEmail = viewRegEmail;
		}

		public boolean isEditRegPassword() {
			return editRegPassword;
		}

		public void setEditRegPassword(boolean editRegPassword) {
			this.editRegPassword = editRegPassword;
		}

		public boolean isEditRegName() {
			return editRegName;
		}

		public void setEditRegName(boolean editRegName) {
			this.editRegName = editRegName;
		}

		public boolean isEditRegUserName() {
			return editRegUserName;
		}

		public void setEditRegUserName(boolean editRegUserName) {
			this.editRegUserName = editRegUserName;
		}

		public boolean isEditRegEmail() {
			return editRegEmail;
		}

		public void setEditRegEmail(boolean editRegEmail) {
			this.editRegEmail = editRegEmail;
		}

		public boolean isEditRegShowPanel() {
			return editRegShowPanel;
		}

		public void setEditRegShowPanel(boolean editRegShowPanel) {
			this.editRegShowPanel = editRegShowPanel;
		}

		public boolean isEditRegRegisteredTo() {
			return editRegRegisteredTo;
		}

		public void setEditRegRegisteredTo(boolean editRegRegisteredTo) {
			this.editRegRegisteredTo = editRegRegisteredTo;
		}

		public boolean isViewPlanShowPanel() {
			return viewPlanShowPanel;
		}

		public void setViewPlanShowPanel(boolean viewPlanShowPanel) {
			this.viewPlanShowPanel = viewPlanShowPanel;
		}

		public boolean isEditRegCloseAccount() {
			return editRegCloseAccount;
		}

		public void setEditRegCloseAccount(boolean editRegCloseAccount) {
			this.editRegCloseAccount = editRegCloseAccount;
		}

		
		
}
