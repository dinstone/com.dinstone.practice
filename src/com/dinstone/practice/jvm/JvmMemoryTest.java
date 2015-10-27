
package com.dinstone.practice.jvm;

import java.nio.ByteBuffer;

public class JvmMemoryTest {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        if ("a".equals(args[0])) {
            System.out.println("hello world");
            Thread.sleep(100000);
        } else {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
            System.out.println("start");
            Thread.sleep(30000);
            clean(buffer);
            System.out.println("end");
            Thread.sleep(100000);
        }
    }

    private static void clean(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
//            ((sun.nio.ch.DirectBuffer) byteBuffer).cleaner().clean();
        }
    }

}
