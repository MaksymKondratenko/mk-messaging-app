package com.maxymkondratenko.kafkalistener.infrastructure;

import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

@Configuration
@Setter
public class ReplyingConsumerConfig extends SimpleConsumerConfig {
    @Autowired
    private Props props;

    @Bean
    public KafkaMessageListenerContainer<String, String> replyContainer() {
        ContainerProperties containerProps = new ContainerProperties(props.getReplyTopic());
        return new KafkaMessageListenerContainer<>(consumerFactory(), containerProps);
    }

    @Bean
    public NewTopic nRequests() {
        return new NewTopic(props.getReplyTopic(), 2, (short) 2);
    }

    @Bean
    public NewTopic nReplies() {
        return new NewTopic(props.getReplyTopic(), 2, (short) 2);
    }
}
