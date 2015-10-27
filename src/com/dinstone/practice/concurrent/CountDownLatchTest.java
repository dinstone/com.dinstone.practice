
package com.dinstone.practice.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {
        System.out.println("start");
        final CountDownLatch cdl = new CountDownLatch(1);
        Thread t = new Thread("cdl") {

            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            };
        };
        t.start();

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }

}
