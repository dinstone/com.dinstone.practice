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

package com.dinstone.practice.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author guojf
 * @version 1.0.0.2012-6-14
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> sm = new HashMap<String, String>(9);

        // OK
        sm.put(null, "empty");
        System.out.println(sm.get(null));

        sm.put("k1", "v1");

        // java.util.ConcurrentModificationException
        // for (String key : sm.keySet()) {
        // sm.remove(key);
        // }

        System.out.println(sm);

        for (Iterator<Entry<String, String>> iterator = sm.entrySet().iterator(); iterator.hasNext();) {
            iterator.next();
            iterator.remove();
        }

        System.out.println(sm);
    }

}
