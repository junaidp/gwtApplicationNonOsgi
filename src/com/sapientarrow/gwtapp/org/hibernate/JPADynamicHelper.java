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
package com.sapientarrow.gwtapp.org.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


//EclipseLink imports
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.dynamic.DynamicHelper;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.sessions.DatabaseSession;

/**
 *
 * @author dclarke
 * @since EclipseLink 1.2
 */
public class JPADynamicHelper extends DynamicHelper {

    public JPADynamicHelper(EntityManagerFactory emf) {
        super(JpaHelper.getServerSession(emf));
    }

    public JPADynamicHelper(EntityManager em) {
        super((DatabaseSession) JpaHelper.getEntityManager(em).getActiveSession());
//	super(JpaHelper.getEntityManager(em).getServerSession());
    }

    /**
     * Add one or more EntityType instances to a session and optionally generate
     * needed tables with or without FK constraints.
     */
    public void addTypes(boolean createMissingTables, boolean generateFKConstraints, DynamicType... types) {
        super.addTypes(createMissingTables, generateFKConstraints, types);
        for (DynamicType type : types) {
            ClassDescriptor descriptor = type.getDescriptor();
            descriptor.getQueryManager().checkDatabaseForDoesExist();
        }
    }
}