
package com.dinstone.practice.base;

public class BaseTest {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            int index = count++ % 5;
            System.out.println(index);
        }

    }

}
