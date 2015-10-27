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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guojf
 * @version 1.0.0.2011-12-24
 */
public class ExecutorTest {

    public static void main(String[] args) {

        new RejectedExecutionHandler() {

            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (!executor.isShutdown()) {
                    try {
                        System.out.println(".");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    executor.execute(r);
                }

            }
        };

        ExecutorService executor = new ThreadPoolExecutor(0, 5, 30L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnable>());

        // executor = new ThreadPoolExecutor(0, 2, 30L, TimeUnit.MILLISECONDS,
        // new LinkedBlockingQueue<Runnable>());
        //
        // executor = new ThreadPoolExecutor(1, 4, 30L, TimeUnit.MILLISECONDS,
        // new LinkedBlockingQueue<Runnable>());

        // executor = new ThreadPoolExecutor(2, 2, 30L, TimeUnit.MILLISECONDS,
        // new LinkedBlockingQueue<Runnable>());

        // executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 15; i++) {

            Runnable r = new ETask(i);

            executor.execute(r);

        }

        System.out.println("will close");
        executor.shutdown();

    }
}

class ETask implements Runnable {

    private int id = 0;

    public ETask(int id) {

        this.id = id;

    }

    public void run() {

        try {

            System.out.println(id + " Start");

            Thread.sleep(1000);

            System.out.println(id + " Do");

            Thread.sleep(2000);

            System.out.println(id + " Exit");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
