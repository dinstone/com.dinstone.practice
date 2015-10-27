
package com.dinstone.practice.ipc.share;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class WriteShareMemory {

    public static void main(String args[]) throws Exception {
        // 创建共享交换文件
        RandomAccessFile raf = new RandomAccessFile("share.swap", "rw");

        int size = 28;
        // 获取共享文件内存映射
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, size);

        // clear content
        for (int i = 0; i < size; i++) {
            mbb.put(i, (byte) 0);
        }

        // 65-90 map ASCII Code A-Z
        for (int i = 65; i < 91; i++) {
            // 0-可读无数据; 1-可写; 2-可读有数据
            int flag = mbb.get(0);
            // 可读标志第一个字节为0
            if (flag != 0) {
                i--;
                continue;
            }

            int index = i - 63;
            mbb.put(0, (byte) 1);
            mbb.put(1, (byte) (index));
            mbb.put(index, (byte) i);
            mbb.put(0, (byte) 2);
            System.out.println("程序 WriteShareMemory:" + System.currentTimeMillis() + ": 位置: " + index + " 写入数据："
                    + (char) i);
            Thread.sleep(513);
        }
    }
}
