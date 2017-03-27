package org.dist.openmq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Sender {
    public static void main(String[] args) {
    try {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        ((com.sun.messaging.ConnectionFactory)cf).setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList,"localhost:7676");
        Connection con = cf.createConnection();
        Session sn = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination dest = sn.createQueue("AQUEUE");
        MessageProducer mp = sn.createProducer(dest);
        TextMessage tm = sn.createTextMessage();
        tm.setText("2 test message");
        mp.send(tm);
        System.out.println("Message sent:");
        sn.close();
        con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}