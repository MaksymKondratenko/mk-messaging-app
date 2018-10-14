package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id")
    public void receiveMessage(@Payload String message) {
        log.info("Received message: [" + message + "].");
    }
}
