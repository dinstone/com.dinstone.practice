
package com.dinstone.practice.jni;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class Hello {

    public static void main(String[] args) {
        CLibrary cl = (CLibrary) Native.loadLibrary(Platform.isWindows() ? "msvcrt" : "c", CLibrary.class);
        for (int i = 0; i < 10; i++) {
            cl.printf("Argument %d: %s\n", i, "a" + i);
        }
    }

}
