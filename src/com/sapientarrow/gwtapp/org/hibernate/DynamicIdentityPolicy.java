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

//EclipseLink imports
import org.eclipse.persistence.internal.identitymaps.CacheId;
import org.eclipse.persistence.internal.jpa.CMP3Policy;
import org.eclipse.persistence.internal.sessions.AbstractSession;

/**
* Custom CMPPolicy to handle Object[]. The parent class supports only List and
* ID class for composite keys.
*
* @author dclarke
* @since EclipseLink 1.2
*/
public class DynamicIdentityPolicy extends CMP3Policy {

  @Override
  public Object createPrimaryKeyFromId(Object key, AbstractSession session) {
      if (key instanceof Object[]) {
          return new CacheId((Object[])key);
      }
      return super.createPrimaryKeyFromId(key, session);
  }

  @Override
  protected KeyElementAccessor[] initializePrimaryKeyFields(Class keyClass, AbstractSession session) {
      if (keyClass == null && getDescriptor().getPrimaryKeyFields().size() > 1) {
          KeyElementAccessor[] result = super.initializePrimaryKeyFields(null, session);
          this.pkClass = Object[].class;
          return result;
      } else {
          return super.initializePrimaryKeyFields(keyClass, session);
      }
  }

}