package com.maxymkondratenko.rabbitlistener.domain;

import com.maxymkondratenko.rabbitlistener.infrastructure.Props;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final Consumer consumer;
    private final Props props;

    public void send(String... args) throws Exception {
        Arrays.stream(args)
                .forEach(arg ->
                        rabbitTemplate.convertAndSend(props.getQueueName(), props.getRoutingKey(), arg));
        consumer.getLatch().await(5000, TimeUnit.MILLISECONDS);
    }
}
