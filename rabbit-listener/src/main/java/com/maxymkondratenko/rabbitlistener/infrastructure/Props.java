package com.maxymkondratenko.rabbitlistener.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "amqp")
@Getter
@Setter
public class Props {
    private String exchangeName;
    private String queueName;
    private String routingKey;
    private String listenerMethod;
    private String defaultMessage;
}
