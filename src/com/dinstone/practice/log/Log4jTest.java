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

import org.apache.log4j.Logger;

/**
 * @author guojf
 * @version 1.0.0.2012-3-6
 */
public class Log4jTest {

    private static final Logger LOGGER = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        long st = System.currentTimeMillis();
        int wline = 100000;
        for (int i = 0; i < wline; i++) {
            LOGGER.warn("test log ============================================" + i);
        }
        long et = System.currentTimeMillis();

        System.out.println("log4j : " + (et - st) * 1000 / wline + " wps");

        //Slf4jTest.log();
    }

}
