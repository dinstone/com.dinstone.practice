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
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class IoAcceptor {

    private static final AtomicInteger ID = new AtomicInteger(0);

    private IoHandler handler;

    private ExecutorService executor;

    private Acceptor acceptor;

    private IoProcessor[] processerPool;

    private Selector selector;

    private volatile boolean selectable;

    private String threadName;

    private ServerSocketChannel severSocketChannel;

    private InetSocketAddress bindAddress;

    private boolean disposing;

    public IoAcceptor() {
        // 初始化线程池
        executor = Executors.newCachedThreadPool();

        // 初始化IoProcessor池
        int size = Runtime.getRuntime().availableProcessors() + 1;
        processerPool = new IoProcessor[size];
        for (int i = 0; i < size; i++) {
            processerPool[i] = new IoProcessor(executor);
        }

        // 初始化选择器
        try {
            selector = Selector.open();
            selectable = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize IoAcceptor.", e);
        }
    }

    public void setHandler(IoHandler handler) {
        this.handler = handler;
    }

    public void bind(InetSocketAddress localAddress) throws IOException {
        // 检查参数
        if (localAddress == null) {
            throw new IllegalArgumentException("localAddress");
        }

        // 检查handler是否设置
        if (handler == null) {
            throw new IllegalStateException("handler is not set.");
        }

        // 检查Acceptor
        if (acceptor != null) {
            throw new IllegalStateException("acceptor is running.");
        }

        acceptor = new Acceptor();
        // 初始化Acceptor线程名称
        threadName = getClass().getSimpleName() + '-' + ID.incrementAndGet();
        executor.execute(new NamePreservingRunnable(acceptor, threadName));

        // bind
        this.bindAddress = localAddress;
        selector.wakeup();

        System.out.println("Server is listening " + localAddress);
    }

    public void unbind() throws IOException {
        if (severSocketChannel != null) {
            SelectionKey skey = severSocketChannel.keyFor(selector);
            if (skey != null) {
                skey.cancel();
            }
            severSocketChannel.close();
            severSocketChannel = null;
        }

        selector.wakeup();
    }

    public final void dispose() {
        try {
            unbind();

            disposing = true;

            executor.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void destroy() throws IOException {
        if (selector != null) {
            selector.close();
        }
    }

    private IoProcessor getProcessor(IoSession session) {
        IoProcessor processor = (IoProcessor) session.getAttribute(IoProcessor.class.getName());
        if (processor == null) {
            int index = Math.abs((int) session.getId() % processerPool.length);
            processor = processerPool[index];
        }

        return processor;
    }

    private void openChannel() throws IOException {
        if (this.bindAddress != null) {
            // create listening socket channel and register selector
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(this.bindAddress, 500);
            ssc.configureBlocking(false);

            ssc.register(selector, SelectionKey.OP_ACCEPT);

            // 打开了之后就清空
            this.bindAddress = null;
            this.severSocketChannel = ssc;
        }
    }

    class Acceptor implements Runnable {

        public void run() {
            while (selectable) {
                try {
                    selector.select();

                    // 打开channel
                    openChannel();

                    if (severSocketChannel == null) {
                        acceptor = null;
                        // unbind,break
                        break;
                    }

                    Set<SelectionKey> skeys = selector.selectedKeys();
                    System.out.println("accepted socket count is " + skeys.size());
                    for (Iterator<SelectionKey> iterator = skeys.iterator(); iterator.hasNext();) {
                        SelectionKey sKey = iterator.next();

                        if (sKey.isAcceptable()) {
                            SocketChannel sc = ((ServerSocketChannel) sKey.channel()).accept();
                            IoSession session = new IoSession(handler, sc);
                            IoProcessor processer = getProcessor(session);
                            session.setProcessor(processer);
                            processer.add(session);
                        }

                        // remove from set of selected keys
                        iterator.remove();
                    }

                } catch (ClosedChannelException e) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 清理资源
            if (selectable && disposing) {
                selectable = false;
                try {
                    for (int i = 0; i < processerPool.length; i++) {
                        processerPool[i].dispose();
                    }
                } finally {
                    try {
                        destroy();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
