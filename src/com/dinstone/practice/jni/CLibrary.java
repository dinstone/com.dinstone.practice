
package com.dinstone.practice.jni;

import com.sun.jna.Library;

public interface CLibrary extends Library {

    void printf(String format, Object... args);

}
