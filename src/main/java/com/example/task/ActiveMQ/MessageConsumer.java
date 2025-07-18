package com.example.task.ActiveMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "Queue-mq", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

}
