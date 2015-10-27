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

package com.dinstone.practice.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/**
 * @author guojf
 * @version 1.0.0.2012-6-12
 */
public class EhcacheTest {

    private static final Logger logger = Logger.getLogger(EhcacheTest.class);

    private static Cache sampleCache = null;

    public static void main(String[] args) {
        init();
        test();
    }

    private static void test() {
        logger.info(sampleCache.getMemoryStoreEvictionPolicy().getName());
        for (int i = 0; i < 10; i++) {
            // 写入缓存
            sampleCache.put(new Element(i, "v" + i));
            // 打印当前缓存的所有值
            logger.info(sampleCache.getKeys());
            // 读取缓存
            Element e = sampleCache.get(i);
            logger.info(e.getValue());
        }
        // 打印命中统计
        logger.info(sampleCache.getStatistics());
    }

    private static void init() {
        CacheManager manager = CacheManager.create();
        // manager.addCache("sample"); //已经在配置文件定义过了
        sampleCache = manager.getCache("sample");
    }

}