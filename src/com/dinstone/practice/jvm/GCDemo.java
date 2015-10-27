
package com.dinstone.practice.jvm;

public class GCDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("start");
        Thread.sleep(20000);

        System.out.println("1/2m");
        Thread.sleep(8000);

        System.out.println("2/2m");
        Thread.sleep(8000);

        System.out.println("3/2m");
        Thread.sleep(30000);

        System.out.println("1/4m");
        Thread.sleep(30000);
        System.out.println("2/4m");
        Thread.sleep(30000);

        System.out.println("3/4m");
        Thread.sleep(30000);
    }
}
