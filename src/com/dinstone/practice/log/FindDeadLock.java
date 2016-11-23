
package com.dinstone.practice.log;

import java.io.IOException;

import org.apache.log4j.Logger;

public class FindDeadLock {

    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {

            public void run() {
                Logger.getLogger(getClass()).info(new FindDeadLock());
            }
        }).start();
        DeadLockingObject.getInstance();

        System.out.println("ssssssss");

        System.in.read();
    }

    public String toString() {
        /* now we are inside log4j, try to create a DeadLockingObject */
        DeadLockingObject.getInstance();
        return "Created DeadlockObject, returning string";
    }
}
