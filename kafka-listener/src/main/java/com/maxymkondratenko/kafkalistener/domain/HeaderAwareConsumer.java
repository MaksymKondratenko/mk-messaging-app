package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HeaderAwareConsumer {

    @KafkaListener(topics = "${kafka.simple-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void simpleReceive(@Payload String message,
                              @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                              @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        log.info("Key: {} \nPartition: {} \nTopic: {} \nTimestamp: {} \nMessage: [{}].", key, partition, topic, ts, message);
    }
}
