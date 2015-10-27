
package com.dinstone.practice.jvm;

import java.nio.ByteBuffer;

public class JvmTest {

    public static void main(String[] args) throws Exception {
        System.out.println("start");
        for (int i = 0; i < 200; i++) {
            final ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024);// 1M
            bb.clear();
            System.out.println("allocat:" + i);
        }
        System.out.println("before sleep");

        Thread.sleep(600 * 1000);
    }
}
