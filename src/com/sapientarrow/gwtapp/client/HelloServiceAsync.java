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
package com.sapientarrow.gwtapp.client;

import java.util.ArrayList;
import java.util.TreeMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanDTO;
import com.sapientarrow.gwtapp.shared.dto.AnnotationsDTO;
import com.sapientarrow.gwtapp.shared.dto.BeanObjectDTO;
import com.sapientarrow.gwtapp.shared.dto.BindingsDTO;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountEntity;
import com.sapientarrow.gwtapp.shared.entity.NameSpaceEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface HelloServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void addUser(UserEntity user, AsyncCallback<String> callback);
	void verifyCaptcha(String challenge, String response, AsyncCallback<Boolean> callback);
	void verifySubscription(String email, AsyncCallback<Boolean> callback);
	void signIn(String userName, String password, AsyncCallback<UserEntity> callback);
	void editUser(UserEntity user, AsyncCallback<String> callback);
	void readUploadedFiles( AsyncCallback<ArrayList<String>> callback);
	void fetchLogo(AsyncCallback<String> callback);
	void fetchGlobalPreferences(AsyncCallback<GlobalPreferencesEntity> callback);
	void updateGlobalPreferences(GlobalPreferencesEntity globalPreferencesEntity, AsyncCallback<String> callback);
	void updateMyAccount(MyAccountEntity myAccountEntity, AsyncCallback<String> callback);
	void logOut(AsyncCallback<String> callback);
	void fetchUserStatus(String userName, String email, AsyncCallback<String> callback);
	void inactivateAccount (String userName, AsyncCallback<String> callback);
	void sendActivationEmailForgotPassword(String userName,AsyncCallback<String> callback);
	void isLoggedInWithin20Mins(UserEntity user,AsyncCallback<Boolean> asyncCallback);
	void fetchNewUser(String createPasswordToken, AsyncCallback<UserEntity> asyncCallback);
	void updatePassword(UserEntity user, AsyncCallback<String> asyncCallback);
	void emailUserName(String email, AsyncCallback<String> asyncCallback);
	void fetchAllUsers(AsyncCallback<ArrayList<UserEntity>> asyncCallback);
	void updateUserAccount(UserEntity user, AsyncCallback<String> asyncCallback);
	void closeAccount(UserEntity user, AsyncCallback<String> asyncCallback);
	void fetchBeanJSON(String className, String reflectionName, String action, AsyncCallback<String> asyncCallback);
	void generateJavaBean(AddedBeanDTO addedBeanDTO, AsyncCallback<String> asyncCallback);
	void generateDynamicBean(AddedBeanDTO addedBeanDTO, AsyncCallback<String> asyncCallback);
	void fetchPackages(AsyncCallback<ArrayList<String>> asyncCallback);
	void fetchClassesOfAllPackages(AsyncCallback<ArrayList<String>> asyncCallback);
	void fetchStringClassesOfAllPackages(AsyncCallback<ArrayList<String>> asyncCallback);
	void fetchAnnotations(AsyncCallback<ArrayList<AnnotationsDTO>> asyncCallback);
	void loadUploadedClass(String className, AsyncCallback<String> asyncCallback);
	void editBeanOnPropertyChange(String selectedBeanName, TreeMap beanPropertiesMap, AsyncCallback<String> asyncCallback);
	void editDynamicBeanOnPropertyChange(String selectedBeanName, TreeMap beanPropertiesMap, AsyncCallback<String> asyncCallback);
	void fetchBeanObject(String beanName, AsyncCallback<String> asyncCallback);
	void downloadBeanFieldsJson(String beanName, AsyncCallback<String> asyncCallback);
	void loadBeansJsonAndCreateDynamicBeans(AsyncCallback<String> asyncCallback);
	void fetchAllBeansInDb(AsyncCallback<ArrayList<BeanObjectDTO>> asyncCallback);
	void fetchAllBindings(String keyword, AsyncCallback<ArrayList<BindingsDTO>> asyncCallback);
	void saveBinding(BindingsDTO binding, AsyncCallback<String> asyncCallback);
	void deleteBinding(int bindingId,  AsyncCallback<String> asyncCallback);
	void deleteMultipleBindings(ArrayList<Integer> bindingIds, AsyncCallback<String> asyncCallback);
	void fetchNameSpaces(AsyncCallback<ArrayList<NameSpaceEntity>> asyncCallback);
	void overriteBeansLayoutClass(int beanId, AsyncCallback<String> asyncCallback);
		
}
