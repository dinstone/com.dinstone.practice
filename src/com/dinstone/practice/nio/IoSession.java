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

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class IoSession {

    private static final AtomicLong IDGEN = new AtomicLong();

    private IoHandler handler;

    private SocketChannel channel;

    private long id;

    private Map<Object, Object> attributes = new ConcurrentHashMap<Object, Object>();

    private SelectionKey selectionKey;

    private IoProcessor processer;

    public IoSession(IoHandler handler, SocketChannel channel) {
        this.handler = handler;
        this.channel = channel;
        id = IDGEN.incrementAndGet();
    }

    public Object getAttribute(Object name) {
        return attributes.get(name);
    }

    public void setAttribute(Object name, Object processor) {
        attributes.put(name, processor);
    }

    public long getId() {
        return id;
    }

    public SocketChannel getSocketChannel() {
        return channel;
    }

    public IoHandler getHandler() {
        return handler;
    }

    public void close() {
        processer.remove(this);
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }

    public void setProcessor(IoProcessor processer) {
        this.processer = processer;
    }

}
