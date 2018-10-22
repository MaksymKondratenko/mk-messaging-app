package com.maxymkondratenko.kafkalistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BatchConsumerRecordConsumer {

    @KafkaListener(topics = "#{props.simpleTopic}", containerFactory = "batchContainerFactory")
    public void listenMessagesInBatch(List<ConsumerRecord<String, String>> records) {
        records.forEach(rec -> log.info("Received consumer record: [" + rec + "]."));
    }

    @KafkaListener(topics = "#{props.simpleTopic}", containerFactory = "batchContainerFactory")
    public void listenMessagesInBatchWithAcknowledgment(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        records.forEach(rec -> log.info("Received consumer record: [" + rec + "]."));
        ack.acknowledge();
        log.info("Acknowledged");
    }
}
