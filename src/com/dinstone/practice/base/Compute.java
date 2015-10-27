
package com.dinstone.practice.base;

import java.util.concurrent.CountDownLatch;

public class Compute {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int tc = Runtime.getRuntime().availableProcessors();
        System.out.println("available processors is " + tc);
        if (args.length > 0) {
            try {
                tc = Integer.parseInt(args[0]);
            } catch (Exception e) {
            }
        }
        System.out.println("thread count is " + tc);

        boolean showArg = true;
        if (args.length > 1) {
            try {
                showArg = Boolean.parseBoolean(args[1]);
            } catch (Exception e) {
            }
        }
        System.out.println("show log is " + showArg);

        final boolean showLog = showArg;

        final CountDownLatch doneLatch = new CountDownLatch(tc);
        final CountDownLatch startLatch = new CountDownLatch(1);

        for (int i = 0; i < tc; i++) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    try {
                        startLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        int c = 521;
                        for (long i = 0; i < 21000000000000000L; i++) {
                            c++;
                            c--;
                            if (showLog) {
                                System.out.println(c);
                                // LOG.info("c");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    doneLatch.countDown();
                }
            };
            t.start();
        }

        try {
            startLatch.countDown();
            long anyStart = System.currentTimeMillis();
            doneLatch.await();
            long anyEnd = System.currentTimeMillis();
            System.out.println("this case take " + (anyEnd - anyStart) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
