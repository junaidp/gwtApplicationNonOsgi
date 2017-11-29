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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sapientarrow.gwtapp.client.admin.AdminPresenter;
import com.sapientarrow.gwtapp.client.admin.AdminView;
import com.sapientarrow.gwtapp.client.beaneditor.JavaBeanEditorPresenter;
import com.sapientarrow.gwtapp.client.beaneditor.JavaBeanEditorView;
import com.sapientarrow.gwtapp.client.binding.BindingsPresenter;
import com.sapientarrow.gwtapp.client.binding.BindingsView;
import com.sapientarrow.gwtapp.client.createpassword.CreatePasswordPresenter;
import com.sapientarrow.gwtapp.client.createpassword.CreatePasswordView;
import com.sapientarrow.gwtapp.client.dashboard.SearchDataView;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.MyAccountView;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.ViewPlanPresenter;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.ViewPlanView;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.ViewRegistrationPresenter;
import com.sapientarrow.gwtapp.client.dashboard.MyDashboard.MyAccountViews.ViewRegistrationView;
import com.sapientarrow.gwtapp.client.dashboard.accordion.DashboardAccordion;
import com.sapientarrow.gwtapp.client.dashboard.accordion.DashboardAccordionPresenter;
import com.sapientarrow.gwtapp.client.dashboard.dashboarddefault.DashboardDefault;
import com.sapientarrow.gwtapp.client.dashboard.dashboarddefault.DashboardDefaultPresenter;
import com.sapientarrow.gwtapp.client.dashboard.portal.DashboardPortalPresenter;
import com.sapientarrow.gwtapp.client.dashboard.portal.DashboardPortalView;
import com.sapientarrow.gwtapp.client.downloadjson.DownloadJsonPresenter;
import com.sapientarrow.gwtapp.client.downloadjson.DownloadJsonView;
import com.sapientarrow.gwtapp.client.event.AdminEvent;
import com.sapientarrow.gwtapp.client.event.AdminEventHandler;
import com.sapientarrow.gwtapp.client.event.AssignEditorsEvent;
import com.sapientarrow.gwtapp.client.event.AssignEditorsEventHandler;
import com.sapientarrow.gwtapp.client.event.DashboardAccordionEvent;
import com.sapientarrow.gwtapp.client.event.DashboardAccordionEventHandler;
import com.sapientarrow.gwtapp.client.event.DashboardEvent;
import com.sapientarrow.gwtapp.client.event.DashboardEventHandler;
import com.sapientarrow.gwtapp.client.event.DynamicBeanEditorEvent;
import com.sapientarrow.gwtapp.client.event.DynamicBeanEditorEventHandler;
import com.sapientarrow.gwtapp.client.event.EditUserEvent;
import com.sapientarrow.gwtapp.client.event.EditUserEventHandler;
import com.sapientarrow.gwtapp.client.event.GlobalPreferencesEvent;
import com.sapientarrow.gwtapp.client.event.GlobalPreferencesEventHandler;
import com.sapientarrow.gwtapp.client.event.JavaBeanEditorEvent;
import com.sapientarrow.gwtapp.client.event.JavaBeanEditorEventHandler;
import com.sapientarrow.gwtapp.client.event.MainEvent;
import com.sapientarrow.gwtapp.client.event.MainEventHandler;
import com.sapientarrow.gwtapp.client.event.MyAccountEvent;
import com.sapientarrow.gwtapp.client.event.MyAccountEventHandler;
import com.sapientarrow.gwtapp.client.event.RegistrationEvent;
import com.sapientarrow.gwtapp.client.event.RegistrationEventHandler;
import com.sapientarrow.gwtapp.client.event.SearchDataEvent;
import com.sapientarrow.gwtapp.client.event.SearchDataEventHandler;
import com.sapientarrow.gwtapp.client.event.SubscriptionVerificationEvent;
import com.sapientarrow.gwtapp.client.event.SubscriptionVerificationEventHandler;
import com.sapientarrow.gwtapp.client.event.ViewEditRegistrationEvent;
import com.sapientarrow.gwtapp.client.event.ViewEditRegistrationEventHandler;
import com.sapientarrow.gwtapp.client.event.ViewPlanEvent;
import com.sapientarrow.gwtapp.client.event.ViewPlanEventHandler;
import com.sapientarrow.gwtapp.client.fileupload.FileUploadPresenter;
import com.sapientarrow.gwtapp.client.fileupload.FileUploadView;
import com.sapientarrow.gwtapp.client.forgotpassword.ForgotPasswordPresenter;
import com.sapientarrow.gwtapp.client.forgotpassword.ForgotPasswordView;
import com.sapientarrow.gwtapp.client.forgotusername.ForgotUserNamePresenter;
import com.sapientarrow.gwtapp.client.forgotusername.ForgotUserNameView;
import com.sapientarrow.gwtapp.client.generateuploadedui.AssignDataToBeanView;
import com.sapientarrow.gwtapp.client.generateuploadedui.BeanFieldsEditorPresenter;
import com.sapientarrow.gwtapp.client.generateuploadedui.BeanFieldsEditorView;
import com.sapientarrow.gwtapp.client.globalpreferences.GlobalPreferencesPresenter;
import com.sapientarrow.gwtapp.client.globalpreferences.GlobalPreferencesView;
import com.sapientarrow.gwtapp.client.login.LoginPresenter;
import com.sapientarrow.gwtapp.client.login.LoginView;
import com.sapientarrow.gwtapp.client.manageuser.ManageUserPresenter;
import com.sapientarrow.gwtapp.client.manageuser.ManageUserView;
import com.sapientarrow.gwtapp.client.presenter.AssignDataToBeanPresenter;
import com.sapientarrow.gwtapp.client.presenter.MainPresenter;
import com.sapientarrow.gwtapp.client.presenter.MyAccountPresenter;
import com.sapientarrow.gwtapp.client.presenter.Presenter;
import com.sapientarrow.gwtapp.client.presenter.SearchDataPresenter;
import com.sapientarrow.gwtapp.client.registration.RegistrationPresenter;
import com.sapientarrow.gwtapp.client.registration.RegistrationView;
import com.sapientarrow.gwtapp.client.subscriptionverification.SubscriptionVerificationPresenter;
import com.sapientarrow.gwtapp.client.subscriptionverification.SubscriptionVerificationView;
import com.sapientarrow.gwtapp.client.view.ApplicationConstants;
import com.sapientarrow.gwtapp.client.view.MainView;
import com.sapientarrow.gwtapp.shared.entity.GlobalPreferencesEntity;
import com.sapientarrow.gwtapp.shared.entity.UserEntity;

//This is the main controller of the whole application which manages to navigate from one page to other

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;

    private final HelloServiceAsync rpcService;
    private HasWidgets container;
    private UserEntity loggedInUser;

    private GlobalPreferencesEntity globalPreferencesEntity;
    private String createPasswordtoken = "";
    private String beanName;
    private String beanType;

    Presenter presenter = null;

    public AppController(HelloServiceAsync rpcService, HandlerManager eventBus) {
	this.eventBus = eventBus;
	this.rpcService = rpcService;
	bind();
    }

    private void bind() {
	History.addValueChangeHandler(this);

	eventBus.addHandler(MainEvent.TYPE, new MainEventHandler() {
	    public void onMain(MainEvent event) {
		loggedInUser = event.getUser();
		globalPreferencesEntity = event.getGlobalPreferences();
		History.newItem(ApplicationConstants.TOKEN_MAIN);
	    }
	});

	eventBus.addHandler(EditUserEvent.TYPE, new EditUserEventHandler() {
	    public void onEditUser(EditUserEvent event) {
		loggedInUser = event.getUser();
		History.newItem(ApplicationConstants.TOKEN_EDIT_USER);
	    }
	});

	eventBus.addHandler(AssignEditorsEvent.TYPE, new AssignEditorsEventHandler() {
	    public void onAssignEditors(AssignEditorsEvent event) {
		beanName = event.getJson();
		beanType = event.getBeanType();
		History.newItem(ApplicationConstants.TOKEN_ASSIGN_EDITORS);
	    }
	});

	eventBus.addHandler(GlobalPreferencesEvent.TYPE, new GlobalPreferencesEventHandler() {
	    public void onGlobalPreferences(GlobalPreferencesEvent event) {
		History.newItem(ApplicationConstants.TOKEN_GLOBAL_PREFERENCES);
	    }
	});

	eventBus.addHandler(AdminEvent.TYPE, new AdminEventHandler() {
	    public void onAdmin(AdminEvent event) {
		History.newItem(ApplicationConstants.TOKEN_ADMIN);
	    }
	});

	eventBus.addHandler(DashboardEvent.TYPE, new DashboardEventHandler() {
	    public void onDashboard(DashboardEvent event) {
		// center = event.getCenter();
		History.newItem(ApplicationConstants.TOKEN_DASHBOARD);
	    }
	});

	eventBus.addHandler(DashboardAccordionEvent.TYPE, new DashboardAccordionEventHandler() {
	    public void onDashboardAccordion(DashboardAccordionEvent event) {
		// center = event.getCenter();
		History.newItem(ApplicationConstants.TOKEN_DASHBOARD_ACCORDION);
	    }
	});

	eventBus.addHandler(ViewPlanEvent.TYPE, new ViewPlanEventHandler() {
	    public void onViewPlan(ViewPlanEvent event) {
		History.newItem(ApplicationConstants.TOKEN_VIEW_PLAN);
	    }
	});

	eventBus.addHandler(ViewEditRegistrationEvent.TYPE, new ViewEditRegistrationEventHandler() {
	    public void onViewEditRegistration(ViewEditRegistrationEvent event) {
		History.newItem(ApplicationConstants.TOKEN_VIEW_REG);
	    }
	});

	eventBus.addHandler(SearchDataEvent.TYPE, new SearchDataEventHandler() {
	    public void onSearchData(SearchDataEvent event) {
		// center = event.getCenter();
		History.newItem(ApplicationConstants.TOKEN_SEARCHDATA);
	    }
	});

	eventBus.addHandler(RegistrationEvent.TYPE, new RegistrationEventHandler() {
	    public void onRegistration(RegistrationEvent event) {
		History.newItem(ApplicationConstants.TOKEN_REGISTRATION);
	    }
	});

	eventBus.addHandler(SubscriptionVerificationEvent.TYPE, new SubscriptionVerificationEventHandler() {
	    public void onSubscriptionVerification(SubscriptionVerificationEvent event) {
		History.newItem(ApplicationConstants.TOKEN_SUBSCRIPTION_VERFICATION);
	    }
	});

	eventBus.addHandler(MyAccountEvent.TYPE, new MyAccountEventHandler() {
	    public void onMyAccount(MyAccountEvent event) {
		History.newItem(ApplicationConstants.TOKEN_MY_ACCOUNT);
	    }
	});

	eventBus.addHandler(JavaBeanEditorEvent.TYPE, new JavaBeanEditorEventHandler() {
	    public void onJavaBeanEditor(JavaBeanEditorEvent event) {
		History.newItem(ApplicationConstants.TOKEN_JAVA_BEAN_EDITOR);
	    }
	});

	eventBus.addHandler(DynamicBeanEditorEvent.TYPE, new DynamicBeanEditorEventHandler() {
	    public void onDynamicBeanEditor(DynamicBeanEditorEvent event) {
		History.newItem(ApplicationConstants.TOKEN_DYNAMIC_BEAN_EDITOR);
	    }
	});
    }

    public void go(final HasWidgets container) {
	this.container = container;

	if ("".equals(History.getToken())) {
	    History.newItem(ApplicationConstants.TOKEN_SUBSCRIPTION_VERFICATION);
	} else if (History.getToken().startsWith("!")) {
	    createPasswordtoken = History.getToken().substring(1);
	    History.newItem("createPassword");
	} else {
	    History.fireCurrentHistoryState();
	}
    }

    public void onValueChange(ValueChangeEvent<String> event) {
	String token = event.getValue();

	if (token != null) {
	    presenter = null;

	    if (token.equals(ApplicationConstants.TOKEN_LOGIN)) {
		presenter = new LoginPresenter(rpcService, eventBus, new LoginView());
		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_MAIN)) {
		presenter = new MainPresenter(rpcService, eventBus, globalPreferencesEntity,
			new MainView(loggedInUser));

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_EDIT_USER)) {
		presenter = new RegistrationPresenter(rpcService, eventBus,
			new RegistrationView(loggedInUser, globalPreferencesEntity));
		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_DASHBOARD)) {
		presenter = new DashboardDefaultPresenter(rpcService, eventBus, new DashboardDefault());
		if (presenter != null) {
		    // setContainer(center);
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_DASHBOARD_ACCORDION)) {
		presenter = new DashboardAccordionPresenter(rpcService, eventBus, new DashboardAccordion());
		if (presenter != null) {
		    // setContainer(center);
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_DASHBOARD_PORTAL)) {
		presenter = new DashboardPortalPresenter(rpcService, eventBus, new DashboardPortalView());
		if (presenter != null) {
		    // setContainer(center);
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_SEARCHDATA)) {
		presenter = new SearchDataPresenter(rpcService, eventBus, new SearchDataView());
		if (presenter != null) {
		    // setContainer(center);
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_REGISTRATION)) {
		presenter = new RegistrationPresenter(rpcService, eventBus, new RegistrationView());
		if (presenter != null) {
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_SUBSCRIPTION_VERFICATION)) {
		presenter = new SubscriptionVerificationPresenter(rpcService, eventBus,
			new SubscriptionVerificationView());
		if (presenter != null) {
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_GLOBAL_PREFERENCES)) {
		presenter = new GlobalPreferencesPresenter(rpcService, eventBus, loggedInUser,
			new GlobalPreferencesView());
		// presenter = new GlobalPreferencesPresenter(rpcService,
		// eventBus, new GlobalPreferencesXmlView());

		if (presenter != null) {
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_FILE_UPLOAD)) {
		presenter = new FileUploadPresenter(rpcService, eventBus, new FileUploadView());
		if (presenter != null) {
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_MY_ACCOUNT)) {
		presenter = new MyAccountPresenter(rpcService, eventBus, new MyAccountView(), loggedInUser,
			globalPreferencesEntity);
		if (presenter != null) {
		    setContainer(container);
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_VIEW_PLAN)) {
		presenter = new ViewPlanPresenter(rpcService, eventBus, new ViewPlanView(), globalPreferencesEntity,
			loggedInUser);
		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_VIEW_REG)) {
		presenter = new ViewRegistrationPresenter(rpcService, eventBus, new ViewRegistrationView(),
			globalPreferencesEntity, loggedInUser);
		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_ADMIN)) {
		presenter = new AdminPresenter(rpcService, eventBus, new AdminView());
		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_FORGOT_PASSWORD)) {
		presenter = new ForgotPasswordPresenter(rpcService, eventBus, new ForgotPasswordView());
		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals("createPassword")) {
		presenter = new CreatePasswordPresenter(rpcService, eventBus,
			new CreatePasswordView(loggedInUser, createPasswordtoken));

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_FORGOT_USERNAME)) {
		presenter = new ForgotUserNamePresenter(rpcService, eventBus, new ForgotUserNameView());

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_MANAGE_USER)) {
		presenter = new ManageUserPresenter(rpcService, eventBus, new ManageUserView());

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_JAVA_BEAN_EDITOR)) {
		presenter = new JavaBeanEditorPresenter(rpcService, eventBus,
			new JavaBeanEditorView(ApplicationConstants.JAVA_BEAN));

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_DYNAMIC_BEAN_EDITOR)) {
		presenter = new JavaBeanEditorPresenter(rpcService, eventBus,
			new JavaBeanEditorView(ApplicationConstants.DYNAMIC_BEAN));

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_UPLOAD_COMPONENTS)) {
		presenter = new BeanFieldsEditorPresenter(rpcService, eventBus,
			new BeanFieldsEditorView(ApplicationConstants.JAVA_BEAN));

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_UPLOAD_COMPONENTS_DYNAMICBEAN)) {
		presenter = new BeanFieldsEditorPresenter(rpcService, eventBus,
			new BeanFieldsEditorView(ApplicationConstants.DYNAMIC_BEAN));

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_ASSIGN_EDITORS)) {
		presenter = new AssignDataToBeanPresenter(rpcService, eventBus, new AssignDataToBeanView(), beanName,
			beanType);

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_DOWNLOAD_JSON)) {
		presenter = new DownloadJsonPresenter(rpcService, eventBus, new DownloadJsonView());

		if (presenter != null) {
		    presenter.go(container);
		}
	    }

	    if (token.equals(ApplicationConstants.TOKEN_BINDINGS)) {
		presenter = new BindingsPresenter(rpcService, eventBus, new BindingsView());

		if (presenter != null) {
		    presenter.go(container);
		}
	    }
	}
    }

    private void setContainer(HasWidgets container) {
	this.container = container;
	this.container.clear();
    }

    @Override
    public void setHandlers() {

    }

}
