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
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class IoProcessor {

    private static final AtomicInteger IDGEN = new AtomicInteger();

    private Selector selector;

    private ExecutorService executor;

    private Queue<IoSession> newSessions = new ConcurrentLinkedQueue<IoSession>();

    private Queue<IoSession> removeSessions = new ConcurrentLinkedQueue<IoSession>();

    private AtomicReference<Processor> processorRef = new AtomicReference<IoProcessor.Processor>();

    public IoProcessor(ExecutorService executor) {
        this.executor = executor;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Processor implements Runnable {

        public void run() {
            try {
                while (true) {
                    System.out.println("current managed session count is " + selector.keys().size());
                    selector.select(1000);

                    if (newSessions.size() > 0) {
                        for (IoSession session = newSessions.poll(); session != null; session = newSessions.poll()) {
                            SocketChannel channel = session.getSocketChannel();
                            channel.configureBlocking(false);
                            session.setSelectionKey(channel.register(selector, SelectionKey.OP_READ, session));
                        }
                    }

                    if (removeSessions.size() > 0) {
                        for (IoSession session = removeSessions.poll(); session != null; session = removeSessions
                            .poll()) {
                            session.getSocketChannel().close();
                            session.getSelectionKey().cancel();
                        }
                    }

                    Set<SelectionKey> skeys = selector.selectedKeys();
                    for (Iterator<SelectionKey> iterator = skeys.iterator(); iterator.hasNext();) {
                        SelectionKey sKey = iterator.next();
                        IoSession session = (IoSession) sKey.attachment();
                        IoHandler protocolHandler = session.getHandler();
                        if (sKey.isReadable()) {
                            protocolHandler.handleRead(session);
                        }

                        if (sKey.isValid() && sKey.isWritable()) {
                            protocolHandler.handleWrite(session);
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

    private void startProcessor() {
        Processor processor = processorRef.get();
        if (processor == null) {
            processor = new Processor();
            if (processorRef.compareAndSet(null, processor)) {
                String actualThreadName = "Processor" + '-' + IDGEN.incrementAndGet();
                executor.execute(new NamePreservingRunnable(processor, actualThreadName));
            }
        }

        selector.wakeup();
    }

    public void add(IoSession session) {
        newSessions.add(session);
        startProcessor();
    }

    public void remove(IoSession ioSession) {
        removeSessions.add(ioSession);
        startProcessor();
    }

    public void dispose() {
        // TODO Auto-generated method stub

    }
}
