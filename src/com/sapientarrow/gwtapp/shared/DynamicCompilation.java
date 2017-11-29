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

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.util.ClassUtils;

import com.sapientarrow.gwtapp.client.view.ApplicationConstants;

// This class creates the Dynamic Beans and wrtie them to file
public class DynamicCompilation {

	public DynamicCompilation(String className, String reflectedClassName, String action) throws Exception {
		// create the source
		//		File sourceFile   = new File("C:\\Users\\Junaid\\git\\gwtapplication\\src\\com\\helloworld\\shared\\beans"+reflectedClassName+".java");
		int indClass = className.lastIndexOf(".");
		String myPath = className.substring(0,indClass);
		String dir = System.getProperty("user.dir");
		int index = dir.lastIndexOf("\\");
		dir = dir.substring(0, index);
		String packageDir = myPath.replace(".","\\");
		
//		dir = dir+"\\src\\"+packageDir+"\\"+reflectedClassName+".java";
		dir = dir+ApplicationConstants.TOMCAT_PATH+packageDir+"\\"+reflectedClassName+".java";
		File sourceFile   = new File(dir);
		FileWriter writer = new FileWriter(sourceFile);
		Class myClass = Class.forName(className);
//		Class myClass = Class.forName("com.helloworld.client.myy.My");
		Class[] listofImplements = myClass.getInterfaces();
		myClass.getSimpleName();

		Method[] method = myClass.getDeclaredMethods();
		Field[] field = myClass.getDeclaredFields();
		myClass.getModifiers();
		


		//////////////Crate Package and class/////////////////
		StringBuffer sb = new StringBuffer("");
		//		sb.append("package com.helloworld.shared.beans; \n \n");
		sb.append("package "+ myPath+"; \n \n");
		sb.append("import java.util.*; \n");
//		if(action.equals(ApplicationConstants.FIELD_EDITORS_CREATION)){
//			sb.append("import com.google.gwt.uibinder.client.UiBinder; \n");
//			sb.append("import com.google.gwt.user.client.ui.Composite; \n");
//			sb.append("import com.google.gwt.user.client.ui.Widget; \n");
//			sb.append("import com.google.gwt.core.client.GWT; \n");
//		}
		sb.append("public class "+reflectedClassName );
//		if(action.equals(ApplicationConstants.FIELD_EDITORS_CREATION))sb.append(" extends Composite ");
		if(listofImplements.length>0){
			sb.append(" implements ");
		}
		//Add implements...and their imports.
		for(int i=0;i< listofImplements.length; i++){
			sb.append(listofImplements[i].getSimpleName()+"");
			int ind = sb.indexOf("public");
			sb.insert(ind, "\n");
			sb.insert(ind, "import "+ listofImplements[i].getName()+";");
			sb.append("\n");
		}
		sb.append("{ \n");
		sb.append("\n");

//		if(action.equals(ApplicationConstants.FIELD_EDITORS_CREATION)){
//
//			sb.append("private static "+reflectedClassName+"UiBinder uiBinder = GWT.create("+reflectedClassName+"UiBinder.class); \n");
//			sb.append("interface "+ reflectedClassName+"UiBinder extends UiBinder<Widget," +reflectedClassName +" > { \n "
//					+ "} \n \n");
//		}

		//////////////Create Constructor//////////////////////
		Constructor[] constructors = myClass.getConstructors();
		for(int i=0; i< constructors.length; i++){
			sb.append(getModifer(constructors[i].getModifiers())+" ");
			sb.append(reflectedClassName+"(");

			/////////////Create Constructor Parameters////////////////////////
			Class[] parameters = constructors[i].getParameterTypes();
			for(int j=0; j<parameters.length; j++){
				if(j>0){
					sb.append(", "+parameters[j].getSimpleName()+" x"+j);
				}else{
					sb.append(parameters[j].getSimpleName()+" x"+j);

				}
			}
			sb.append(") {");
			sb.append("\n");
			sb.append("\n");
//			if(action.equals(ApplicationConstants.FIELD_EDITORS_CREATION) && i==0){
//				sb.append("initWidget(uiBinder.createAndBindUi(this)); \n");
//			}
			sb.append("}");
			sb.append("\n");

		}

		sb.append("\n");
		////////////////Create Fields/////////////////////
		for(int i=0; i< field.length; i++){
			sb.append(getModifer(field[i].getModifiers())+" ");
			sb.append(field[i].getType().getSimpleName()+" ");
			sb.append(field[i].getName()+"");
			if(ClassUtils.isPrimitiveOrWrapper(field[i].getType())){
				field[i].setAccessible(true);
				try{
					if(field[i].get(null)!=null){
//						sb.append("= "+ field[i].get(null));
						sb.append(" = "+ 1L);  
					}
				}catch(Exception ex){

				}

			}


			sb.append(";");
			sb.append("\n");

			////////////////CHECK if its a Declared bean and then add its imports and create childs///////
				if(! field[i].getType().getName().startsWith("java.") && implementsSerializable( field[i].getType().getInterfaces())
						&& allFieldsPrivate(field[i].getType())){//Check if its a custom bean.
					int ind = sb.indexOf("public");
					sb.insert(ind, "\n");
					sb.insert(ind, "import "+ field[i].getType().getName()+";");
					sb.append("\n");
					if(!action.equals(ApplicationConstants.FIELD_EDITORS_CREATION)){
						
					reflectedClassName = reflectedClassName+"Child"+i;
					new DynamicCompilation(field[i].getType().getName(), reflectedClassName, action);
					}
				}
			
		}

		sb.append("\n");
		////////////////Create methods////////////////////
		for(int i=0; i< method.length; i++){

			int indexUCL1 =firstIndexOfUCL(method[i].getName());
			String fieldName1 =method[i].getName().substring(indexUCL1);
			fieldName1 = Character.toLowerCase(fieldName1.charAt(0)) + fieldName1.substring(1); 

			Class<?>[] parametersType = method[i].getParameterTypes();

			sb.append(getModifer(method[i].getModifiers())+"  "+ 
					method[i].getReturnType().getSimpleName() +" "+ method[i].getName()+"(");
			for(int j=0; j<parametersType.length; j++){
				if(j>0){
//					sb.append(", "+parametersType[j].getSimpleName()+" "+method[i].get.getName());JAVA 8
//				}else{
//					sb.append(parametersType[j].getSimpleName()+" "+method[i].getParameters()[j].getName());JAVA 8
//					sb.append(", "+parametersType[j].getSimpleName()+" "+parametersType[j].getName());
					sb.append(", "+parametersType[j].getSimpleName()+" "+fieldName1);
				}else{
//					sb.append(parametersType[j].getSimpleName()+" "+parametersType[j].getName());
					sb.append(parametersType[j].getSimpleName()+" "+fieldName1);


				}
			}
			sb.append(")"+   "{ \n" );
			/////////Writing method bodies ONLY FOR GETTERS/SETTERS.///////////////
			if(isGetter(method[i]) || isSetter(method[i])){
				int indexUCL =firstIndexOfUCL(method[i].getName());
				String fieldName =method[i].getName().substring(indexUCL);
				fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1); 

				if(method[i].getReturnType().getSimpleName().equals("void")){
//					sb.append("this."+fieldName +" = "+ method[i].getParameters()[0].getName()+";");JAVA 8
//					sb.append("this."+fieldName +" = "+ parametersType[0].getName()+";");
					sb.append("this."+fieldName +" = "+ fieldName+";");
					
				}else{
					sb.append("return "+ fieldName+";");
				}
			}
			sb.append("\n" + " }\n");
		}
		sb.append("}");

		writer.write(sb+"");
		writer.close();
	}

	//		JavaCompiler compiler    = ToolProvider.getSystemJavaCompiler();
	//		StandardJavaFileManager fileManager =
	//				compiler.getStandardFileManager(null, null, null);
	//
	//		fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
	//				Arrays.asList(new File("/temp")));
	//		// Compile the file
	//		compiler.getTask(null,
	//				fileManager,
	//				null,
	//				null,
	//				null,
	//				fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile)))
	//		.call();
	//		fileManager.close();



	private String generateBeanChildName(String reflectedClassName)
			throws Exception {
		Class[] packageClasses = getClasses("com.helloworld.shared.beans");
		ArrayList<String> listClasses = new ArrayList<String>(); 
		for(int j=0; j< packageClasses.length; j++){

			listClasses.add(packageClasses[j].getName());
		}

		int random = 0;
		String childName = reflectedClassName+"Child";
		while(listClasses.contains(childName)){
			childName = childName+""+random+1;
		}
		return childName;
	}

	private boolean implementsSerializable(Class<?>[] listofImplements){
		for(int i=0;i< listofImplements.length; i++){
			if(listofImplements[i].getSimpleName().equals("Serializable")){
				return true;
			}
		}
		return false;

	}

	private boolean allFieldsPrivate(Class<?> fieldClass) {
		for(int i=0; i<fieldClass.getDeclaredFields().length; i++){
			if(! Modifier.isPrivate(fieldClass.getDeclaredFields()[i].getModifiers())){
				return false;
			}
		}
		return true;
	}

	public String getModifer(int modifier){
		if(Modifier.isAbstract(modifier)){
			return "abstract";
		}
		if( Modifier.isFinal(modifier)){
			return "final";
		}
		if(Modifier.isInterface(modifier)){
			return "interface";
		}
		if(Modifier.isNative(modifier)){
			return "native";
		}
		if( Modifier.isPrivate(modifier)){
			return "private";
		}
		if( Modifier.isProtected(modifier)){
			return "protected";
		}
		if( Modifier.isPublic(modifier)){
			return "public";
		}
		if(Modifier.isStatic(modifier)){
			return "static";
		}
		if(Modifier.isStrict(modifier)){
			return "strict"; 
		}
		if(Modifier.isSynchronized(modifier)){
			return "synchronized";
		}
		if(Modifier.isTransient(modifier)){
			return "transient";
		}
		if(Modifier.isVolatile(modifier)){
			return "volatile";
		}
		else return "public";
	}

	public static boolean isGetter(Method method){
		if(!method.getName().startsWith("get")&&!method.getName().startsWith("is"))      return false;
		if(method.getParameterTypes().length != 0)   return false;  
		if(void.class.equals(method.getReturnType())) return false;
		return true;
	}

	public static boolean isSetter(Method method){
		if(!method.getName().startsWith("set")) return false;
		if(method.getParameterTypes().length != 1) return false;
		return true;
	}

	public int firstIndexOfUCL(String str) {        
		for(int i=0; i<str.length()-1; i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				return i;
			}
		}
		return -1;
	}


	//	@SuppressWarnings("unchecked")
	//	public static void runIt() {
	//		try {
	//			Class params[] = {};
	//			Object paramsObj[] = {};
	//			//      Class thisClass = Class.forName("Hello");
	//			Class thisClass = MyFirstBean.class;
	//			Object iClass = thisClass.newInstance();
	//			Method thisMethod = thisClass.getDeclaredMethod("getAddress", params);
	//			thisMethod.invoke(iClass, paramsObj);
	//		}
	//		catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

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


	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
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
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
	private File createFilePath(String myPath) {

		String dir = System.getProperty("user.dir");
		int slashIndex = dir.lastIndexOf("\\");
		dir = dir.substring(0, slashIndex+1);
		String packageDir = myPath.replace(".","\\");
		dir =dir +"src\\"+ packageDir;
		File myPackage    = new File(dir);
		return myPackage;
	}

}
