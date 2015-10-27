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

package com.dinstone.practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guojf
 * @version 1.0.0.2012-2-7
 */
public class TimeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // time();
        String input = "/asd/dd";

        int index = input.lastIndexOf('/');
        if (index > 0) {
            String s = input.substring(index);
            System.out.println("sub = " + s);
        } else if (index == 0) {
            System.out.println("sub = " + input);
        }
        // split(input);

        String regex = "(/\\w+)*";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(input);

        if (matcher.find()) {
            int c = matcher.groupCount();
            System.out.println("groupCount=" + c);
            for (int i = 0; i <= c; i++) {
                System.out.println(matcher.group(i));
            }

            System.out.println("OK");
        } else {
            System.out.println("NG");
        }

    }

    private static void split(String input) {
        String[] sl = input.split("/");
        for (int i = 0; i < sl.length; i++) {
            System.out.println("sl[" + i + "] = " + sl[i]);
        }
    }

    private static void time() {
        String regex = "([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])";
        Pattern p = Pattern.compile(regex);
        String input = " 15:25:53 ";
        Matcher matcher = p.matcher(input);

        if (matcher.find()) {
            int c = matcher.groupCount();
            for (int i = 0; i <= c; i++) {
                System.out.println(matcher.group(i));
            }

            int h = Integer.parseInt(matcher.group(1));
            int m = Integer.parseInt(matcher.group(2));
            int s = Integer.parseInt(matcher.group(3));
            System.out.println("秒数:" + (h * 3600 + m * 60 + s));
            System.out.println("OK");
        } else {
            System.out.println("NG");
        }
    }
}
