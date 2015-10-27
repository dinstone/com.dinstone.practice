
package com.dinstone.practice.ipc.pipeline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PipeLineLaucher {

    private static class Receiver extends Thread {

        private InputStream in;

        public Receiver(InputStream inputStream) {
            this.in = inputStream;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println("received " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Sender extends Thread {

        private OutputStream out;

        public Sender(OutputStream outputStream) {
            this.out = outputStream;
        }

        @Override
        public void run() {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                int i = 0;
                while (i < 1000) {
                    String message = i + "";
                    writer.write(message);
                    writer.newLine();
                    writer.flush();

                    System.out.println("send " + i);

                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        try {
            File dir = new File("bin");
            System.out.println(dir.getAbsolutePath());

            Process process = Runtime.getRuntime().exec("java com.dinstone.practice.ipc.pipeline.SubProcess",
                new String[0], dir);

            new Receiver(process.getErrorStream()).start();

            new Receiver(process.getInputStream()).start();

            new Sender(process.getOutputStream()).start();

            System.out.println("MainProcess wait for SubProcess");
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
