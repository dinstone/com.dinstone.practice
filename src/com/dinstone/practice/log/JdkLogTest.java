
package com.dinstone.practice.log;

import java.util.logging.Logger;

public class JdkLogTest {

    private static final Logger LOG = Logger.getLogger(JdkLogTest.class.getName());

    /**
     * -Djava.util.logging.config.file=logging.properties
     * 
     * @param args
     */
    public static void main(String[] args) {
        LOG.finest("finest log");
        LOG.finer("finer log");
        LOG.fine("fine log");
        LOG.info("info log");
        LOG.warning("warning log");
        LOG.severe("severe log");
    }

}
