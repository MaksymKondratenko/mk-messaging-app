package com.maxymkondratenko.kafkalistener.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class ReplyingProducerConfig extends SimpleProducerConfig {

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(KafkaMessageListenerContainer<String, String> replyContainer) {
        return new ReplyingKafkaTemplate<>(producerFactory(), replyContainer);
    }
}
