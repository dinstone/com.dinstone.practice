
package com.dinstone.practice.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * Server Configure Notification Listener
 * 
 * @author haitao.tu
 */
public class ServerConfigureNotificationListener implements NotificationListener {

    public void handleNotification(Notification notification, Object handback) {
        log("SequenceNumber:" + notification.getSequenceNumber());
        log("Type:" + notification.getType());
        log("Message:" + notification.getMessage());
        log("Source:" + notification.getSource());
        log("TimeStamp:" + notification.getTimeStamp());
    }

    private void log(String message) {
        System.out.println(message);
    }

}
