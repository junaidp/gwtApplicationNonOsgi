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
package com.sapientarrow.gwtapp.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mindrot.BCrypt;

import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.org.hibernate.DynHelper;
import com.sapientarrow.gwtapp.server.FilesCreationHelper;
import com.sapientarrow.gwtapp.shared.dto.BeanObjectDTO;
import com.sapientarrow.gwtapp.shared.dto.BindingsDTO;
import com.sapientarrow.gwtapp.shared.entity.BeanObjects;
import com.sapientarrow.gwtapp.shared.entity.BindingsEntity;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.MyAccountEntity;
import com.sapientarrow.gwtapp.shared.entity.NameSpaceEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

// This class is a Main class to communicate to database , where we have all the queries in hibernate
public class MyRdbHelper {

	private SessionFactory sessionFactory;
	Logger logger;
	private static int workload = 12;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public String addUser(UserEntity user) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();

			boolean existingUser = true;
			if(user.getUserId()== 0){
				existingUser = false;
			}

			if(userNameAlreadyExist(session, user.getUserName(), user.getUserId())){
				return ApplicationConstants.USERNAME_NOT_AVAILABLE;
			}
			if(emailAlreadyExist(session, user.getEmail(),  user.getUserId())){
				return ApplicationConstants.EMAIL_NOT_AVAILABLE;
			}

			try{// Check if coming password is in Bcrypt form already {in case of editing User account ,when user only changing other fields but not password}
				BCrypt.checkpw("test", user.getPassword());
			}catch(Exception ex){
				//It will come inside Exception Only when the password is not a Bcrypted hashed password, So only then we will make it Bcrypted
				String password = createBCrypt(user.getPassword());
				user.setPassword(password);
			}
			String generatedPassword = user.getPassword();

			user.setTokenGeneratedDate(new Date());
			user.setToken(generatedPassword);
			user.getMyAccountId().setLastEdited(new Date());
			session.saveOrUpdate(user.getMyAccountId());
			session.saveOrUpdate(user);
			session.flush();
			if(existingUser){
				return ApplicationConstants.USER_UPDATED;
			}else{
				return ApplicationConstants.USER_ADDED;
			}

		}
		catch(Exception ex){
			logger.warn(String.format("Exception occured in adduser", ex.getMessage()), ex);
			System.out.println("Exception occured in adduser"+ ex.getMessage());
			throw new Exception("Exception occured in adduser");
		}
		finally{
			session.close();
		}
	}

	public String createBCrypt(String password){

		String hash = bCrypt(password);
		return hash;
	}

	public static String bCrypt(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}


	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash)
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}

	public boolean userNameAlreadyExist(Session session, String userName, int userId)throws Exception{

		try{
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("userName", userName));
			crit.add(Restrictions.ne("userId", userId));
			if(crit.list().size()>0){
				return true;
			}else{
				return false;
			}
		}	catch(Exception ex){
			logger.warn(String.format("Exception occured in userNameAlreadyExist", ex.getMessage()), ex);
			System.out.println("Exception occured in userNameAlreadyExist"+ ex.getMessage());
			throw new Exception("Exception occured in userNameAlreadyExist");
		}

	}

	public boolean emailAlreadyExist(Session session, String email, int userId)throws Exception{
		try{
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("email", email));
			crit.add(Restrictions.ne("userId", userId));
			if(crit.list().size()>0){
				return true;
			}else{
				return false;
			}
		}	catch(Exception ex){
			logger.warn(String.format("Exception occured in emailAlreadyExist", ex.getMessage()), ex);
			System.out.println("Exception occured in emailAlreadyExist"+ ex.getMessage());
			throw new Exception("Exception occured in emailAlreadyExist");
		}
	}


	public boolean verifySubscription(String email)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("email", email));
			if(crit.list().size()>0){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception ex){
			logger.warn(String.format("Exception occured in verifySubscription", ex.getMessage()), ex);
			System.out.println("Exception occured in verifySubscription"+ ex.getMessage());
			throw new Exception("Exception occured in verifySubscription");
		}
	}


	public UserEntity signIn(String userName, String password) throws Exception{
		Session session = null;
		UserEntity user = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("userName", userName));
			crit.createAlias("myAccountId", "myAccount");
			if(crit.list().size()>0){
				user = (UserEntity) crit.list().get(0);
				if(checkPassword(password, user.getPassword())){
					System.out.println("User LoggedIn : "+ user.getName());
					user.setUserFetchStatus("Success");
					return user;
				}else{
					System.out.println("User Login Attemt failed "+ user.getName());
					UserEntity noUser = new UserEntity();
					noUser.setUserFetchStatus(ApplicationConstants.USERNAME_PASSWORD_NOT_MATCH);
					return noUser;
				}
			}else{
				UserEntity noUser = new UserEntity();
				noUser.setUserFetchStatus(ApplicationConstants.USER_NOT_FOUND);
				return noUser;
			}
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in signIn", ex.getMessage()), ex);
			System.out.println("Exception occured in signIn"+ ex.getMessage());
			throw new Exception("Exception occured in signIn");
		}
	}


	public GlobalPreferencesEntity fetchGlobalPreferences()throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(GlobalPreferencesEntity.class);
			crit.createAlias("myAccountPreferencesId", "myAccountPreferences");
			if(crit.list().size()>0){
				GlobalPreferencesEntity globalPreferencesEntity = (GlobalPreferencesEntity) crit.list().get(0);
				return globalPreferencesEntity;
			}
			else {
				return null;
			}
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchGlobalPreferences", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchGlobalPreferences"+ ex.getMessage());
			throw new Exception("Exception occured in fetchGlobalPreferences");
		}
	}


	public String updateGlobalPreferences(
			GlobalPreferencesEntity globalPreferencesEntity)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.saveOrUpdate(globalPreferencesEntity.getMyAccountPreferencesId());
			session.saveOrUpdate(globalPreferencesEntity);
			session.flush();
			return ApplicationConstants.GLOBALPREFERENCESUPDATED;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updateGlobalPreferences", ex.getMessage()), ex);
			System.out.println("Exception occured in updateGlobalPreferences"+ ex.getMessage());
			throw new Exception("Exception occured in updateGlobalPreferences");
		}
	}


	public String updateMyAccount(MyAccountEntity myAccountEntity)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			myAccountEntity.setLastEdited(new Date());
			session.update(myAccountEntity);
			session.flush();
			return ApplicationConstants.MY_ACCOUNT_UPDATED;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updateGlobalPreferences", ex.getMessage()), ex);
			System.out.println("Exception occured in updateGlobalPreferences"+ ex.getMessage());
			throw new Exception("Exception occured in updateGlobalPreferences");
		}
	}

	public String inactivateAccount(String username) throws Exception{

		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("userName", username));
			if(crit.list().size()>0){
				Transaction tr = session.beginTransaction();
				UserEntity user = (UserEntity) crit.list().get(0);
				user.setStatus(ApplicationConstants.BLOCK);
				session.update(user);
				tr.commit();
				return ApplicationConstants.ACCOUNT_INACTIVATED;
			}
			return ApplicationConstants.USER_NOT_FOUND;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in inactivateAccount", ex.getMessage()), ex);
			throw new Exception("Exception occured in inactivateAccount");
		}
		finally{
			session.close();
		}

	}

	public UserEntity fetchUser(String username) throws Exception{

		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("userName", username));
			if(crit.list().size()>0){
				UserEntity user = (UserEntity) crit.list().get(0);

				return user;
			}
			return null;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchUser", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchUser");
		}
		finally{
			session.close();
		}

	}

	public String fetchUserStatus(String username, String email)throws Exception {
		Session session = null;
		int status = 0;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("userName", username));
			if(crit.list().size()>0){
				UserEntity user = (UserEntity) crit.list().get(0);
				status = user.getStatus();

			}else{
				return ApplicationConstants.USER_NOT_FOUND;
			}
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchUserStatus", ex.getLocalizedMessage()), ex);
			logger.info(String.format("Exception occured in fetchUserStatus", ex.getLocalizedMessage()), ex);
			System.out.println(ex.getLocalizedMessage());
		}
		finally{
			session.close();
		}
		if(status != 1){
			return ApplicationConstants.CONTACT_ADMIN;
		}
		else{
			return sendActivationEmailForgotPassword(username, email);

		}
	}

	public String sendActivationEmailForgotPassword(String username, String email) throws Exception {

		UserEntity user = fetchUser(username);
		if(user ==null){
			return ApplicationConstants.USER_NOT_FOUND;
		}
		else{
			Session session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("userName", username));
			crit.add(Restrictions.eq("email", email));

			if(crit.list().size() > 0){
				user = (UserEntity) crit.list().get(0);
				Transaction tr = session.beginTransaction();
				user.setTokenGeneratedDate(new Date());
				session.update(user);
				tr.commit();
				sendEmailForgotPassword(user);
			}else{
				return ApplicationConstants.EMAIL_NOT_CORRECT;
			}
			return "A new temporary password token <br>" +
			"has been emailed to you";
		}
	}

	private void sendEmailForgotPassword(UserEntity user){
		final String username = "hyphenconsult@gmail.com";
		final String password = "ilzhkshpmtqduzuc";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		javax.mail.Session sessionMail = javax.mail.Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(sessionMail);
			message.setFrom(new InternetAddress("hyphenconsult@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject("Account Password changed");

			message.setContent("Dear " + user.getName() +" <br></br> <br></br>"
					+ "Your Password updated <br></br> <br></br>"
					+ "Please create your new password <br></br> <br></br>"
					//					+" Your password must be at least 8 charcters long, should have atleast 1 digit(0-9) and should have an uppercase letter.<br></br>"
					//					+" You secret question must be at least  8 characters long and should have an uppercase letter, You will need to answer the secret question if you ever forget your password.<br></br>"
					+" To activate your account, please click on the link below.<br></br> <br></br>"
					//					+" <a href= http://localhost:8080/tie/#"+ user.getToken()+">"+"Activate Account</a>","text/html" );
					//					+" <a href= http://127.0.0.1:8888/HelloWorldGWT.html?gwt.codesvr=127.0.0.1:9997#!"+ user.getToken()+">"+"Activate Account</a>","text/html" );
					+" <a href= abilite.ddns.net/gwtapp/#!"+ user.getToken()+">"+"Activate Account</a>","text/html" );



			Transport.send(message);

			System.out.println("email sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public UserEntity fetchNewUser(String token)throws Exception {
		Session session = null;
		UserEntity user = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("token", token));
			crit.createAlias("myAccountId", "myAccount");


			if(crit.list().size()>0){
				user =(UserEntity) crit.list().get(0);
			}
			return user;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchNewUser", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchNewUser");
		}
		finally{
			session.close();
		}

	}

	public Boolean isLoggedInWithin20Mins(UserEntity user) throws Exception {

		DateTime passwordGenerateDate = new DateTime(user.getTokenGeneratedDate()); 
		DateTime currentDate = new DateTime(); //current date
		Minutes diff =  Minutes.minutesBetween(passwordGenerateDate, currentDate);

		if (diff.getMinutes()>20){
			return false;
		}else 
			return true;

	}


	public String updatePassword(UserEntity user)throws Exception {
		Session session = null;
		try{
			String password = createBCrypt(user.getPassword());
			user.setPassword(password);

			session = sessionFactory.openSession();
			session.update(user);
			session.flush();
			return ApplicationConstants.PASSWORD_UPDATED;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePassword", ex.getMessage()), ex);
			throw new Exception("Exception occured in updatePassword");
		}

	}


	public String emailUserName(String email)throws Exception {
		Session session = null;

		try{

			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.add(Restrictions.eq("email", email));
			if(crit.list().size()>0){
				UserEntity user = (UserEntity) crit.list().get(0);
				emailUserNameToUser(user);
				return ApplicationConstants.USERNAME_EMAIL_SENT;
			}else{
				return ApplicationConstants.EMAIL_NOT_VALID;
			}

		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePassword", ex.getMessage()), ex);
			throw new Exception("Exception occured in updatePassword");
		}

	}


	private void emailUserNameToUser(UserEntity user) {
		final String username = "hyphenconsult@gmail.com";
		final String password = "ilzhkshpmtqduzuc";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		javax.mail.Session sessionMail = javax.mail.Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(sessionMail);
			message.setFrom(new InternetAddress("hyphenconsult@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject("Requested Username");

			message.setContent("Dear " + user.getName() +" <br></br> <br></br>"
					+ "As per your request ,please find below your username <br></br> <br></br>"
					+ "Username: <b> "+user.getUserName()+"</b>"+" <br></br> <br></br>"
					+ "Thank You <br></br>"
					+ "","text/html" );

			Transport.send(message);

			System.out.println("email sent forgot username");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


	public ArrayList<UserEntity> fetchAllUsers()throws Exception {
		Session session = null;
		ArrayList<UserEntity> users = new ArrayList<UserEntity>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(UserEntity.class);
			crit.createAlias("myAccountId", "myAccount");
			for(int i=0; i< crit.list().size(); i++){
				UserEntity user = (UserEntity) crit.list().get(i);
				users.add(user);
			}
			return users;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAllUsers", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchAllUsers");
		}

	}


	public String updateUserAccount(UserEntity user)throws Exception {
		Session session =null;
		try{
			session = sessionFactory.openSession();
			session.update(user);
			session.flush();
			return ApplicationConstants.USER_UPDATED;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updateUserAccount", ex.getMessage()), ex);
			throw new Exception("Exception occured in updateUserAccount");
		}
	}


	public String closeAccount(UserEntity user) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.update(user);
			session.flush();
			return ApplicationConstants.ACCOUNT_CLOSED;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in closeAccount", ex.getMessage()), ex);
			throw new Exception("Exception occured in closeAccount");
		}
	}


	public String saveBeanObjectIntoDataBase(Object beanObjectToSavedInDb,
			String selectedBeanName)throws Exception {

		Session session = null;
		BeanObjects beanObjectEntity = null;
		try{
			session = sessionFactory.openSession();
			beanObjectEntity = fetchBeanAlreadySaved(selectedBeanName);
			if(beanObjectEntity == null){
				beanObjectEntity = new BeanObjects();
			}

			beanObjectEntity.setBeanName(selectedBeanName);

			//			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//		    ObjectOutputStream os = new ObjectOutputStream(out);
			//		    os.writeObject(beanObjectToSavedInDb);
			//		    byte[] serializedObject = out.toByteArray();

			Blob blob = Hibernate.getLobCreator(session).createBlob(serialize(beanObjectToSavedInDb));
			//			Blob blob = Hibernate.createBlob(serialize(beanObjectToSavedInDb));
			beanObjectEntity.setBeanObject(blob);
			beanObjectEntity.setBeanType('S');
			session.saveOrUpdate(beanObjectEntity);
			session.flush();
			return "bean saved in database";
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in saveBeanObjectIntoDataBase", ex.getMessage()), ex);
			throw new Exception("Exception occured in saveBeanObjectIntoDataBase");
		}
	}

	//	public String saveBindingsBeanObjectIntoDataBase(Object beanObjectToSavedInDb,
	//			String selectedBeanName, int beanId)throws Exception {
	//
	//		Session session = null;
	//		BeanObjects beanObjectEntity = null;
	//		try{
	//			session = sessionFactory.openSession();
	//			Criteria crit = session.createCriteria(BindingsEntity.class);
	//			crit.add(Restrictions.eq("beanId", beanId));
	//			BindingsEntity bindingsEntity = crit.list().get(0);
	//			beanObjectEntity = fetchBeanAlreadySaved(selectedBeanName);
	//			if(beanObjectEntity == null){
	//				beanObjectEntity = new BeanObjects();
	//			}
	//
	//			beanObjectEntity.setBeanName(selectedBeanName);
	//
	//			Blob blob = Hibernate.getLobCreator(session).createBlob(serialize(beanObjectToSavedInDb));
	//			//			Blob blob = Hibernate.createBlob(serialize(beanObjectToSavedInDb));
	//			beanObjectEntity.setBeanObject(blob);
	//			beanObjectEntity.setBeanType('S');
	//			session.saveOrUpdate(beanObjectEntity);
	//			session.flush();
	//			return "bean saved in database";
	//		}catch(Exception ex){
	//			logger.warn(String.format("Exception occured in saveBeanObjectIntoDataBase", ex.getMessage()), ex);
	//			throw new Exception("Exception occured in saveBeanObjectIntoDataBase");
	//		}
	//	}

	public BeanObjects fetchBeanAlreadySaved(String beanName)throws Exception{
		Session session = null;
		BeanObjects beanObjects = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(BeanObjects.class);
			crit.add(Restrictions.eq("beanName", beanName));
			if(crit.list().size() > 0){
				beanObjects = (BeanObjects) crit.list().get(0);

			}
			return beanObjects;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in isBeanNameAlreadySaved", ex.getMessage()), ex);
			throw new Exception("Exception occured in isBeanNameAlreadySaved");
		}
	}

	public String fetchBeanObject(String beanName)throws Exception{
		Session session = null;
		String beanJson = "";
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(BeanObjects.class);
			crit.add(Restrictions.eq("beanName", beanName));
			if(crit.list().size() > 0){
				BeanObjects beanObjects = (BeanObjects) crit.list().get(0);
				ObjectMapper mapper = new ObjectMapper();
				byte[] bdata = beanObjects.getBeanObject().getBytes(1, (int) beanObjects.getBeanObject().length());
				mapper.setSerializationInclusion(Inclusion.NON_NULL);
				beanJson = mapper.writeValueAsString(toObject(bdata));

			}
			return beanJson;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchBeanObject", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchBeanObject");
		}

	}

	public ArrayList<BeanObjectDTO> fetchAllBeansInDb()throws Exception{
		Session session = null;
		ArrayList<BeanObjectDTO> beanObjectsDTO = new ArrayList<BeanObjectDTO>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(BeanObjects.class);
			if(crit.list().size() > 0){
				for(int i=0; i< crit.list().size(); i++){
					BeanObjects beanObject = (BeanObjects) crit.list().get(i);
					String beanJson = "";
					BeanObjectDTO beanObjectDTO = new BeanObjectDTO();
					beanObjectDTO.setBeanId(beanObject.getBeanId());
					beanObjectDTO.setBeanName(beanObject.getBeanName());
					if(beanObject.getBeanObject() != null){
						ObjectMapper mapper = new ObjectMapper();
						byte[] bdata = beanObject.getBeanObject().getBytes(1, (int) beanObject.getBeanObject().length());
						mapper.setSerializationInclusion(Inclusion.NON_NULL);
						beanJson = mapper.writeValueAsString(toObject(bdata));
					}
					beanObject.setBeanJson(beanJson);
					beanObjectDTO.setBeanJson(beanJson);
					beanObjectsDTO.add(beanObjectDTO);
				}


			}
			return beanObjectsDTO;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAllBeansInDb", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchAllBeansInDb");
		}

	}


	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}

	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
		return obj;
	}


	public String loadBeansJsonAndCreateDynamicBeans() throws Exception {


		return loadBeansJson();
	}


	public String loadBeansJson()throws Exception{

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			String dir = System.getProperty("user.dir");
			File folder = new File(dir+"\\"+ApplicationConstants.DYNAMIC_BEANS_FOLDER);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					Object obj = parser.parse(new FileReader(file));
					jsonObject = (JSONObject) obj;
					int id = file.getName().indexOf(".");
					int dashInd = file.getName().indexOf("-");
					String beanName= file.getName().substring(dashInd+1, id);
					fetchBeanTable(file.getName().substring(dashInd+1, id), jsonObject);
					//					DynHelper dyn = new DynHelper();
					//					dyn.createDynamicBeanFromOnModule(jsonObject, beanName);
				}
			}

		}catch(Exception ex){
			throw new Exception(ex);
		}
		return "bena's json and table loaded";
	}


	public void fetchBeanTable(String beanName, JSONObject jsonObject) throws Exception{
		try{
			Session session = sessionFactory.openSession();
			String sql = "SELECT * FROM "+beanName;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List dbData = query.list();
			System.out.println(dbData);
			DynHelper dyn = new DynHelper();
			dyn.createDynamicBeanFromOnModule(dbData, jsonObject, beanName);
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}


	public ArrayList<BindingsDTO> fetchAllBindings(String keyword) throws Exception{

		Session session = null;
		ArrayList<BindingsDTO> listBindingsDTO = new ArrayList<BindingsDTO>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(BindingsEntity.class);
			crit.createAlias("nameSpaceId", "nameSpace");
			crit.add(Restrictions.like("bindingName", keyword, MatchMode.START));
			for(int i=0; i< crit.list().size(); i++){
				BindingsEntity bindingsEntity = (BindingsEntity) crit.list().get(i);

				BindingsDTO bindingDTO = new BindingsDTO();
				bindingDTO.setBindingId(bindingsEntity.getBindingId());
				bindingDTO.setBindingName(bindingsEntity.getBindingName());
				bindingDTO.setBindingType(bindingsEntity.getBindingType());
				bindingDTO.setBindingValue(bindingsEntity.getBindingValue());
				bindingDTO.setNameSpaceId(bindingsEntity.getNameSpaceId());
				bindingDTO.setType(bindingsEntity.getType());
				bindingDTO.setBeanId(bindingsEntity.getBeanId());

				listBindingsDTO.add(bindingDTO);
			}


			return listBindingsDTO;
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}


	public String saveBinding(BindingsDTO bindingDTO) throws Exception {
		Session session = null;
		try{
			if(bindingNameAvailable(bindingDTO.getBindingName(), bindingDTO.getBindingId())){
				session = sessionFactory.openSession();
				if(bindingDTO.getNameSpaceId().getNameSpaceId() == 0){
					if(nameSpaceAvailable(bindingDTO.getNameSpaceId().getNameSpaceName(), bindingDTO.getNameSpaceId().getNameSpaceId())){
						saveNameSpace(bindingDTO.getNameSpaceId(), session);
					}else{
						return ApplicationConstants.NAMESPACE_NOT_AVAILABLE;
					}
				}

				BindingsEntity bindingEntity = new BindingsEntity();
				bindingEntity.setBindingId(bindingDTO.getBindingId());
				bindingEntity.setBindingName(bindingDTO.getBindingName());
				bindingEntity.setBindingType(bindingDTO.getBindingType());
				bindingEntity.setBindingValue(bindingDTO.getBindingValue());
				bindingEntity.setNameSpaceId(bindingDTO.getNameSpaceId());
				bindingEntity.setBeanId(bindingDTO.getBeanId());
				bindingEntity.setType(bindingDTO.getType());
				if(bindingDTO.getType() == 'B'&& bindingDTO.getBeanId() != 0){
					if(bindingDTO.getBeanPropertiesMap().size() > 0){
						FilesCreationHelper filesCreationHelper = new FilesCreationHelper();
						Object beanObjectToSavedInDb =  filesCreationHelper.editBeanOnPropertyChange(bindingDTO.getBindingType(), bindingDTO.getBeanPropertiesMap());
						Blob blob = Hibernate.getLobCreator(session).createBlob(serialize(beanObjectToSavedInDb));
						bindingEntity.setBindingValue_ext(blob);
					}
					//					bindingEntity.setBindingValue_ext(fetchBeansBlob(bindingDTO.getBeanId(), session));
				}
				session.saveOrUpdate(bindingEntity);
				session.flush();
				return "binding saved";
			}else{
				return ApplicationConstants.BINDING_NAME_NOT_AVAILABLE;
			}
		}catch(Exception ex){
			throw new Exception(ex);
		}

	}

	private Blob fetchBeansBlob(int beanId, Session session) throws Exception{

		Blob beanBlob = null;
		try{
			BeanObjects beanObjects  = (BeanObjects) session.get(BeanObjects.class, beanId);
			if(beanObjects!= null && beanObjects.getBeanObject() != null){
				beanBlob = beanObjects.getBeanObject();
			}

			return beanBlob;
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}


	public void saveNameSpace(NameSpaceEntity nameSpaceEntity, Session session)throws Exception{
		try{

			session.saveOrUpdate(nameSpaceEntity);
			session.flush();

		}catch(Exception ex){
			throw new Exception(ex);
		}
	}

	public boolean bindingNameAvailable(String name, int bindingId)throws Exception{
		Session session = null;
		try{
			if(bindingId !=0){
				return true;
			}
			session = sessionFactory.openSession();
			Criteria crit =session.createCriteria(BindingsEntity.class);
			crit.add(Restrictions.eq("bindingName", name));
			if(crit.list().size()> 0){
				return false;
			}else{
				return true;
			}
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}

	public boolean nameSpaceAvailable(String name, int nameSpaceId)throws Exception{
		Session session = null;
		try{
			if(nameSpaceId!=0){
				return true;
			}
			session = sessionFactory.openSession();
			Criteria crit =session.createCriteria(NameSpaceEntity.class);
			crit.add(Restrictions.eq("nameSpaceName", name));
			if(crit.list().size()> 0){
				return false;
			}else{
				return true;
			}
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}


	public String deleteBinding(int bindingId)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			BindingsEntity bindingEntity = (BindingsEntity) session.get(BindingsEntity.class, bindingId);
			session.delete(bindingEntity);
			session.flush();
			return "Binding Deleted";
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}


	public String deleteMultipleBindings(ArrayList<Integer> bindingIds) throws Exception{
		for(int i=0; i< bindingIds.size(); i++){
			deleteBinding(bindingIds.get(i));
		}
		return "Bindings Deleted";
	}


	public ArrayList<NameSpaceEntity> fetchNameSpaces()throws Exception {
		Session session = null;
		ArrayList<NameSpaceEntity> nameSpaceList = new ArrayList<NameSpaceEntity>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(NameSpaceEntity.class);
			for(int i=0; i< crit.list().size(); i++){
				NameSpaceEntity nameSpaceEntity = (NameSpaceEntity) crit.list().get(i);
				nameSpaceList.add(nameSpaceEntity);
			}
			return nameSpaceList;
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}


	public void saveBeanInDatabase(String name)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			BeanObjects bean = new BeanObjects();
			bean.setBeanName(name);
			bean.setBeanType('S');
			session.saveOrUpdate(bean);
			session.flush();
		}catch(Exception ex){
			throw new Exception(ex);
		}
	}
}


