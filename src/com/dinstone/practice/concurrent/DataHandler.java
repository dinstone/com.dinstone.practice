
package com.dinstone.practice.concurrent;

public class DataHandler {

    // store data:
    private char[] buffer = "AAAAAAAAAA".toCharArray();

    // lock:
    private ReadWriteLock lock = new ReadWriteLock();

    public char[] read(String name) throws InterruptedException {
        System.out.println(name + " waiting for read...");
        lock.readLock();
        try {
            char[] data = doRead();
            System.out.println(name + " reads data: " + new String(data));
            return data;
        } finally {
            lock.readUnlock();
        }
    }

    public void write(String name, char[] data) throws InterruptedException {
        System.out.println(name + " waiting for write...");
        lock.writeLock();
        try {
            System.out.println(name + " wrote data: " + new String(data));
            doWrite(data);
        } finally {
            lock.writeUnlock();
        }
    }

    private char[] doRead() {
        char[] ret = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            ret[i] = buffer[i];
            sleep(3);
        }
        return ret;
    }

    private void doWrite(char[] data) {
        if (data != null) {
            buffer = new char[data.length];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = data[i];
                sleep(10);
            }
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
        }
    }
}
