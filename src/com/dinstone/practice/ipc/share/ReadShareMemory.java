
package com.dinstone.practice.ipc.share;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class ReadShareMemory {

    public static void main(String args[]) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("share.swap", "rw");
        FileChannel fc = raf.getChannel();
        int size = 28;
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, size);
        int lastIndex = 0;
        for (int i = 1; i < 27; i++) {
            // 0-可读无数据; 1-可写; 2-可读有数据
            int flag = mbb.get(0);
            int index = mbb.get(1);
            if (flag != 2 || index == lastIndex) {
                // 加入不可读，或未写入新数据时重复循环
                i--;
                continue;
            }
            lastIndex = index;
            System.out.println("程序 ReadShareMemory:" + System.currentTimeMillis() + ":位置: " + index + " 读出数据："
                    + (char) mbb.get(index));
            mbb.put(0, (byte) 0); // 置第一个字节为可读标志为0
            if (index == 27) { // 读完数据后退出
                break;
            }
        }
    }
}
