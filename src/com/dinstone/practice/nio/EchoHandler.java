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
import java.nio.channels.SocketChannel;

public class EchoHandler implements IoHandler {

    private int bufSize;

    public EchoHandler(int bufSize) {
        this.bufSize = bufSize;
    }

    public void handleRead(IoSession session) throws IOException {
        SocketChannel sc = session.getSocketChannel();
        ByteBuffer buf = getBuffer(session);
        int len = sc.read(buf);
        System.out.println("read len is " + len);
        if (len == -1) {
            session.close();
        } else if (len > 0) {
            session.getSelectionKey().interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    private ByteBuffer getBuffer(IoSession session) {
        ByteBuffer buf = (ByteBuffer) session.getAttribute("buffer");
        if (buf == null) {
            buf = ByteBuffer.allocate(bufSize);
            session.setAttribute("buffer", buf);
        }
        return buf;
    }

    public void handleWrite(IoSession session) throws IOException {
        ByteBuffer buf = getBuffer(session);
        buf.flip();

        SocketChannel sc = session.getSocketChannel();
        int len = sc.write(buf);
        System.out.println("write len is " + len);

        if (!buf.hasRemaining()) {
            session.getSelectionKey().interestOps(SelectionKey.OP_READ);
        }

        buf.compact();
    }

}
