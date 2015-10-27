
package com.dinstone.practice.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileRead {

    private static final int SIZE = 8 * 1024;

    private static final int BIGSIZE = 8 * 1024;

    public static long fileInputStream(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        long checkSum = 0L;
        int b = 0;
        while ((b = f.read()) != -1) {
            checkSum += b;
        }
        f.close();
        return checkSum;
    }

    public static long fileInputStreamWithArray(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        int SIZE = 8 * 1024;
        byte[] barray = new byte[SIZE];
        long checkSum = 0L;
        int nRead;
        while ((nRead = f.read(barray, 0, SIZE)) != -1) {
            for (int i = 0; i < nRead; i++) {
                checkSum += barray[i];
            }
        }
        f.close();
        return checkSum;
    }

    public static long bufferedInputStream(String file) throws FileNotFoundException, IOException {
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
        int b;
        long checkSum = 0L;
        while ((b = f.read()) != -1) {
            checkSum += b;
        }
        f.close();
        return checkSum;
    }

    public static long bufferedInputStreamWithArray(String file) throws FileNotFoundException, IOException {
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
        int SIZE = 8 * 1024;
        byte[] barray = new byte[SIZE];
        long checkSum = 0L;
        int nRead;
        while ((nRead = f.read(barray, 0, SIZE)) != -1) {
            for (int i = 0; i < nRead; i++) {
                checkSum += barray[i];
            }
        }
        f.close();
        return checkSum;
    }

    public static long mappedByteBuffer(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        FileChannel ch = f.getChannel();
        MappedByteBuffer mb = ch.map(MapMode.READ_ONLY, 0L, ch.size());
        long checkSum = 0L;
        while (mb.hasRemaining()) {
            checkSum += mb.get();
        }

        ch.close();
        f.close();
        return checkSum;
    }

    public static long mappedByteBufferWithArray(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        FileChannel ch = f.getChannel();
        MappedByteBuffer mb = ch.map(MapMode.READ_ONLY, 0L, ch.size());
        byte[] barray = new byte[SIZE];
        long checkSum = 0L;
        int nGet;
        while (mb.hasRemaining()) {
            nGet = Math.min(mb.remaining(), SIZE);
            mb.get(barray, 0, nGet);
            for (int i = 0; i < nGet; i++) {
                checkSum += barray[i];
            }
        }

        ch.close();
        f.close();
        return checkSum;
    }

    public static long fileChannelWithDirectArray(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        FileChannel ch = f.getChannel();
        ByteBuffer bb = ByteBuffer.allocateDirect(128 * 1024);
        long checkSum = 0L;
        int nRead;
        while ((nRead = ch.read(bb)) != -1) {
            bb.position(0);
            bb.limit(nRead);
            while (bb.hasRemaining()) {
                checkSum += bb.get();
            }
            bb.clear();
        }

        ch.close();
        f.close();
        return checkSum;
    }

    public static long fileChannelWithWrapArray(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        FileChannel ch = f.getChannel();
        byte[] barray = new byte[SIZE];
        ByteBuffer bb = ByteBuffer.wrap(barray);
        long checkSum = 0L;
        int nRead;
        while ((nRead = ch.read(bb)) != -1) {
            for (int i = 0; i < nRead; i++) {
                checkSum += barray[i];
            }
            bb.clear();
        }

        ch.close();
        f.close();
        return checkSum;
    }

    public static long fileChannelWithArray(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        FileChannel ch = f.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(BIGSIZE);
        byte[] barray = new byte[SIZE];
        long checkSum = 0L;
        int nRead, nGet;
        while ((nRead = ch.read(bb)) != -1) {
            if (nRead == 0) {
                continue;
            }
            bb.position(0);
            bb.limit(nRead);
            while (bb.hasRemaining()) {
                nGet = Math.min(bb.remaining(), SIZE);
                bb.get(barray, 0, nGet);
                for (int i = 0; i < nGet; i++) {
                    checkSum += barray[i];
                }
            }
            bb.clear();
        }

        ch.close();
        f.close();
        return checkSum;
    }

    public static long fileChannel(String file) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(file);
        FileChannel ch = f.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(SIZE);
        long checkSum = 0L;
        int nRead;
        while ((nRead = ch.read(bb)) != -1) {
            if (nRead == 0) {
                continue;
            }
            bb.position(0);
            bb.limit(nRead);
            while (bb.hasRemaining()) {
                checkSum += bb.get();
            }
            bb.clear();
        }

        ch.close();
        f.close();
        return checkSum;
    }

    public static long randomAccessFileWithArray(String file) throws FileNotFoundException, IOException {
        RandomAccessFile f = new RandomAccessFile(file, "r");
        int SIZE = 8 * 1024;
        byte[] barray = new byte[SIZE];
        long checkSum = 0L;
        int nRead;
        while ((nRead = f.read(barray, 0, SIZE)) != -1) {
            for (int i = 0; i < nRead; i++) {
                checkSum += barray[i];
            }
        }

        f.close();
        return checkSum;
    }

    public static long randomAccessFile(String file) throws FileNotFoundException, IOException {
        RandomAccessFile f = new RandomAccessFile(file, "r");
        int b;
        long checkSum = 0L;
        while ((b = f.read()) != -1) {
            checkSum += b;
        }
        f.close();
        return checkSum;
    }
}
