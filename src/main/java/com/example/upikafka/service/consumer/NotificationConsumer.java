package com.example.upikafka.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "upi-transactions", groupId = "upi-group")
    public void consume(String message) {
        System.out.println("🔔 Notification: " + message);
    }
}


