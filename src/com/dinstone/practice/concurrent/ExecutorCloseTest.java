
package com.dinstone.practice.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorCloseTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // shutdown();

        terminate();
    }

    private static void terminate() {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 15; i++) {
            Runnable r = new ETask(i);
            executor.execute(r);
        }

        System.out.println("executor will close");
        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("executor isShutdown : " + executor.isShutdown());
        System.out.println("executor isTerminated : " + executor.isTerminated());
    }

    private static void shutdown() {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 15; i++) {
            Runnable r = new ETask(i);
            executor.execute(r);
        }

        System.out.println("executor will close");
        executor.shutdown();
        System.out.println("executor isShutdown : " + executor.isShutdown());
        System.out.println("executor isTerminated : " + executor.isTerminated());
    }

    static class ETask implements Runnable {

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

}
