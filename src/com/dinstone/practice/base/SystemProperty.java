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

package com.dinstone.practice.base;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class SystemProperty {

    /**
     * @param args
     */
    public static void main(String[] args) {
        showSystemProperty();
    }

    public static void showSystemProperty() {
        System.out.println("####################################################");
        System.out.println("# System Properties ");
        System.out.println("####################################################");
        Properties sp = System.getProperties();
        Enumeration<?> names = sp.propertyNames();
        while (names.hasMoreElements()) {
            String e = (String) names.nextElement();
            System.out.println(e + "=" + sp.getProperty(e));
        }

        System.out.println("####################################################");
        System.out.println("# Environment Variables ");
        System.out.println("####################################################");
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            System.out.println(key + "=" + env.get(key));
        }
    }

}
