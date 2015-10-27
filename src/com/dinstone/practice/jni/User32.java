
package com.dinstone.practice.jni;

import com.sun.jna.Library;

public interface User32 extends Library {

    boolean LockWorkStation();
}
