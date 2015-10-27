
package com.dinstone.practice.jvm;

public class ThreadStackSize {

    private static long c = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
        recursiveFunction(4800);
    }

    private static void recursiveFunction(long n) {
        if (c < n) {
            System.out.println(c++);
            recursiveFunction(n);
        }
    }

}
