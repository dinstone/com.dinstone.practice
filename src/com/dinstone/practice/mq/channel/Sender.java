
package com.dinstone.practice.mq.channel;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection con = factory.createConnection();
            con.start();

            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue fq = session.createQueue("firstQueue");

            MessageProducer p = session.createProducer(fq);
            p.setDeliveryMode(DeliveryMode.PERSISTENT);

            while (true) {
                TextMessage message = session.createTextMessage();
                message.setText("message_" + System.currentTimeMillis());
                p.send(message);
                System.out.println("Sent message: " + message.getText());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }

            session.close();
            con.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
