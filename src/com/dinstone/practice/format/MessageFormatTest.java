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

package com.dinstone.practice.format;

import java.text.MessageFormat;

/**
 * @author guojf
 * @version 1.0.0.2011-12-31
 */
public class MessageFormatTest {

    public static void main(String[] args) {
        String line = "asd,asd,";
        String[] parts = line.split(",", 4);
        System.out.println("length = " + parts.length);
        for (String p : parts) {
            System.out.println(p);
        }
        System.out.println("======================");

        MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0} file(s). '''{1'}");

        Object[] testArgs = { new Long(1234), "sssss" };
        System.out.println(form.format(testArgs));

        Object[] testArgs2 = { new Long(8084), "ppppppp" };
        System.out.println(form.format(testArgs2));
    }
}
