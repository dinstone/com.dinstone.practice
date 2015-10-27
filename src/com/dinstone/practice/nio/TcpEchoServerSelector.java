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
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TcpEchoServerSelector {

    public static void main(String[] args) {
        try {
            // create listening socket channel and register selector
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("server is listening on 8080");

            // create a handler that will implement the protocol
            ProtocolHandler protocol = new EchoProtocol(26);

            // run forever,processing available I/O operations
            while (true) {
                if (selector.select(3000) == 0) {
                    System.out.print(".");
                    continue;
                }

                Set<SelectionKey> skeys = selector.selectedKeys();
                for (Iterator<SelectionKey> iterator = skeys.iterator(); iterator.hasNext();) {
                    SelectionKey sKey = iterator.next();

                    if (sKey.isAcceptable()) {
                        protocol.handleAccept(sKey);
                    }

                    if (sKey.isReadable()) {
                        protocol.handleRead(sKey);
                    }

                    if (sKey.isValid() && sKey.isWritable()) {
                        protocol.handleWrite(sKey);
                    }

                    // remove from set of selected keys
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
