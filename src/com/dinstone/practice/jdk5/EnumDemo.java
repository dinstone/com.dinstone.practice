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

package com.dinstone.practice.jdk5;

/**
 * @author guojf
 * @version 1.0.0.2011-12-28
 */
public class EnumDemo {

    private enum Color {
        Red("R", 1), White("W", 2), Blue("B", 3);

        private int value;

        private String tag;

        private Color(String tag, int value) {
            this.tag = tag;
            this.value = value;
        }

        /**
         * the value to get
         * 
         * @return the value
         * @see EnumDemo.Color#value
         */
        public int getValue() {
            return value;
        }

        public String getTag() {
            return tag;
        }

    };

    /**
     * @param args
     */
    public static void main(String[] args) {
        Color myColor = Color.Red;
        showEnum(myColor);

        Color valueOf = Color.valueOf("Red");
        showEnum(valueOf);

        if (myColor == valueOf) {
            System.out.println("it is the same object");
        }
    }

    private static void showEnum(Color color) {
        System.out.println(color);
        System.out.println(color.getTag());
        System.out.println(color.getValue());
    }

}
