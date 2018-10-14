package com.maxymkondratenko.rabbitlistener.infrastructure;

import com.maxymkondratenko.rabbitlistener.domain.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AmqpConfig {
    private final Props props;

    @Bean
    public Queue queue() {
        return new Queue(props.getQueueName(), false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(props.getExchangeName());
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(props.getRoutingKey());
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory factory,
                                                    MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(props.getQueueName());
        container.setMessageListener(adapter);
        return container;
    }

    @Bean
    @Autowired
    public MessageListenerAdapter listenerAdapter(Consumer consumer) {
        return new MessageListenerAdapter(consumer, props.getListenerMethod());
    }
}
