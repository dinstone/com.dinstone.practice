
package com.dinstone.practice.jvm;

import java.util.concurrent.CountDownLatch;

public class TestNativeOutOfMemoryError {

    public static void main(String[] args) {

        for (int i = 0;; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }

}

class HoldThread extends Thread {

    CountDownLatch cdl = new CountDownLatch(1);

    public HoldThread() {
        this.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
    }
}