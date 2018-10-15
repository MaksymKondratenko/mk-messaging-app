package com.maxymkondratenko.kafkalistener.domain;

import com.maxymkondratenko.kafkalistener.infrastructure.Props;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class SimpleProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Props props;

    public void sendWoResponse(String... messages) {
        Arrays.stream(messages)
                .map(msg -> "wo/ response: " + msg)
                .forEach(msg -> kafkaTemplate.send(props.getSimpleTopic(), msg));
    }

    public List<ListenableFuture<SendResult<String, String>>> sendWithResponse(String... messages) {
        return Arrays
                .stream(messages)
                .map(msg -> "w/ response: " + msg)
                .map(msg -> kafkaTemplate.send(props.getSimpleTopic(), msg))
                .collect(toList());
    }

    public List<ListenableFuture<SendResult<String, String>>> sendInTransaction(String... messages) {
        String topic = props.getSimpleTopic();
        return kafkaTemplate.executeInTransaction(kafka ->
                Arrays
                        .stream(messages)
                        .map(msg -> "w/ in transaction: " + msg)
                        .map(msg -> kafka.send(topic, msg))
                        .collect(toList())
        );
    }
}
