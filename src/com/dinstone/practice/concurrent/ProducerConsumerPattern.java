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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

    private BlockingQueue<String> drop;

    List<String> messages = Arrays.asList("Mares eat oats", "Does eat oats", "Little lambs eat ivy",
        "Wouldn't you eat ivy too?");

    public Producer(BlockingQueue<String> d) {
        this.drop = d;
    }

    public void run() {
        try {
            for (String s : messages) {
                drop.put(s);
                Thread.sleep(800);
            }

            drop.put("DONE");
        } catch (InterruptedException intEx) {
            System.out.println("Interrupted! " + "Last one out, turn out the lights!");
        }
    }
}

class Consumer implements Runnable {

    private BlockingQueue<String> drop;

    public Consumer(BlockingQueue<String> d) {
        this.drop = d;
    }

    public void run() {
        try {
            String msg = null;
            while (!((msg = drop.take()).equals("DONE"))) {
                System.out.println(msg);
            }
        } catch (InterruptedException intEx) {
            System.out.println("Interrupted! " + "Last one out, turn out the lights!");
        }
    }
}

/**
 * 经典生产者消费者
 * 
 * @author guojf
 * @version 1.0.0.2012-9-17
 */
public class ProducerConsumerPattern {

    public static void main(String[] args) {
        BlockingQueue<String> drop = new ArrayBlockingQueue<String>(1, true);
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
