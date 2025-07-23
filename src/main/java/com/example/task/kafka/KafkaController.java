package com.example.task.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable String msg) {
        producerService.sendMessage(msg);
        return "Message sent: " + msg;
    }
}
