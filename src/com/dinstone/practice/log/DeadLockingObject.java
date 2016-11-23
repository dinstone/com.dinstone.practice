
package com.dinstone.practice.log;

import org.apache.log4j.Logger;

public final class DeadLockingObject {

    private static final Logger LOG = Logger.getLogger(DeadLockingObject.class.getName());

    private static DeadLockingObject singleton = new DeadLockingObject();

    private DeadLockingObject() {
    }

    public synchronized static DeadLockingObject getInstance() {
        try {
            // to make the deadlock occur
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        LOG.info("Returning nice singleton");
        return singleton;

    }
}
