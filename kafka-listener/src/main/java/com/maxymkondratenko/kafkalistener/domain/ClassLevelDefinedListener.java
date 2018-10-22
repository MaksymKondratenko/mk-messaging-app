package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
@KafkaListener(id = "classLevel", topics = "${kafka.specific-topic}")
public class ClassLevelDefinedListener {

    @KafkaHandler
    public void listenPayload(@Payload String payload) {
        log.info("Message payload: [{}]", payload);
    }

    @KafkaHandler
    public void listenMessage(Message<String> message) {
        log.info("Message timestamp: {} \nPayload: {}", message.getHeaders().getTimestamp(), message.getPayload());
    }
}
