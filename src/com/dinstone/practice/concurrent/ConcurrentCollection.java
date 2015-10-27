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

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author guojf
 * @version 1.0.0.2011-12-22
 */
public class ConcurrentCollection {

    private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

    private List<Integer> list = new ArrayList<Integer>();

    /**
     * @param args
     */
    public static void main(String[] args) {
         ConcurrentCollection cc = new ConcurrentCollection();
         concurrent(cc);
         common(cc);

        Queue<Integer> subJobs = new ConcurrentLinkedQueue<Integer>();
        subJobs.add(1);
        while (true) {
            Integer s = subJobs.poll();
            if (s == null) {
                break;
            }

            System.out.println("s = " + s);
            if (s < 20) {
                subJobs.add(s + 1);
            }
        }
    }

    private static void common(ConcurrentCollection cc) {
        cc.list.add(1);

        for (Integer key : cc.list) {
            if (key < 5) {
                cc.list.add(key + 4);
            }

            System.out.println(key);
        }

        for (Integer key : cc.list) {
            System.out.println(key);
        }
    }

    private static void concurrent(ConcurrentCollection cc) {
        cc.queue.add(1);

        for (Integer key : cc.queue) {
            if (key < 5) {
                cc.queue.add(key + 4);
            }

            // 存在
            if (cc.queue.contains(5)) {
                System.out.println("contains 5");
            }

            System.out.println("key=" + key);
            System.out.println("size=" + cc.queue.size());
        }
        System.out.println("===================");
        for (Integer key : cc.queue) {
            System.out.println(key);
        }
        System.out.println("===================");
    }
}
