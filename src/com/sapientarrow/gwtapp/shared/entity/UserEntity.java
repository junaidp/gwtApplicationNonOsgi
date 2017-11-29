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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

// user entity for saving users of this application
	@Entity

	@Table(name="user")
	public class UserEntity implements Serializable {
		
		public UserEntity(){}
		

		private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="userId")
		private int userId;
		
		@Column(name="username")
		private String userName;
		
		@Column(name="password")
		private String password;
		
		@Column(name="name")
		private String name;
		
		@Column(name="email")
		private String email;
		
		@Column(name="status")
		private int status;
		
		@Temporal(TemporalType.DATE)
		@Column(name="tokenGeneratedDate")
		private Date tokenGeneratedDate;
		
		@JoinColumn(name="myAccountId")
		@ManyToOne( fetch = FetchType.LAZY )
		private MyAccountEntity myAccountId;
		
		@Column(name="isAdmin")
		private boolean admin;
		
		@Column(name="token")
		private String token;
		
		@Transient
		private String userFetchStatus;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public MyAccountEntity getMyAccountId() {
			return myAccountId;
		}

		public void setMyAccountId(MyAccountEntity myAccountId) {
			this.myAccountId = myAccountId;
		}

		public boolean isAdmin() {
			return admin;
		}

		public void setAdmin(boolean isAdmin) {
			this.admin = isAdmin;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public Date getTokenGeneratedDate() {
			return tokenGeneratedDate;
		}

		public void setTokenGeneratedDate(Date tokenGeneratedDate) {
			this.tokenGeneratedDate = tokenGeneratedDate;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getUserFetchStatus() {
			return userFetchStatus;
		}

		public void setUserFetchStatus(String userFetchStatus) {
			this.userFetchStatus = userFetchStatus;
		}

	
}
