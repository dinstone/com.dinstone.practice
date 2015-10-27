
package com.dinstone.practice.concurrent;

public class ReadWriteLock {

    private int readingThreads = 0;

    private int writingThreads = 0;

    private int waitingThreads = 0; // waiting for write

    private boolean preferWrite = true;

    public synchronized void readLock() throws InterruptedException {
        while (writingThreads > 0 || (preferWrite && waitingThreads > 0)) {
            this.wait();
        }
        readingThreads++;
    }

    public synchronized void readUnlock() {
        readingThreads--;
        preferWrite = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingThreads++;
        try {
            while (readingThreads > 0 || writingThreads > 0) {
                this.wait();
            }
        } finally {
            waitingThreads--;
        }
        writingThreads++;
    }

    public synchronized void writeUnlock() {
        writingThreads--;
        preferWrite = false;
        notifyAll();
    }
}
