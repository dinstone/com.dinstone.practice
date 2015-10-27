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
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class EchoProtocol implements ProtocolHandler {

    private int bufSize;

    public EchoProtocol(int bufSize) {
        super();
        this.bufSize = bufSize;
    }

    public void handleAccept(SelectionKey skey) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) skey.channel()).accept();
        sc.configureBlocking(false);
        sc.register(skey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    public void handleRead(SelectionKey skey) throws IOException {
        ByteBuffer buf = (ByteBuffer) skey.attachment();
        SocketChannel sc = (SocketChannel) skey.channel();
        int len = sc.read(buf);
        System.out.println("read len is " + len);
        if (len == -1) {
            sc.close();
        } else if (len > 0) {
            skey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    public void handleWrite(SelectionKey skey) throws IOException {
        ByteBuffer buf = (ByteBuffer) skey.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) skey.channel();
        int len = sc.write(buf);
        System.out.println("write len is " + len);
        if (!buf.hasRemaining()) {
            skey.interestOps(SelectionKey.OP_READ);
        }
        buf.compact();
    }
}
