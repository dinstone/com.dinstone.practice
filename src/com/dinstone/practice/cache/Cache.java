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

public interface Cache<K extends Comparable, V> {

    /**
     * 查询
     * 
     * @param obj
     * @return
     */
    V get(K obj);

    /**
     * 插入和更新
     * 
     * @param key
     * @param value
     */
    void put(K key, V value);

    void put(K key, V value, long expireTime);

    /**
     * 删除
     * 
     * @param key
     */
    void remove(K key);

    Pair[] getAll();

    int size();

}