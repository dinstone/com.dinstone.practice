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

package com.dinstone.practice.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LRUCache<K extends Comparable<? extends K>, V> implements Cache<K, V>, Serializable {

    private static final long serialVersionUID = 3674312987828041877L;

    Map<K, Item> m_map = Collections.synchronizedMap(new HashMap<K, Item>());

    Item m_start = new Item(); // 表头

    Item m_end = new Item(); // 表尾

    int m_maxSize;

    Object m_listLock = new Object(); // 用于并发的锁

    static class Item {

        public Item(Comparable k, Object v, long e) {

            key = k;

            value = v;

            expires = e;

        }

        public Item() {
        }

        public Comparable key; // 键值

        public Object value; // 对象

        public long expires; // 有效期

        public Item previous;

        public Item next;

    }

    void removeItem(Item item) {

        synchronized (m_listLock) {

            item.previous.next = item.next;

            item.next.previous = item.previous;

        }

    }

    void insertHead(Item item) {

        synchronized (m_listLock) {

            item.previous = m_start;

            item.next = m_start.next;

            m_start.next.previous = item;

            m_start.next = item;

        }

    }

    void moveToHead(Item item) {

        synchronized (m_listLock) {

            item.previous.next = item.next;

            item.next.previous = item.previous;

            item.previous = m_start;

            item.next = m_start.next;

            m_start.next.previous = item;

            m_start.next = item;

        }

    }

    public LRUCache(int maxObjects) {

        m_maxSize = maxObjects;

        m_start.next = m_end;

        m_end.previous = m_start;

    }

    @SuppressWarnings("unchecked")
    public Pair[] getAll() {

        Pair p[] = new Pair[m_maxSize];

        int count = 0;

        synchronized (m_listLock) {

            Item cur = m_start.next;

            while (cur != m_end) {

                p[count] = new Pair(cur.key, cur.value);

                ++count;

                cur = cur.next;

            }

        }

        Pair np[] = new Pair[count];

        System.arraycopy(p, 0, np, 0, count);

        return np;

    }

    @SuppressWarnings("unchecked")
    public V get(K key) {

        Item cur = m_map.get(key);

        if (cur == null) {

            return null;

        }

        // 过期则删除对象

        if (System.currentTimeMillis() > cur.expires) {

            m_map.remove(cur.key);

            removeItem(cur);

            return null;

        }

        if (cur != m_start.next) {

            moveToHead(cur);

        }

        return (V) cur.value;

    }

    public void put(K key, V obj) {

        put(key, obj, -1);

    }

    public void put(K key, V value, long validTime) {

        Item cur = m_map.get(key);

        if (cur != null) {

            cur.value = value;

            if (validTime > 0) {

                cur.expires = System.currentTimeMillis() + validTime;

            }

            else {

                cur.expires = Long.MAX_VALUE;

            }

            moveToHead(cur); // 成为最新的对象，移动到头部

            return;

        }

        if (m_map.size() >= m_maxSize) {

            cur = m_end.previous;

            m_map.remove(cur.key);

            removeItem(cur);

        }

        long expires = 0;

        if (validTime > 0) {

            expires = System.currentTimeMillis() + validTime;

        }

        else {

            expires = Long.MAX_VALUE;

        }

        Item item = new Item(key, value, expires);

        insertHead(item);

        m_map.put(key, item);

    }

    public void remove(K key) {

        Item cur = m_map.get(key);

        if (cur == null) {

            return;

        }

        m_map.remove(key);

        removeItem(cur);

    }

    public int size() {

        return m_map.size();

    }

}
