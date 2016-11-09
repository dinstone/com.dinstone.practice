
package com.dinstone.practice.io;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IODemo {

    public static void main(String[] args) throws Exception {
        String msg = "sssssssssss";

        streamWrite(msg);
        charWrite(msg);

        InputStream in = new FileInputStream("iodemo");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n = 0;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        out.close();
        msg = new String(out.toByteArray(), "utf-8");
    }

    private static void charWrite(String msg) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(getOutputstream(), "utf-8"));
        writer.write(msg);
        writer.flush();
        writer.close();
    }

    private static void streamWrite(String msg) throws FileNotFoundException, IOException {
        byte[] bytes = msg.getBytes("utf-8");
        OutputStream out = getOutputstream();
        // 一个字节一个字节循环输出,不推荐
        // for (byte b : bytes) {
        // out.write(b);
        // }

        // 一次全输出,推荐
        // out.write(bytes);

        // 输出一部分字节,,推荐
        out.write(bytes, 2, bytes.length - 1);

        out.flush();
        out.close();
    }

    private static FileOutputStream getOutputstream() throws FileNotFoundException {
        return new FileOutputStream("iodemo");
    }

}
