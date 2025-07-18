package com.example.task.ActiveMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "Queue-mq")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @JmsListener(destination = "Queue-mq")
    public void receiveMessage2(String message) {
        System.out.println("Received message: " + message);
    }

}
