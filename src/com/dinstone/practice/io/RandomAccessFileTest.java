
package com.dinstone.practice.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        String test = "sdsww112323212llsldlsldlsldlsldlsldsd";
        byte[] b = test.getBytes();
        File file = new File("raf.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream output = new FileOutputStream(file);
        output.write(b);
        output.flush();
        output.close();

        RandomAccessFile random = new RandomAccessFile(file, "rw");
        System.out.println(random.length());
        System.out.println(random.getFilePointer());
        random.seek(random.length());
        random.writeChars("XXX");// 6
        random.writeBoolean(true);// 1
        random.writeBoolean(false);// 1
        random.writeChar('a');// 2
        System.out.println(random.length());
        random.seek(0);
        System.out.println(random.readBoolean());
        System.out.println(random.readBoolean());
        System.out.println(random.readLine());
        random.close();
    }
}
