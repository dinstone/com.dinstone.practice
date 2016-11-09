
package com.dinstone.practice.base;

public class Fibonacci {

    public static void main(String[] args) {
        fn(1000);
    }

    private static int fn(int n) {
        if (n <= 0) {
            System.out.print(" 0");
            return 0;
        } else if (n == 1) {
            System.out.print(" 1");
            return 1;
        }

        int pre2 = 0;
        int pre1 = 1;
        int current = 0;
        System.out.print(" 0");
        System.out.print(" 1");
        for (int i = 2; i <= n; i++) {
            current = pre2 + pre1;
            System.out.print(" " + current);
            pre2 = pre1;
            pre1 = current;
        }

        return current;
    }

}
