
package com.dinstone.practice.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class FileIoTest {

    public static void main(String[] args) throws Exception {
        final String file = "100M.db";
        int bas = 100 * 1024 * 1024;
        writeFile(file, bas);

        readFile(file);
    }

    private static void readFile(final String file) throws IllegalAccessException, InvocationTargetException {
        Method[] ms = FileRead.class.getMethods();
        for (Method method : ms) {
            if (Modifier.isStatic(method.getModifiers())) {
                System.out.print(method.getName());
                long start = System.currentTimeMillis();
                method.invoke(null, file);
                long time = System.currentTimeMillis() - start;
                System.out.println("\t" + time + " ms");
            }
        }
    }

    private static void writeFile(String file, int bas) throws FileNotFoundException, IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bs = new byte[] { 'a', 'b', 'c', 'd' };
        int length = bas / bs.length;
        for (int i = 0; i < length; i++) {
            out.write(bs);
        }

        out.close();
    }
}
