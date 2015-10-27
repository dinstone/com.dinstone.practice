/*
 * Copyright (C) 2011~2012 dinstone <dinstone@163.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.dinstone.practice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TcpEchoClientNonblocking {

    private static final int PORT = 8080;

    private static int c = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    doEcho();
                }
            });

            t.start();
        }
    }

    private static void doEcho() {
        try {
            SocketChannel csc = SocketChannel.open();
            csc.configureBlocking(false);

            SocketAddress remote = new InetSocketAddress("localhost", PORT);
            System.out.printf("connetting the server at %s:%s", "localhost", PORT).println();
            if (!csc.connect(remote)) {
                while (!csc.finishConnect()) {
                    System.out.print(".");
                }
            }

            byte[] content = new byte[26];
            for (int i = 0; i < 26; i++) {
                content[i] = (byte) (65 + i);
            }
            System.out.println("Content:" + new String(content, "ISO-8859-1"));

            for (int i = 0; i < 50; i++) {
                sendReceiveMessage(csc, content);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("current fail count is " + c);
            System.out.println("the connection will closed");
            csc.close();
        } catch (IOException e) {
            c++;
            e.printStackTrace();
        }
    }

    private static void sendReceiveMessage(SocketChannel csc, byte[] content) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(content);
        ByteBuffer readBuffer = ByteBuffer.allocate(26);

        int tbyteRcvd = 0;
        int lastRcvd = 0;
        while (tbyteRcvd < 26) {
            if (writeBuffer.hasRemaining()) {
                csc.write(writeBuffer);
            }

            if ((lastRcvd = csc.read(readBuffer)) == -1) {
                System.out.println("Connection closed prematurely");
                csc.close();
                break;
            }
            System.out.println("last received len is " + lastRcvd);
            System.out.println("total received len is " + lastRcvd);

            tbyteRcvd += lastRcvd;
            // System.out.println("doing other thing...");
        }

        System.out.println("Received:" + new String(readBuffer.array(), 0, tbyteRcvd));
    }
}
