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

package com.dinstone.practice.locale;

import java.util.Locale;

import com.dinstone.practice.base.SystemProperty;

/**
 * @author guojf
 * @version 1.0.0.2012-3-5
 */
public class LocaleCheck {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SystemProperty.showSystemProperty();
        
        System.out.println("=========Available Locales==========");
        showAvailableLocales();
        System.out.println("=========Default Locale==========");
        Locale dl = Locale.getDefault();
        showLocale(dl);
    }

    private static void showAvailableLocales() {
        Locale[] localeList = Locale.getAvailableLocales();
        for (int i = 0; i < localeList.length; i++) {
            Locale locale = localeList[i];
            showLocale(locale);
        }
    }

    private static void showLocale(Locale locale) {
        System.out.print("Locale(\"");
        System.out.print(locale.getLanguage());
        System.out.print("\",\"");
        System.out.print(locale.getCountry());
        System.out.print("\",\"");
        System.out.print(locale.getVariant());
        System.out.print("\") \t ");
        System.out.println(locale.getDisplayName());
    }

}
