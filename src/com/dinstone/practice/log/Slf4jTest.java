
package com.dinstone.practice.log;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {

    private static final Logger LOG = LoggerFactory.getLogger(Slf4jTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        Logger plog = LoggerFactory.getLogger("com.dinstone.practice.log");
        System.out.println(plog.getClass().getName());

        org.apache.log4j.Logger l4jroot = org.apache.log4j.Logger.getRootLogger();
        l4jroot.setLevel(Level.INFO);

        org.apache.log4j.Logger l4jpk = org.apache.log4j.Logger.getLogger("com.dinstone.practice.log");
        l4jpk.setLevel(Level.WARN);

        LOG.debug("debug ...");
        LOG.info("info ...");
        LOG.warn("warn ...");
        LOG.error("error ...");
    }

    public static void log() {
        LOG.debug("debug ...");
        LOG.info("info ...");
        LOG.warn("warn ...");
        LOG.error("error ...");
    }
}
