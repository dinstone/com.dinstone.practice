
package com.dinstone.practice.jni;

import com.sun.jna.Native;

public class LockWorkStation {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("User32.LockWorkStation()");
        User32 user32 = (User32) Native.loadLibrary("user32", User32.class);
        user32.LockWorkStation();
    }

}
