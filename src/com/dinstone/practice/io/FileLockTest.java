
package com.dinstone.practice.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {

    public static void main(String[] args) throws IOException {
        String file = "s.tmp";
        // 共享读锁
        sharedRead(file);

        // 排他锁
        excluse(file);

        // 共享写锁
        sharedWrite(file);
    }

    private static void sharedWrite(String file) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        FileLock lock = fout.getChannel().lock(0, Integer.MAX_VALUE, false);
        showLock(lock);
        lock.release();
    }

    private static void excluse(String file) throws FileNotFoundException, IOException {
        FileChannel fch = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = fch.lock();
        showLock(lock);
        lock.release();
    }

    private static void sharedRead(String file) throws FileNotFoundException, IOException {
        FileInputStream fin = new FileInputStream(file);
        FileLock lock = fin.getChannel().lock(0, Integer.MAX_VALUE, true);
        showLock(lock);
        lock.release();
    }

    private static void showLock(FileLock lock) {
        System.out.println("isValid = " + lock.isValid());
        System.out.println("isShared = " + lock.isShared());
    }

}
