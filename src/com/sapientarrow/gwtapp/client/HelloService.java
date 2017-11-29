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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanDTO;
import com.sapientarrow.gwtapp.shared.dto.AnnotationsDTO;
import com.sapientarrow.gwtapp.shared.dto.BeanObjectDTO;
import com.sapientarrow.gwtapp.shared.dto.BindingsDTO;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountEntity;
import com.sapientarrow.gwtapp.shared.entity.NameSpaceEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface HelloService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	String addUser(UserEntity user) throws Exception;
	boolean verifyCaptcha(String challenge, String response)throws Exception;
	boolean verifySubscription(String email) throws Exception;
	UserEntity signIn(String userName, String password) throws Exception;
	String editUser(UserEntity user) throws Exception;
	ArrayList<String> readUploadedFiles() throws Exception;
	String fetchLogo() throws Exception;
	GlobalPreferencesEntity fetchGlobalPreferences() throws Exception;
	String updateGlobalPreferences(GlobalPreferencesEntity globalPreferencesEntity) throws Exception;
	String updateMyAccount (MyAccountEntity myAccountEntity)throws Exception;
	String logOut() throws Exception;
	String fetchUserStatus(String userName, String email) throws Exception;
	String inactivateAccount(String userName) throws Exception;
	String sendActivationEmailForgotPassword(String userName) throws Exception;
	boolean isLoggedInWithin20Mins(UserEntity user) throws Exception;
	UserEntity fetchNewUser(String createPasswordToken)throws Exception;
	String updatePassword(UserEntity user)throws Exception;
	String emailUserName(String email)throws Exception;
	ArrayList<UserEntity>fetchAllUsers()throws Exception;
	String updateUserAccount(UserEntity user)throws Exception;
	String closeAccount(UserEntity user) throws Exception;
	String fetchBeanJSON(String className, String reflectionName, String action) throws Exception;
	String generateJavaBean(AddedBeanDTO addedBeanDTO)throws Exception;
	String generateDynamicBean(AddedBeanDTO addedBeanDTO)throws Exception;
	ArrayList<String> fetchPackages()throws Exception;
	ArrayList<String> fetchClassesOfAllPackages()throws Exception;
	ArrayList<String> fetchStringClassesOfAllPackages()throws Exception;
	ArrayList<AnnotationsDTO>fetchAnnotations()throws Exception;
	String loadUploadedClass (String className)throws Exception;
	String editBeanOnPropertyChange(String selectedBeanName, TreeMap beanPropertiesMap) throws Exception;
	String editDynamicBeanOnPropertyChange(String selectedBeanName, TreeMap beanPropertiesMap) throws Exception;
	String fetchBeanObject(String beanName)throws Exception;
	String downloadBeanFieldsJson(String beanName) throws Exception;
	String loadBeansJsonAndCreateDynamicBeans()throws Exception;
	ArrayList<BeanObjectDTO> fetchAllBeansInDb()throws Exception;
	ArrayList<BindingsDTO> fetchAllBindings(String keyword)throws Exception;
	String saveBinding(BindingsDTO binding) throws Exception;
	String deleteBinding(int bindingId) throws Exception;
	String deleteMultipleBindings(ArrayList<Integer> bndingIds) throws Exception;
	ArrayList<NameSpaceEntity>fetchNameSpaces()throws Exception;
	String overriteBeansLayoutClass(int beanId)throws Exception;
	
}
