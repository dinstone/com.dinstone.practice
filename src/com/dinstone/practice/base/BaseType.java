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

/**
 * @author guojf
 * @version 1.0.0.2012-3-3
 */
public class BaseType {

    public static void main(String[] args) {
        double a = (3.3 - 2.4) / 0.1;
        System.out.println(a);

        double b = (double) 979 / 474;
        System.out.println(b);

        int w = 1000 / 200 / 30;
        System.out.println(w);

        long t = w - 34;
        System.out.println(t);
    }

}
