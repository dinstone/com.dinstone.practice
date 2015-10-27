
package com.dinstone.practice.jmx;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Server Configure
 * 
 * @author haitao.tu
 */
public class ServerConfigure extends NotificationBroadcasterSupport implements ServerConfigureMBean {

    private AtomicLong sequenceNumber = new AtomicLong(1);

    private int port;

    private String host;

    public void setPort(int port) {
        int oldPort = this.port;
        this.port = port;
        AttributeChangeNotification notification = new AttributeChangeNotification(this,
            sequenceNumber.getAndIncrement(), System.currentTimeMillis(), "Server Port Change",
            AttributeChangeNotification.ATTRIBUTE_CHANGE, "java.lang.Integer", oldPort + "", this.port + "");
        super.sendNotification(notification);
    }

    public void setHost(String host) {
        String oldHost = this.host;
        this.host = host;
        AttributeChangeNotification notification = new AttributeChangeNotification(this,
            sequenceNumber.getAndIncrement(), System.currentTimeMillis(), "Server Host Change",
            AttributeChangeNotification.ATTRIBUTE_CHANGE, "java.lang.String", oldHost, this.host);
        super.sendNotification(notification);
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

}
