package com.maxymkondratenko.kafkalistener.domain;

import com.maxymkondratenko.kafkalistener.infrastructure.Props;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Props props;

    public void send(String... messages) {
        Arrays.stream(messages)
                .forEach(msg -> kafkaTemplate.send(props.getTopic(), msg));
    }
}
