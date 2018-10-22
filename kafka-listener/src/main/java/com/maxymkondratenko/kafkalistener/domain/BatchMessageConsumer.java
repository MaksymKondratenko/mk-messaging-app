package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BatchMessageConsumer {

    @KafkaListener(topics = "${kafka.simple-topic}", containerFactory = "batchContainerFactory")
    public void listenMessagesInBatch(List<Message<?>> messages) {
        messages.forEach(msg -> log.info("Received Message object: [" + msg + "]."));
    }

    @KafkaListener(topics = "${kafka.simple-topic}", containerFactory = "batchContainerFactory")
    public void listenMessagesInBatchWithAcknowledgment(List<Message<?>> messages, Acknowledgment ack) {
        messages.forEach(msg -> log.info("Received Message object: [" + msg + "]."));
        ack.acknowledge();
        log.info("Acknowledged");
    }
}
