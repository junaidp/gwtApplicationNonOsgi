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
package com.sapientarrow.gwtapp.client.view;

public class ApplicationConstants {

    /// LAYOUT HEADINGS///
    public static final String CONFIGURATION_HEADING = "Configuration";
    public static final String MYACCOUNT_HEADING = "My Account";
    public static final String RUNTIME_HEADING = "Run Time";
    public static final String REPORTS_HEADING = "Reports";

    /// TOKENS///
    public static final String TOKEN_MAIN = "main";
    public static final String TOKEN_LOGIN = "login";
    public static final String TOKEN_DASHBOARD = "dashboard";
    public static final String TOKEN_SEARCHDATA = "searchData";
    public static final String TOKEN_REGISTRATION = "registration";
    public static final String TOKEN_SUBSCRIPTION_VERFICATION = "subscriptionVerification";
    public static final String TOKEN_EDIT_USER = "editUser";
    public static final String TOKEN_DASHBOARD_ACCORDION = "dashboardAccordion";
    public static final String TOKEN_DASHBOARD_PORTAL = "dashboardPortal";
    public static final String TOKEN_GLOBAL_PREFERENCES = "globalPreferences";
    public static final String TOKEN_FILE_UPLOAD = "fileUpload";
    public static final String TOKEN_MY_ACCOUNT = "myAccount";
    public static final String TOKEN_VIEW_PLAN = "viewPlan";
    public static final String TOKEN_ADMIN = "admin";
    public static final String TOKEN_FORGOT_PASSWORD = "forgotPassword";
    public static final String TOKEN_FORGOT_USERNAME = "forgotUsername";
    public static final String TOKEN_MANAGE_USER = "manageUser";
    public static final String TOKEN_VIEW_EDIT_REG = "viewEditReg";
    public static final String TOKEN_VIEW_REG = "viewRegistration";
    public static final String TOKEN_JAVA_BEAN_EDITOR = "javaBeanEditor";
    public static final String TOKEN_DYNAMIC_BEAN_EDITOR = "dynamicBeanEditor";
    public static final String TOKEN_ASSIGN_EDITORS = "assignEditor";
    public static final String TOKEN_UPLOAD_COMPONENTS = "uploadComponents";
    public static final String TOKEN_UPLOAD_COMPONENTS_DYNAMICBEAN = "uploadComponentsDynamicBean";
    public static final String TOKEN_DOWNLOAD_JSON = "downloadJson";
    public static final String TOKEN_BINDINGS = "bindings";

    // GLOBAL PREFERENCES
    public static final String VIEW_PLAN = "View Plan";
    public static final String VIEW_REGISTRATION = "View Registration";
    public static final String EDIT_REGISTRATION = "Edit Registration";
    public static final String VIEW_USAGE = "View Usage";
    public static final String MY_ACCOUNT = "My Account";
    public static final String LOGO = "Logo";
    public static final String GLOBALPREFERENCESUPDATED = "Global Preferences Updated";
    public static final String MY_ACCOUNT_UPDATED = "Account updated";
    public static final String USERNAME_EMAIL_SENT = "Username sent, please check your email. Thank you";
    public static final String PANEL_DASHBOARD = "Dashboard";
    public static final String PANEL_ACCORDION = "Accordion";
    public static final String PANEL_PORTLET = "Portlet";

    /// FIELD VERIFIERS///
    public static final String INVALID_USERNAME = "username invalid";
    public static final String PASSWORD_USERNAME = "password invalid";
    public static final String INVALID_EMAIL = "email invalid";
    public static final String PASSWORD_NOTMATCH = "password & confirm password do not match";
    public static final String INVALID_PASSWORD = "password invalid";
    public static final String INVALID_NAME = "Name Invalid";
    public static final String CAPTCHANOTVERIFIED = "Captcha not verified";
    public static final String SUBSCRIPTION_NOT_VERIFIED = "We do not have any account with this email";
    public static final String USERNAME_NOT_AVAILABLE = "Username not available";
    public static final String EMAIL_NOT_AVAILABLE = "This email is already registered with our system";
    public static final String USER_ADDED = "User Added";
    public static final String USERNAME_PASSWORD_NOT_MATCH = "username/password do not match";
    public static final String USER_UPDATED = "record updated";
    public static final String ENTER_REGISTERED_EMAIL = "Enter your registered email";
    public static final String ENTER_USERNAME = "Enter username";
    public static final String ENTER_PASSWORD = "Enter password";
    public static final int BLOCK = 2;
    public static final int ACTIVE = 1;
    public static final int CLOSED = 3;
    public static final String ACCOUNT_INACTIVATED = "You Account has been locked, Please contact your Administrator";
    public static final String USER_NOT_FOUND = "This Username is not in our system";
    public static final String CONTACT_ADMIN = "Your account is inactive, please contact your Administrator";
    public static final String PASSWORD_UPDATED = "Password updated";
    public static final String EMAIL_NOT_VALID = "This Email Address is not in our system , Please try again";
    public static final String ENTER_NEW_PASSWORD = "Enter new password";
    public static final String CONFIRM_NEW_PASSWORD = "Confirm new password";
    public static final String EMAIL_NOT_CORRECT = "Email Address not correct";
    public static final String CLOSE_ACCOUNT_WARNING = "You will no loger be able to access your account . Are You sure you want to close this Account !";
    public static final String ACCOUNT_CLOSED = "Account Closed";
    public static final String ENTER_BEANNAME = "Enter Bean Name";
    public static final String SELECT_PACKAGE = "Select Package";
    public static final String SAVE = "Save";
    public static final String PROPERTY_NAME = "Property Name";
    public static final String PACKAGE_NOT_SELECTED = "please select a valid package";
    public static final String BEANNAME_REQUIRED = "please enter a valid Bean name";
    public static final String UPDATE = "Update";
    public static final String ADDOBJECT = "Add Data Object";
    public static final String DEFAULT_PACKAGE = "com.sapientarrow.gwtapp.shared.beans";
    public static final String DEFAULT_PACKAGE_START = "com.sapientarrow.gwtapp.";
    public static final String UPLOADED_VIEWS_PACKAGE = "com.sapientarrow.gwtapp.client.view.widgets.UploadedComponents";

    public static final String SELECT_FOR_REFLECTION = "Select Bean for reflection";
    public static final String FIELD_NAME_NOT_AVAILABLE = "";
    public static final String CLASS_LEVEL_ANNOTATIONS = "Class level Annotations";
    public static final String FIELD_LEVEL_ANNOTATIONS = "Field level Annotations";
    public static final String ANNOTATIONS_HIBERNATE = "org.hibernate";
    public static final String ANNOTATIONS_JPA = "javax.persistence";
    public static final String ANNOTATIONS_SPRING = "org.springframework";
    public static final String ANNOTATIONS_CDI = "javax.enterprise";
    public static final String IMPORT_NOT_AVAILABLE = "Annotation not available in provided List, You still want to continue with Adding this Annotation";
    public static final String ENTER_ANNOTAION_TEXT = "Annotation Text";
    public static final String EDIT = "Edit";
    public static final String SELECT_BEAN = "Select Bean";
    public static final String FIELD_EDITORS_CREATION = "Field Editor Creation";
    public static final String UPLOADED_VIEWS_NAME = "UploadedClass";
    public static final String INVALID_UIXML_FILE = "Please select a valid GWT Designer's ui.xml File";
    public static final String INVALID_UIJAVA_FILE = "Please select a valid corresponsing java File";
    public static final String INVALID_UIXML_AND_JAVA_FILE = "Please select a valid ui.xml and corresponding java Files";
    public static final String DOWNLOADED_BEAN_FIELDS_JSON = "downloadedJson";
    public static final String DYNAMIC_BEANS_FOLDER = "dynamicBeans";
    public static final String DYNAMIC_BEAN = "dynamicBean";
    public static final String JAVA_BEAN = "javaBean";
    public static final String ENTER_BINDING_NAME = "Enter Binding Name";
    public static final String ENTER_BINDING_VALUE = "Enter Binding Value";
    public static final String ENTER_NAME_SPACE = "Enter Name Space";
    public static final String NAMESPACE_NOT_AVAILABLE = "Name Space not available, Please try another name for your namespace";
    public static final String BINDING_NAME_NOT_AVAILABLE = "Binding Name not available, Please try another name for your binding";
    public static final String BINDING_NAME_NOT_VALID = "Please select a valid name for your Binding ";
    public static final String NAMESPACE_NAME_NOT_VALID = "Please select a valid name for your Namespace";
    public static final String BEAN_CREATION_FOR_BINDING = "binding";
    public static final String BEAN_CREATION_FOR_EDITOR = "editor";
    public static final String UPLOADED_VIEWS__BINDING_NAME = "UploadedBindingClass";
    public static final String NO_UI_CLASS_FOUND = "No layout available for this bean";
    public static final String DASHBOARD = "Dashboard";
    public static final String MENUS = "Menus";
    public static final String ENTER_WIDTH = "Enter width";
    public static final String ENTER_HEIGHT = "Enter height";
    public static final String TOMCAT_PATH = "\\webapps\\gwtapp\\WEB-INF\\classes\\";

}
