package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BatchConsumer {

    @KafkaListener(topics = "${kafka.simple-topic}", containerFactory = "batchContainerFactory")
    public void listen(@Payload List<String> messages,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) List<Long> timestamps,
                       @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        for (int i = 0; i < messages.size(); i++) {
            log.info("Key: {} \nPartition: {} \nTopic: {} \nOffset: {} \nTimestamp: {} \nMessage: [{}].",
                    keys.get(i), partitions.get(i), topics.get(i), timestamps.get(i), messages.get(i));
        }
    }
}
