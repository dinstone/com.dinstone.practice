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

package com.dinstone.practice.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author guojf
 * @version 1.0.0.2011-12-24
 */
public class CallableTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            try {
                Callable<String> c = new CallableTask(String.valueOf(i));
                Future<String> ft = executor.submit(c);
                System.out.println("Finish:" + ft.get(1000, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

    }
}

class CallableTask implements Callable<String> {

    private String id = null;

    public CallableTask(String id) {

        this.id = id;

    }

    public String call() {

        try {

            System.out.println(id + " Start");

            Thread.sleep(1000);

            System.out.println(id + " Do");

            Thread.sleep(1000);

            System.out.println(id + " Exit");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return id;

    }
}