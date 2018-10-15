package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReplyingConsumer {

    @KafkaListener(topics = "${kafka.reply-topic}", groupId = "${spring.kafka.consumer.group-id}")
    @SendTo("${kafka.sent-to-topic}")
    public void receiveAndReply(@Payload String message) {
        log.info("Received message: [" + message + "] and replied.");
    }

    @KafkaListener(topics = "${kafka.sent-to-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveReplied(@Payload String message) {
        log.info("Received replied message: [" + message + "].");
    }
}
