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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Entity class for Global Preferences
@Table(name="globalprefrences")

	@Entity
	public class GlobalPreferencesEntity   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="globalPrefrencesId")
		private int globalPrefrencesId;
		
				
		@JoinColumn(name="myAccountPreferencesId")
		@ManyToOne( fetch = FetchType.LAZY )
		private MyAccountPreferencesEntity myAccountPreferencesId;
		
		@Column(name="panelTypeDashboard")
		private boolean panelTypeDashboard;
		
		@Column(name="panelTypeAccordion")
		private boolean panelTypeAccordion;
		
		@Column(name="panelTypePortlet")
		private boolean panelTypePortlet;
		
		@Column(name="logoUrl")
		private String logoUrl;
		
		@Column(name="logoHeight")
		private int logoHeight;
		
		@Column(name="logoWidth")
		private int logoWidth;

		public int getGlobalPrefrencesId() {
			return globalPrefrencesId;
		}

		public void setGlobalPrefrencesId(int globalPrefrencesId) {
			this.globalPrefrencesId = globalPrefrencesId;
		}

		public MyAccountPreferencesEntity getMyAccountPreferencesId() {
			return myAccountPreferencesId;
		}

		public void setMyAccountPreferencesId(
				MyAccountPreferencesEntity myAccountPreferencesId) {
			this.myAccountPreferencesId = myAccountPreferencesId;
		}

		public boolean isPanelTypeDashboard() {
			return panelTypeDashboard;
		}

		public void setPanelTypeDashboard(boolean panelTypeDashboard) {
			this.panelTypeDashboard = panelTypeDashboard;
		}

		public boolean isPanelTypeAccordion() {
			return panelTypeAccordion;
		}

		public void setPanelTypeAccordion(boolean panelTypeAccordion) {
			this.panelTypeAccordion = panelTypeAccordion;
		}

		public boolean isPanelTypePortlet() {
			return panelTypePortlet;
		}

		public void setPanelTypePortlet(boolean panelTypePortlet) {
			this.panelTypePortlet = panelTypePortlet;
		}

		public String getLogoUrl() {
			return logoUrl;
		}

		public void setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
		}

		public int getLogoHeight() {
			return logoHeight;
		}

		public void setLogoHeight(int logoHeight) {
			this.logoHeight = logoHeight;
		}

		public int getLogoWidth() {
			return logoWidth;
		}

		public void setLogoWidth(int logoWidth) {
			this.logoWidth = logoWidth;
		}

//		public HeaderPreferencesEntity getHeaderMenuPreferencesId() {
//			return headerMenuPreferencesId;
//		}
//
//		public void setHeaderMenuPreferencesId(
//				HeaderPreferencesEntity headerMenuPreferencesId) {
//			this.headerMenuPreferencesId = headerMenuPreferencesId;
//		}

		
}
