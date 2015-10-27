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

package com.dinstone.practice.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SEVERE(highest value) WARNING INFO CONFIG FINE FINER FINEST(lowest value)
 * 
 * @author guojf
 * @version 1.0.0.2013-5-21
 */
public class JDK14LogTest {

    /**
     * SEVERE(highest value) WARNING INFO CONFIG FINE FINER FINEST(lowest value)
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个INFO级别的Handler
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);

        // Root logger
        Logger rootLogger = Logger.getLogger("");
        showLogger(rootLogger);

        Logger firstLogger = Logger.getLogger("first");
        // 设置firstLogger级别为null，让firstLogger继承父级别
        // 默认RootLogger的级别为INFO
        firstLogger.setLevel(null);
        showLogger(firstLogger);

        Logger middleLogger = Logger.getLogger("first.middle");
        // 添加一个INFO级别的Handler
        middleLogger.addHandler(handler);
        // 设置middleLogger的日志级别为关闭
        middleLogger.setLevel(Level.OFF);
        middleLogger.addHandler(handler);
        showLogger(middleLogger);

        Logger lastLogger = Logger.getLogger("first.middle.last");
        lastLogger.setLevel(Level.FINE);
        showLogger(lastLogger);

        firstLogger.info("first info");
        middleLogger.info("middle info");

        // lastLogger的INFO级别的日志输出3遍
        lastLogger.info("last info");
        // lastLogger的级别是Fine,但是handler的级别是INFO,所以不会输出
        lastLogger.fine("last fine");

        // 不使用父级Handler
        lastLogger.setUseParentHandlers(false);
        lastLogger.info("last info again");
    }

    private static void showLogger(Logger logger) {
        String name = logger.getName();
        if ("".equals(name)) {
            name = "Root";
        }
        Logger parent = logger.getParent();
        String pname = null;
        if (parent != null) {
            if ("".equals(parent.getName())) {
                pname = "Root";
            } else {
                pname = parent.getName();
            }
        }

        System.out.println("[" + name + "] Logger Parent\t: " + pname);
        System.out.println("[" + name + "] Logger Level\t: " + logger.getLevel());

        Handler[] hs = logger.getHandlers();
        System.out.println("[" + name + "] Logger Handlers\t: " + hs.length);
        System.out.println("----------------------------------------------");
        for (Handler h : hs) {
            System.out.println(h + " : " + h.getLevel());
        }
        System.out.println("==============================================");
    }
}
