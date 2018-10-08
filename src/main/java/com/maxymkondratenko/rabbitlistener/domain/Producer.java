package com.maxymkondratenko.rabbitlistener.domain;

import com.maxymkondratenko.rabbitlistener.infrastructure.Props;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final Props props;

    public void send(String... args) {
        Arrays.stream(args)
                .forEach(arg ->
                        rabbitTemplate.convertAndSend(props.getExchangeName(), props.getRoutingKey(), arg));
    }
}
