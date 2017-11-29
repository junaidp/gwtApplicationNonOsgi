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
package com.sapientarrow.gwtapp.shared;

import java.util.ArrayList;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.widgets.AddBindingWidget;

// This class is a Field Verifier for Bindings , which will check if any field of Bindings have some wrong value
public class BindingsFieldVerifier  {
	
	UIFieldsErrors errors = new UIFieldsErrors();
	
	public boolean validate(AddBindingWidget display){
		boolean result = true;
		
		errors.clear();
		validateBindingName(display.getTxtBindingName().getText());
		validateNameSpace(display.getListNameSpace().getDisplayValue());
		
		if (errors.getErrors().size() > 0){
			result = false;
		}
		
		return result;
	}

	private void validateBindingName(String input) {
		if(Character.isDigit(input.charAt(0)) || input.trim().length()<=0 || input.contains(" ")){
			errors.add(new UIFieldsError(ApplicationConstants.BINDING_NAME_NOT_VALID));
		}
		
	}
	
	private void validateNameSpace(String input) {
		if(input.trim().length()<=0 || Character.isDigit(input.charAt(0)) || input.contains(" ")){
			errors.add(new UIFieldsError(ApplicationConstants.NAMESPACE_NAME_NOT_VALID));
		}
		
	}
	
	
	
	public UIFieldsErrors getErrors() {
		return errors;
	}
	
	public SafeHtml getErrorMessages(){
		
		ArrayList<UIFieldsError> listUIErrors = errors.getErrors();
			SafeHtmlBuilder builder = new SafeHtmlBuilder();
		
		for (int i = 0; i < listUIErrors.size(); i++){
			builder.appendEscaped(listUIErrors.get(i).getErrorMessage()).appendHtmlConstant("<br/>");
		}

		return builder.toSafeHtml();
	}

}

//
//package com.zrsol.cricsite.shared;
//
//import java.util.ArrayList;
//
//import com.google.gwt.safehtml.shared.SafeHtml;
//import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
//import com.google.gwt.user.client.ui.ListBox;
//
//
//
//public class CreateFanPageFieldVerifier {
//
//	UIFieldsErrors errors = new UIFieldsErrors();
//	
//
//	
//	public boolean validate(AddTeamWidgetIFace addTeamWidget){
//		boolean result = true;
//		
//		errors.clear();
//		isValidFirstName(addTeamWidget.getTxtTeamName().getText());
//		isValidEmail(addTeamWidget.getTxtEmail().getText());
//		isValidCountry(addTeamWidget.getComboCountry());
//		isValidStateProv(addTeamWidget.getComboState());
//		isValidCity(addTeamWidget.getComboCity());
//	
//		if (errors.getErrors().size() > 0){
//			result = false;
//		}
//		
//		return result;
//	}
//	
//	public boolean isValidCountry(ListBox comboCountry){
//		boolean result = false;
//		
//		if (comboCountry != null){
//			if (comboCountry.getItemCount() > 0){
//				result = true;
//			}else{
//				errors.add(new UIFieldsError(SignupConstants.COUNTRYERROR));
//			}
//		}else{
//			errors.add(new UIFieldsError(SignupConstants.COUNTRYERROR));
//		}
//		
//		return result;
//	}
//	
//	public boolean isValidStateProv(ListBox comboStateProv){
//		boolean result = false;
//		
//		if (comboStateProv != null){
//			if (comboStateProv.getItemCount() > 0){
//				result = true;
//			}else{
//				errors.add(new UIFieldsError(SignupConstants.STATEERROR));
//			}
//		}else{
//			errors.add(new UIFieldsError(SignupConstants.STATEERROR));
//		}
//		
//		return result;
//	}
//	
//	public boolean isValidCity(ListBox comboCity){
//		boolean result = false;
//		
//		if (comboCity != null){
//			if (comboCity.getItemCount() > 0){
//				result = true;
//			}else{
//				errors.add(new UIFieldsError(SignupConstants.CITYERROR));
//			}
//		}else{
//			errors.add(new UIFieldsError(SignupConstants.CITYERROR));
//		}
//		
//		return result;
//	}
//	
//	public boolean isValidEmail(String email){
//		boolean result = true;
//		
//		  if ((email == null) || (email.trim().length() == 0)) {
//				errors.add(new UIFieldsError(SignupConstants.EMAILERROR));
//				result = false;
//			}
//		  else if(commonVerifier.hasSpecialChars(email))
//			{
//				errors.add(new UIFieldsError(SignupConstants.EMAILSPECIAL + " " + Constants.PROHIBITED_CHARACTERS));
//				result = false;
//			}
//		  else if(commonVerifier.isValidEmail(email))
//			{
//				errors.add(new UIFieldsError(SignupConstants.INVALIDEMAIL));
//				result = false;
//			}
//		  
//		  return result;
//	}
//	
//	public boolean isValidFirstName(String teamName){
//		boolean result = true;
//		
//		  if ((teamName == null) || (teamName.trim().length() == 0)) {
//				errors.add(new UIFieldsError(PlayerConstants.FNAMEERROR));
//				result = false;
//			}
//		  else if(commonVerifier.hasSpecialChars(teamName))
//			{
//				errors.add(new UIFieldsError(PlayerConstants.FNAMESPECIAL + " " + Constants.PROHIBITED_CHARACTERS));
//				result = false;
//			}
//		  else if(commonVerifier.isAllNumerics(teamName))
//			{
//				errors.add(new UIFieldsError(PlayerConstants.FNAMENUMERIC));
//				result = false;
//			}
//		 
//		  return result;
//	}
//	
//	public boolean isValidLastName(String teamName){
//		boolean result = true;
//		
//		  if ((teamName == null) || (teamName.trim().length() == 0)) {
//				errors.add(new UIFieldsError(PlayerConstants.LNAMEERROR));
//				result = false;
//			}
//		  else if(commonVerifier.hasSpecialChars(teamName))
//			{
//				errors.add(new UIFieldsError(PlayerConstants.LNAMESPECIAL + " " + Constants.PROHIBITED_CHARACTERS));
//				result = false;
//			}
//		  else if(commonVerifier.isAllNumerics(teamName))
//			{
//				errors.add(new UIFieldsError(PlayerConstants.LNAMENUMERIC));
//				result = false;
//			}
//		 
//		  return result;
//	}
//	
//	public boolean isValidLogo(String logo){
//		boolean result = true;
//		
//		if ((logo == null) || (logo.trim().length() == 0)) {
//				errors.add(new UIFieldsError(TeamConstants.TEAMLOGOERROR));
//				result = false;
//		}else{
//			int beginIndex = logo.indexOf('.');
//			if (beginIndex >= 0){
//				String fileExt = logo.substring(beginIndex + 1, beginIndex+4);
//				if (!isValidPicType(fileExt)){
//					errors.add(new UIFieldsError("Upload picture in the following formats " + Constants.VALID_FILETYPES));
//					result = false;
//				}
//			}else{
//				errors.add(new UIFieldsError("Upload picture in the following formats " + Constants.VALID_FILETYPES));
//				result = false;
//			}
//		}
//	 
//	  return result;
//	}
//	
//	public boolean isValidPicType (String fileExt){
//		boolean result = false;
//		
//		if (Constants.VALID_FILETYPES.contains(fileExt)){
//			result = true;
//		}
//		
//		return result;
//	}
//	
//	public boolean isValidTeamDesc(String teamDesc){
//		boolean result = true;
//		
//		  if ((teamDesc == null) || (teamDesc.trim().length() == 0)) {
//				errors.add(new UIFieldsError(TeamConstants.TEAMDESCERROR));
//				result = false;
//			}
//		  else if(commonVerifier.hasSpecialChars(teamDesc))
//			{
//				errors.add(new UIFieldsError(TeamConstants.TEAMDESCSPECIAL + " " + Constants.PROHIBITED_CHARACTERS));
//				result = false;
//			}
//		  else if(commonVerifier.isAllNumerics(teamDesc))
//			{
//				errors.add(new UIFieldsError(TeamConstants.TEAMDESCNUMERIC));
//				result = false;
//			}
//		 
//		  return result;
//	}
//
//	public UIFieldsErrors getErrors() {
//		return errors;
//	}
//	
//	public SafeHtml getErrorMessages(){
//		
//		ArrayList<UIFieldsError> listUIErrors = errors.getErrors();
//		UIFieldsError uiError;
//		SafeHtmlBuilder builder = new SafeHtmlBuilder();
//		
//		for (int i = 0; i < listUIErrors.size(); i++){
//			builder.appendEscaped(listUIErrors.get(i).getErrorMessage()).appendHtmlConstant("<br/>");
//		}
//
//		return builder.toSafeHtml();
//	}
//}
//
//
