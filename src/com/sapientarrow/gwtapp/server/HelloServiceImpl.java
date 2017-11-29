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
package com.sapientarrow.gwtapp.server;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sapientarrow.gwtapp.client.HelloService;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.database.MyRdbHelper;
import com.sapientarrow.gwtapp.org.hibernate.DynHelper;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanDTO;
import com.sapientarrow.gwtapp.shared.dto.AnnotationsDTO;
import com.sapientarrow.gwtapp.shared.dto.BeanObjectDTO;
import com.sapientarrow.gwtapp.shared.dto.BindingsDTO;
import com.sapientarrow.gwtapp.shared.entity.BeanObjects;
import com.sapientarrow.gwtapp.shared.entity.BindingsEntity;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountEntity;
import com.sapientarrow.gwtapp.shared.entity.NameSpaceEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class HelloServiceImpl extends RemoteServiceServlet implements
HelloService {


	public String greetServer(String input) throws IllegalArgumentException {
		return "";
	}

	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	MyRdbHelper rdbHelper = (MyRdbHelper) ctx.getBean("ManagerApp");
	
	HttpSession session ;

	@Override
	public String addUser(UserEntity user) throws Exception {

		if(isValidEmailAddress(user.getEmail())){
			return rdbHelper.addUser(user);
		}else{
			return ApplicationConstants.INVALID_EMAIL;
		}
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean verifyCaptcha(String challenge, String response)
			throws Exception {
		ReCaptcha r = ReCaptchaFactory.newReCaptcha("6LcEKg4TAAAAAFADmX5mrhcKkaeNMcxh7k5CiQ2K",
				"6LcEKg4TAAAAAPXmd7B6Oskr8N2ywB-4ufp4Hnx5", true);
		boolean result =  r.checkAnswer(
				getThreadLocalRequest().getRemoteAddr().toString(), challenge,
				response).isValid();
		return result;
	}

	@Override
	public boolean verifySubscription(String email) throws Exception {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data5PU");
//		DynHelper emfHelper = (DynHelper) ctx.getBean("ManagerEmf");
//		emfHelper.createDynamicBean("com.halcyonpro.gwtapp.shared.dto.DummyBean");
//		DynHelper emfHelper = new DynHelper();
//		emfHelper.createDynamicBean("com.halcyonpro.gwtapp.shared.dto.DummyBean");
		return rdbHelper.verifySubscription(email);
	}

	@Override
	public UserEntity signIn(String userName, String password)
			throws Exception {
		UserEntity user =  rdbHelper.signIn(userName, password);
		if(user!=null)
		{
			session=getThreadLocalRequest().getSession(true);
			session.setAttribute("userId", user.getUserId());
		}
		return user;
	}

	@Override
	public String editUser(UserEntity user) throws Exception {
		return rdbHelper.addUser(user);
	}

	public ArrayList<String> readUploadedFiles(){
		String root = getServletContext().getRealPath("/");
		ArrayList<String> fileNames = new ArrayList<String>();
		File folder = new File(root + "/fileuploads");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			fileNames.add(listOfFiles[i].getName());
		}
		return fileNames;
	}

	public String fetchLogo(){
		String root = getServletContext().getRealPath("/");
		//		File folder = new File(root + "/users"+"/"+userId+"/logo");
		File folder = new File(root + "/logo");
		String logoUrl = "";
		File[] logos = folder.listFiles();
		if(logos!=null){
			for (int i = 0; i < logos.length; i++) {
				logoUrl = "/logo"+"/"+logos[0].getName();
			}
		}
		return logoUrl;

	}

	@Override
	public GlobalPreferencesEntity fetchGlobalPreferences() throws Exception {
		return rdbHelper.fetchGlobalPreferences();
	}

	@Override
	public String updateGlobalPreferences(
			GlobalPreferencesEntity globalPreferencesEntity) throws Exception {
		return rdbHelper.updateGlobalPreferences(globalPreferencesEntity);
	}

	@Override
	public String updateMyAccount(MyAccountEntity myAccountEntity)
			throws Exception {
		return rdbHelper.updateMyAccount(myAccountEntity);
	}

	@Override
	public String logOut() throws Exception {
		session=getThreadLocalRequest().getSession(true);
		session.invalidate();
		return "loggedOut";
	}

	@Override
	public String fetchUserStatus(String userName, String email) throws Exception {
		return rdbHelper.fetchUserStatus(userName, email);
	}

	@Override
	public String inactivateAccount(String userName) throws Exception {
		return rdbHelper.inactivateAccount(userName);
	}

	@Override
	public String sendActivationEmailForgotPassword(String userName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLoggedInWithin20Mins(UserEntity user) throws Exception {
		return rdbHelper.isLoggedInWithin20Mins(user);
	}

	@Override
	public UserEntity fetchNewUser(String passwordToken) throws Exception {
		return rdbHelper.fetchNewUser(passwordToken);
	}

	@Override
	public String updatePassword(UserEntity user) throws Exception {
		return rdbHelper.updatePassword(user);
	}

	@Override
	public String emailUserName(String email) throws Exception {
		return rdbHelper.emailUserName(email);
	}

	@Override
	public ArrayList<UserEntity> fetchAllUsers() throws Exception {
		return rdbHelper.fetchAllUsers();
	}

	@Override
	public String updateUserAccount(UserEntity user) throws Exception {
		return rdbHelper.updateUserAccount(user);
	}

	@Override
	public String closeAccount(UserEntity user) throws Exception {
		return rdbHelper.closeAccount(user);
	}

	@Override
	public String fetchBeanJSON(String className, String reflectionName, String action) throws Exception {
		FilesCreationHelper filesCreationHelper = new FilesCreationHelper();
		String json =  filesCreationHelper.fetchBeanJSON(className, reflectionName, action);
		session=getThreadLocalRequest().getSession(true);
		session.setAttribute("json", json);
		return json;
	}

	@Override
	public String generateJavaBean(AddedBeanDTO addedBeanDTO) throws Exception {
		
		FilesCreationHelper filesCreationHelper = new FilesCreationHelper();
		return filesCreationHelper.generateBean(addedBeanDTO);
		
	}
	
	@Override
	public String generateDynamicBean(AddedBeanDTO addedBeanDTO) throws Exception {
		
		DynHelper dynHelper = new DynHelper();
		return dynHelper.createDynamicBeanWithoutData(addedBeanDTO);
		
	}

	@Override
	public ArrayList<String> fetchPackages() throws Exception {
		Package[] packageslist = Package.getPackages();

		ArrayList<String> packagesList = new ArrayList<String>();
		for(int i=0; i< packageslist.length; i++){
						if(packageslist[i].getName().startsWith(ApplicationConstants.DEFAULT_PACKAGE)){
//			if(packageslist[i].getName().startsWith("com.helloworld.client")){
				packagesList.add(packageslist[i].getName());
			}
		}
		return packagesList	;
	}


	//	Reflections reflections = new Reflections(packageslist[i].getName());
	//
	//	 Set<Class<? extends Object>> allClasses = 
	//	     reflections.getSubTypesOf(Object.class);
	//	Class[] allClasses = getClasses(packageslist[i].getName());
	//	 System.out.println(allClasses);

	private static Class[] getClasses(String packageName)
			throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	private static ArrayList<String> getClassesString(String packageName)
			throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<String> classes = new ArrayList<String>();
		for (File directory : dirs) {
			classes.addAll(findClassesString(directory, packageName));
		}
		return classes;
	}


	private static List<Class> findClasses(File directory, String packageName) throws Exception {

		List<Class> classes = new ArrayList<Class>();

		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				try{
						
					classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				
				}catch(Exception ex){
					System.out.println(ex);
					throw new Exception(ex); 
				}
			}

		}

		return classes;

	}

	private static List<String> findClassesString(File directory, String packageName) throws Exception {

		List<String> classes = new ArrayList<String>();

		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClassesString(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				try{
					classes.add(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				}catch(Exception ex){
					System.out.println(ex);
					throw new Exception(ex);
				}
			}

		}

		return classes;

	}

	@Override
	public ArrayList<String> fetchStringClassesOfAllPackages() throws Exception {
		ArrayList<String> allPackages = fetchPackages();
		ArrayList<String> allClasses = new ArrayList<String>();
		for(int i=0;i< allPackages.size(); i++){
			//			Class[] packageClasses = getClasses(allPackages.get(i));
			ArrayList<String> packageClasses = getClassesString(allPackages.get(i));
			for(int j=0; j< packageClasses.size(); j++){
				if(! allClasses.contains(packageClasses.get(j))){
					allClasses.add(packageClasses.get(j));
				}
			}
		}

		return allClasses;
	}

	@Override
	public ArrayList<String> fetchClassesOfAllPackages() throws Exception {
		ArrayList<String> allPackages = fetchPackages();
		ArrayList<String> allClasses = new ArrayList<String>();
		for(int i=0;i< allPackages.size(); i++){
			//		Class[] packageClasses = getClasses(allPackages.get(i));
			Class[] packageClasses = getClasses(allPackages.get(i));
			for(int j=0; j< packageClasses.length; j++){
				if(! allClasses.contains(packageClasses[j].getName()+"::"+ packageClasses[j].getSimpleName())){
					allClasses.add(packageClasses[j].getName()+"::"+ packageClasses[j].getSimpleName());
				}
			}
		}

		return allClasses;
	}

	public ArrayList<AnnotationsDTO> fetchAnnotations() throws Exception{
		Reflections reflectionsHibernate = new Reflections("org.hibernate.annotations");
		Reflections reflectionsJPA = new Reflections("javax.persistence");
		Reflections reflectionsSpring = new Reflections("org.springframework");
		Reflections reflectionsCDI = new Reflections("javax.enterprise");
		Reflections reflectionsLang = new Reflections("java.lang");

		ArrayList<AnnotationsDTO> listAnnotationsDTO = new ArrayList<AnnotationsDTO>();
		Set<Class<? extends Annotation>> annotationsHibernate = 
				reflectionsHibernate.getSubTypesOf(Annotation.class);

		Set<Class<? extends Annotation>> annotationsJPA = 
				reflectionsJPA.getSubTypesOf(Annotation.class);

		Set<Class<? extends Annotation>> annotationsSpring = 
				reflectionsSpring.getSubTypesOf(Annotation.class);

		Set<Class<? extends Annotation>> annotationsCDI = 
				reflectionsCDI.getSubTypesOf(Annotation.class);

		Set<Class<? extends Annotation>> annotationsLang = 
				reflectionsLang.getSubTypesOf(Annotation.class);


		Iterator iterLang = annotationsLang.iterator();
		while (iterLang.hasNext()) {
			addAnnotation(listAnnotationsDTO, iterLang);
		}

		Iterator iterHib = annotationsHibernate.iterator();
		while (iterHib.hasNext()) {
			addAnnotation(listAnnotationsDTO, iterHib);
		}
		Iterator iter = annotationsHibernate.iterator();
		while (iter.hasNext()) {
			addAnnotation(listAnnotationsDTO, iter);
		}
		Iterator iterJPA = annotationsJPA.iterator();
		while (iterJPA.hasNext()) {
			addAnnotation(listAnnotationsDTO, iterJPA);
		}
		Iterator iterSpring = annotationsSpring.iterator();
		while (iterSpring.hasNext()) {
			addAnnotation(listAnnotationsDTO, iterSpring);
		}
		Iterator iterCDI = annotationsCDI.iterator();
		while (iterCDI.hasNext()) {
			addAnnotation(listAnnotationsDTO, iterCDI);
		}
		return listAnnotationsDTO;
	}

	private void addAnnotation(ArrayList<AnnotationsDTO> listAnnotationsDTO,
			Iterator iter) {
		Class anot  = (Class) iter.next();
		String annotation = anot.getSimpleName();
		String annotationImport = anot.getName();
		AnnotationsDTO hibAnnotationsDTO = new AnnotationsDTO();
		hibAnnotationsDTO.setName(annotation);
		hibAnnotationsDTO.setImportClass(annotationImport);
		listAnnotationsDTO.add(hibAnnotationsDTO);
	}

	@Override
	public String loadUploadedClass(String className) throws Exception {
		 Class.forName("com.helloworld.server.LogoUploadServlet").newInstance();
		return "";
	}

	@Override
	public String editBeanOnPropertyChange(String selectedBeanName, TreeMap beanPropertiesMap) throws Exception {
		
		FilesCreationHelper filesCreationHelper = new FilesCreationHelper();
//		return filesCreationHelper.editBeanOnPropertyChange(selectedBeanName, beanPropertiesMap);
		Object obj =  filesCreationHelper.editBeanOnPropertyChange(selectedBeanName, beanPropertiesMap);
		 return obj.toString();
	}
	
	@Override
	public String editDynamicBeanOnPropertyChange(String selectedBeanName, TreeMap beanPropertiesMap) throws Exception {
		
		DynHelper dynHelper = new DynHelper();
		return dynHelper.createDynamicBeanWithData(beanPropertiesMap, selectedBeanName);
		 
	}

	@Override
	public String fetchBeanObject(String beanName) throws Exception {
		return rdbHelper.fetchBeanObject(beanName);
		
	}

	@Override
	public String downloadBeanFieldsJson(String beanName) throws Exception {
		FilesCreationHelper filesCreationHelper = new FilesCreationHelper();
		return filesCreationHelper.fetchBeanStructureJson(beanName);
	}

	@Override
	public String loadBeansJsonAndCreateDynamicBeans() throws Exception {
		return rdbHelper.loadBeansJsonAndCreateDynamicBeans();
	}

	@Override
	public ArrayList<BeanObjectDTO> fetchAllBeansInDb() throws Exception {
		return rdbHelper.fetchAllBeansInDb();
	}

	@Override
	public ArrayList<BindingsDTO> fetchAllBindings(String keyword) throws Exception {
		return rdbHelper.fetchAllBindings(keyword);
	}

	@Override
	public String saveBinding(BindingsDTO binding) throws Exception {
		return rdbHelper.saveBinding(binding);
	}

	@Override
	public String deleteBinding(int bindingId) throws Exception {
		return rdbHelper.deleteBinding(bindingId);
	}

	@Override
	public String deleteMultipleBindings(ArrayList<Integer> bndingIds)
			throws Exception {
		return rdbHelper.deleteMultipleBindings(bndingIds);
	}

	@Override
	public ArrayList<NameSpaceEntity> fetchNameSpaces() throws Exception {
		return rdbHelper.fetchNameSpaces();
	}

	@Override
	public String overriteBeansLayoutClass(int beanId) throws Exception {
		try{
			String root = getServletContext().getRealPath("/");
            File xmlFile = new File(root+"/bindingBeans/"+beanId+"/"+ApplicationConstants.UPLOADED_VIEWS__BINDING_NAME+".ui.xml");
            File javaFile = new File(root+"/bindingBeans/"+beanId+"/"+ApplicationConstants.UPLOADED_VIEWS__BINDING_NAME+".java");
            	File path = createFilePath(ApplicationConstants.UPLOADED_VIEWS_PACKAGE);

            	if(xmlFile.exists()){
            	Files.copy(Paths.get(xmlFile.toString()), Paths.get(path+"/"+xmlFile.getName()), StandardCopyOption.REPLACE_EXISTING);
            	}
            	Files.copy(Paths.get(javaFile.toString()), Paths.get(path+"/"+javaFile.getName()), StandardCopyOption.REPLACE_EXISTING);

	    	    return "file overrite successfully";
	    	}catch(Exception e){
	    		
	    		throw new Exception( ApplicationConstants.NO_UI_CLASS_FOUND);
	    	}
	}
	
	private File createFilePath(String myPath) {

		String dir = System.getProperty("user.dir");
		int slashIndex = dir.lastIndexOf("\\");
		dir = dir.substring(0, slashIndex+1);
		String packageDir = myPath.replace(".","\\");
//		dir =dir +"src\\"+ packageDir;
		dir =dir +ApplicationConstants.TOMCAT_PATH+ packageDir;
		
		File myPackage    = new File(dir);
		return myPackage;
	}

	
}
