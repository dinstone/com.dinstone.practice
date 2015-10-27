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

package com.dinstone.practice.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author guojf
 * @version 1.0.0.2012-3-3
 */
public class DateFormatTest {

    public static void main(String[] args) {

        checkDate(Locale.getDefault());

        checkDate(Locale.ENGLISH);

        checkDate(new Locale("en", "ms"));

        checkDate(new Locale("", "ms"));

        String sa = "2012-03-03";
        Date check = null;
        Locale locale = Locale.ENGLISH;
        DateFormat[] dfs = getDateFormats(locale);
        for (DateFormat dfo : dfs) {
            try {
                check = dfo.parse(sa);
                if (check != null) {
                    System.out.println(check);
                    break;
                }
            } catch (ParseException ignore) {
            }
        }

    }

    private static void checkDate(Locale l) {
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, l);
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT, l);
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM, l);
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG, l);
        DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL, l);
        String s = df.format(now);
        String s1 = df1.format(now);
        String s2 = df2.format(now);
        String s3 = df3.format(now);
        String s4 = df4.format(now);
        System.out.println("=====================Locale(" + l + ")=====================");
        System.out.println("(Default) \t " + s);
        System.out.println("(SHORT)   \t " + s1);
        System.out.println("(MEDIUM)  \t " + s2);
        System.out.println("(LONG)    \t " + s3);
        System.out.println("(FULL)    \t " + s4);
    }

    private static DateFormat[] getDateFormats(Locale locale) {
        DateFormat dt1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, locale);
        DateFormat dt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale);
        DateFormat dt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);

        DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        DateFormat d2 = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        DateFormat d3 = DateFormat.getDateInstance(DateFormat.LONG, locale);

        DateFormat rfc3399 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DateFormat[] dfs = { dt1, dt2, dt3, rfc3399, d1, d2, d3 }; // added
                                                                   // RFC
        // 3339 date
        // format
        // (XW-473)
        return dfs;
    }
}
