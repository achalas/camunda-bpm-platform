/*
 * Copyright © 2013-2019 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.identity.impl.ldap;

import java.util.ArrayList;

import org.camunda.bpm.engine.impl.test.PluggableProcessEngineTestCase;

/**
 * @author Daniel Meyer
 *
 */
public abstract class LdapIdentityProviderTest extends PluggableProcessEngineTestCase {

  protected LdapTestEnvironment ldapTestEnvironment;

  protected void setUp() throws Exception {
    ldapTestEnvironment = new LdapTestEnvironment();
    ldapTestEnvironment.init();
    super.setUp();
  }

  protected void tearDown() throws Exception {
    if(ldapTestEnvironment != null) {
      ldapTestEnvironment.shutdown();
      ldapTestEnvironment = null;
    }
    super.tearDown();
  }
}