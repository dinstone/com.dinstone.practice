
package com.dinstone.practice.concurrent;

import java.util.concurrent.CountDownLatch;

public class UnSync {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final ShareObject so = new ShareObject();
        so.count = 3;

        final CountDownLatch start = new CountDownLatch(1);

        Thread first = new Thread("first") {

            @Override
            public void run() {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                first(so);
            }
        };
        Thread second = new Thread("second") {

            @Override
            public void run() {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                second(so);
            }
        };

        first.start();
        second.start();

        start.countDown();

        Thread.sleep(1000);
    }

    private static void first(final ShareObject so) {
        if (so.count == 3) {
            so.count = 5;
            System.out.println("first :: set so.count = 5 , result = " + so.count);
        }

        if (so.count == 5) {
            so.count = 2;
            System.out.println("first :: set so.count = 2 , result = " + so.count);
        } else {
            so.count = 1;
            System.out.println("first :: set so.count = 1 , result = " + so.count);
        }
        System.out.println("first :: so.count = " + so.count);
    }

    private static void second(final ShareObject so) {
        if (so.count > 3) {
            so.count = 4;
            System.out.println("second :: set so.count = 4 , result = " + so.count);
        } else {
            so.count = 3;
            System.out.println("second :: set so.count = 3 , result = " + so.count);
        }

        System.out.println("second :: so.count = " + so.count);
    }

    static class ShareObject {

        int count;
    }

}
