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

package com.dinstone.practice.object;

public class P {

    public static int sint = 2;

    private int iint = 1;

    static {
        System.out.println(sint);
        sint = 3;
    }

    {
        System.out.println(iint);
        iint = 4;
    }

    public P() {
        System.out.println(sint);
        System.out.println(iint);
    }

    public int getIint() {
        return iint;
    }

    public void setIint(int iint) {
        this.iint = iint;
    }

}