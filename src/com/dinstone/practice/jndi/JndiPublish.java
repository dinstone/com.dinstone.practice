/*
 * Copyright (C) 2011~2012 dinstone <dinstone@163.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.dinstone.practice.jndi;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class JndiPublish {

    public static void main(String[] args) {
        //
        // This example creates a subcontext in a namespace
        //
        try {
            Properties prop = new Properties();
            // set content initial factory,access CORBA naming service
            prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
            // set provider url
            prop.setProperty(Context.PROVIDER_URL, "iiop://127.0.0.1:900");

            InitialContext ic = new InitialContext(prop);
            // create subcontext
            ic.createSubcontext("/Test");
            Object t = ic.lookup("/Test");
            System.out.println(t);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
