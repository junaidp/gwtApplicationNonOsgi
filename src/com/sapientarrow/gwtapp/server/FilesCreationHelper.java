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
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.database.MyRdbHelper;
import com.sapientarrow.gwtapp.shared.DynamicCompilation;
import com.sapientarrow.gwtapp.shared.dto.AddedBeanDTO;
import com.sapientarrow.gwtapp.shared.dto.BeanExceptionDTO;

// This class creates and writes the bean via reflection in  the project
public class FilesCreationHelper {

	private String baseBeanName= "";
	private Object parentobject = null;
	private Object baseObject = null;
	private Method parentMethod = null;


	public String generateBean(AddedBeanDTO addedBeanDTO) throws Exception{
		try{

			String dir = System.getProperty("user.dir");
			int slashIndex = dir.lastIndexOf("\\");
			dir = dir.substring(0, slashIndex+1);
			String packageDir = addedBeanDTO.getPackageName().replace(".","\\");
//			dir =dir +"src\\"+ packageDir;
			dir =dir +ApplicationConstants.TOMCAT_PATH+ packageDir;
			File myPackage    = new File(dir);
			myPackage.mkdir();
			File sourceFile   = new File(dir+"\\"+addedBeanDTO.getBeanName()+".java");
			FileWriter writer = new FileWriter(sourceFile);



			//////////////Crate Package and class/////////////////
			StringBuffer sb = new StringBuffer("");
			sb.append("package "+ addedBeanDTO.getPackageName()+"; \n \n");
			sb.append("import java.io.Serializable; \n ");
			sb.append("import java.util.*; \n \n");
			for(int i=0; i< addedBeanDTO.getListImports().size(); i++){
				sb.append("import "+ addedBeanDTO.getListImports().get(i)+"; \n");
			}

			sb.append("\n");
			//Annotaion....
			for(int i=0; i< addedBeanDTO.getListAnnotationsDTO().size(); i++){
				sb.append(addedBeanDTO.getListAnnotationsDTO().get(i).getName()+addedBeanDTO.getListAnnotationsDTO().get(i).getAnnotationText()+  "\n" );
			}
			sb.append("\n");
			//End Annotation...

			sb.append("public class "+addedBeanDTO.getBeanName()+" implements Serializable { \n \n");
			sb.append("public "+ addedBeanDTO.getBeanName()+"() { \n");
			sb.append("} \n \n");
			//////////ADD Fields///////////
			for(int i=0; i< addedBeanDTO.getListProperties().size(); i++){

				//Field Annotaion....
				for(int j=0; j< addedBeanDTO.getListProperties().get(i).getListAnnotationsDTO().size(); j++){
					sb.append(addedBeanDTO.getListProperties().get(i).getListAnnotationsDTO().get(j).getName()+ 
							addedBeanDTO.getListProperties().get(i).getListAnnotationsDTO().get(j).getAnnotationText()+ "\n" );
				}
				sb.append(addedBeanDTO.getListProperties().get(i).getModifier()+" "+addedBeanDTO.getListProperties().get(i).getDataType()+" "+ addedBeanDTO.getListProperties().get(i).getName()+";");
				sb.append("\n");
				sb.append("\n");
			}
			sb.append("\n");
			/////////ADD Getters/Setters //////////
			for(int i=0; i< addedBeanDTO.getListProperties().size(); i++){
				String fieldName =addedBeanDTO.getListProperties().get(i).getName();
				fieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);	
				if(addedBeanDTO.getListProperties().get(i).getGetterSetter().equalsIgnoreCase("Both")){
					sb.append(/*addedBeanDTO.getListProperties().get(i).getModifier()+*/"public "+ addedBeanDTO.getListProperties().get(i).getDataType()+" ");
					sb.append("get"+ fieldName+"() {\n");
					sb.append("return "+ addedBeanDTO.getListProperties().get(i).getName()+"; \n");
					sb.append("} \n");
					sb.append("public void ");
					sb.append("set"+ fieldName+"("+ addedBeanDTO.getListProperties().get(i).getDataType()+" "+addedBeanDTO.getListProperties().get(i).getName()+") { ");
					sb.append("\n");
					sb.append("this."+addedBeanDTO.getListProperties().get(i).getName()+" = "+ addedBeanDTO.getListProperties().get(i).getName()+";");
					sb.append("\n }");
					sb.append("\n");
				}
				else if(addedBeanDTO.getListProperties().get(i).getGetterSetter().equalsIgnoreCase("Getters")){
					sb.append("public "+ addedBeanDTO.getListProperties().get(i).getDataType()+" ");
					sb.append("get"+ fieldName+"() {\n");
					sb.append("return "+ addedBeanDTO.getListProperties().get(i).getName()+"; \n");
					sb.append("} \n");
					sb.append("\n");
				}
				else if(addedBeanDTO.getListProperties().get(i).getGetterSetter().equalsIgnoreCase("Setters")){
					sb.append("public void ");
					sb.append("set"+ fieldName+"("+ addedBeanDTO.getListProperties().get(i).getDataType()+" "+addedBeanDTO.getListProperties().get(i).getName()+") { ");
					sb.append("\n");
					sb.append("this."+addedBeanDTO.getListProperties().get(i).getName()+" = "+ addedBeanDTO.getListProperties().get(i).getName()+"; \n");
					sb.append("} \n");
					sb.append("\n");
				}

			}
			sb.append("\n }");
			writer.write(sb+"");
			writer.close();
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			MyRdbHelper rdbHelper = (MyRdbHelper) ctx.getBean("ManagerApp");
			rdbHelper.saveBeanInDatabase(addedBeanDTO.getPackageName()+"."+addedBeanDTO.getBeanName());
			
			return "Bean Created";
		}catch(Exception ex){
			throw new Exception(ex.getCause());
		}
	}

	public int firstIndexOfUCL(String str) {        
		for(int i=0; i<str.length()-1; i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				return i;
			}
		}
		return -1;
	}

	public String fetchBeanJSON(String className, String reflectionName, String action) throws Exception {
		String jsonInString = "";
		try{

			new DynamicCompilation(className, reflectionName, action);
			int ind = className.lastIndexOf(".");
			String reflectedClass = className.substring(0, ind+1);
			reflectedClass = reflectedClass+reflectionName;

			Class myClass = Class.forName(className);
			Object obj = myClass.newInstance();
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(obj);
		}catch(Exception ex){
			throw new Exception( "Exception occured in Reflection"+ ex);
		}
		return jsonInString;
		//		return "";
	}


	public static boolean isSetter(Method method){
		if(!method.getName().startsWith("set")) return false;
		if(method.getParameterTypes().length != 1) return false;
		return true;
	}
	
	


	public Object editBeanOnPropertyChange(String selectedBeanName,
			TreeMap beanPropertiesMap) throws Exception {

		try{
			Class bean = Class.forName(selectedBeanName);
			baseBeanName = selectedBeanName;
			parentobject = bean.newInstance();

			return editBeanOnChange(selectedBeanName, beanPropertiesMap);
		}catch(Exception ex){
			throw ex;
		}
	}
	
//	public String editBindingsBean(String selectedBeanName,
//			TreeMap beanPropertiesMap) throws Exception {
//
//		try{
//			Class bean = Class.forName(selectedBeanName);
//			baseBeanName = selectedBeanName;
//			parentobject = bean.newInstance();
//
//			return editBeanOnChange(selectedBeanName, beanPropertiesMap);
//		}catch(Exception ex){
//			throw ex;
//		}
//	}


	public Object editBeanOnChange(String selectedBeanName,
			TreeMap beanPropertiesMap) throws Exception {


		try{
			Class bean = Class.forName(selectedBeanName);
			Object object = bean.newInstance();
			if(parentMethod!=null){
				parentMethod.invoke(parentobject, object);
			}
			parentobject = object;
			if(baseObject == null){
				baseObject = parentobject;
			}

			// Iterate on methods of current Bean and invoking all methods (other than sub entities)
			final Method[] methods = bean.getMethods();
			for(Method method : methods){
				if(isSetter(method)){
					Class<?>[] parameter = method.getParameterTypes();
					String propertyName = parameter[0].getName();
					if(!propertyName.startsWith(ApplicationConstants.DEFAULT_PACKAGE_START)){
						editBean(object, beanPropertiesMap, method);
					}
				}	
			}
			// Iterate on methods of current Bean and invoking all methods (Only sub entities)
			for(Method method : methods){
				if(isSetter(method)){
					Class<?>[] parameter = method.getParameterTypes();
					String propertyName = parameter[0].getName();
					String entityName = parameter[0].getSimpleName();

					if(propertyName.startsWith(ApplicationConstants.DEFAULT_PACKAGE_START)){


						Set set = beanPropertiesMap.entrySet();
						Iterator i = set.iterator();
						TreeMap beanPropertiesChildMap = new TreeMap();
						parentMethod = method;
						while(i.hasNext()) {
							Map.Entry me = (Map.Entry)i.next();
							String propertyName1 = me.getKey().toString();
							if(propertyName1.contains("_")){
								int ind_ = propertyName1.indexOf("_");
								String beanChildName = propertyName1.substring(0, ind_);

								if(entityName.equalsIgnoreCase(beanChildName)){

									beanPropertiesChildMap.put(propertyName1.substring(ind_+1), me.getValue());
								}
							}
						}
						if(beanPropertiesChildMap.size() > 0){
							editBeanOnChange(propertyName, beanPropertiesChildMap);
						}

					}

				}
			}
			saveBeanObjectIntoDataBase(baseObject, baseBeanName);
			return baseObject;
		}catch(Exception ex){
			throw new Exception("Exception : "+ ex.getLocalizedMessage());
		}
	}

	
	//////////////////////////BEAN EDITING FOR BINDING/////////////////////
//	public String editBindingBeanOnPropertyChange(String selectedBeanName,
//			TreeMap beanPropertiesMap) throws Exception {
//
//		try{
//			Class bean = Class.forName(selectedBeanName);
//			baseBeanName = selectedBeanName;
//			parentobject = bean.newInstance();
//
//			return editBeanOnChange(selectedBeanName, beanPropertiesMap);
//		}catch(Exception ex){
//			throw ex;
//		}
//	}
//	
//	public String editBindingBindingsBean(String selectedBeanName,
//			TreeMap beanPropertiesMap) throws Exception {
//
//		try{
//			Class bean = Class.forName(selectedBeanName);
//			baseBeanName = selectedBeanName;
//			parentobject = bean.newInstance();
//
//			return editBeanOnChange(selectedBeanName, beanPropertiesMap);
//		}catch(Exception ex){
//			throw ex;
//		}
//	}
//
//
//	public String editBindingBeanOnChange(String selectedBeanName,
//			TreeMap beanPropertiesMap) throws Exception {
//
//
//		try{
//			Class bean = Class.forName(selectedBeanName);
//			Object object = bean.newInstance();
//			if(parentMethod!=null){
//				parentMethod.invoke(parentobject, object);
//			}
//			parentobject = object;
//			if(baseObject == null){
//				baseObject = parentobject;
//			}
//
//			// Iterate on methods of current Bean and invoking all methods (other than sub entities)
//			final Method[] methods = bean.getMethods();
//			for(Method method : methods){
//				if(isSetter(method)){
//					Class<?>[] parameter = method.getParameterTypes();
//					String propertyName = parameter[0].getName();
//					if(!propertyName.startsWith(ApplicationConstants.DEFAULT_PACKAGE_START)){
//						editBean(object, beanPropertiesMap, method);
//					}
//				}	
//			}
//			// Iterate on methods of current Bean and invoking all methods (Only sub entities)
//			for(Method method : methods){
//				if(isSetter(method)){
//					Class<?>[] parameter = method.getParameterTypes();
//					String propertyName = parameter[0].getName();
//					String entityName = parameter[0].getSimpleName();
//
//					if(propertyName.startsWith(ApplicationConstants.DEFAULT_PACKAGE_START)){
//
//
//						Set set = beanPropertiesMap.entrySet();
//						Iterator i = set.iterator();
//						TreeMap beanPropertiesChildMap = new TreeMap();
//						parentMethod = method;
//						while(i.hasNext()) {
//							Map.Entry me = (Map.Entry)i.next();
//							String propertyName1 = me.getKey().toString();
//							if(propertyName1.contains("_")){
//								int ind_ = propertyName1.indexOf("_");
//								String beanChildName = propertyName1.substring(0, ind_);
//
//								if(entityName.equalsIgnoreCase(beanChildName)){
//
//									beanPropertiesChildMap.put(propertyName1.substring(ind_+1), me.getValue());
//								}
//							}
//						}
//						if(beanPropertiesChildMap.size() > 0){
//							editBeanOnChange(propertyName, beanPropertiesChildMap);
//						}
//
//					}
//
//				}
//			}
//			saveBindingBeanObjectIntoDataBase(baseObject, baseBeanName);
//			return "";
//		}catch(Exception ex){
//			throw new Exception("Exception : "+ ex.getLocalizedMessage());
//		}
//	}
//	/////////////////////////FOR BINDING END//////////////////

	private void editBean(Object beanObject, TreeMap beanPropertiesMap, Method method)throws Exception {
		Map.Entry me = null;
		try {
			Set set = beanPropertiesMap.entrySet();
			Iterator i = set.iterator();

			while(i.hasNext()) {
				me = (Map.Entry)i.next();
				if(method.getName().equalsIgnoreCase("set"+me.getKey())){
					method.invoke(beanObject, me.getValue());
					break;
				}
			}

		} catch (Exception e) {
			
			int ind = beanObject.toString().indexOf("@");
			ObjectMapper mapper = new ObjectMapper();
			BeanExceptionDTO beanExceptionDTO = new BeanExceptionDTO();
			beanExceptionDTO.setBean(beanObject.toString().substring(0, ind));
			beanExceptionDTO.setExpecting(method.getParameterTypes()[0].getSimpleName());
			beanExceptionDTO.setField(me.getKey().toString());
			beanExceptionDTO.setReceiving(me.getValue().getClass().getSimpleName());

			String jsonInString = mapper.writeValueAsString(beanExceptionDTO);
			throw new Exception(jsonInString);
			//			throw new Exception("Exception on field '"+me.getKey()+"' of bean: " + beanObject.toString().substring(0, ind) +" Expecting: "+ 
			//			method.getParameterTypes()[0].getSimpleName() +" Receiving: " +me.getValue().getClass().getSimpleName()  + " ( "+ e.getLocalizedMessage()+" ) ");
		}
	}

	private void saveBeanObjectIntoDataBase(Object beanObject, String selectedBeanName) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MyRdbHelper rdbHelper = (MyRdbHelper) ctx.getBean("ManagerApp");
		
		rdbHelper.saveBeanObjectIntoDataBase(beanObject, selectedBeanName);
		

	}
	
	public String fetchBeanStructureJson(String beanName)throws Exception {
		Class myClass = Class.forName(beanName);
		Field[] field = myClass.getDeclaredFields();

		field[0].getName();

		JSONObject obj = new JSONObject();
		for(int i=0; i< field.length ; i++){
			obj.put(field[i].getName(), "("+field[i].getType().getSimpleName()+")");
		}
		String dir = System.getProperty("user.dir");
		File folder = new File(dir+"/"+ApplicationConstants.DOWNLOADED_BEAN_FIELDS_JSON);
		FileWriter file = new FileWriter(folder);
		file.write(obj.toString());
		file.flush();
		file.close();
		return null;
	}


}
