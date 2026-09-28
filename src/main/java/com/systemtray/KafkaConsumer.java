package com.systemtray;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    public KafkaConsumer() {}

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("\n============== Received: \n" + message);
        System.out.println("==============\n");
    }
}   