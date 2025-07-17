package com.example.task.ActiveMQ;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MQController {

    private final MessageProducer producer;

    public MQController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        producer.sendMessage("test-queue", msg);
        return "Message sent to ActiveMQ!";
    }
}
