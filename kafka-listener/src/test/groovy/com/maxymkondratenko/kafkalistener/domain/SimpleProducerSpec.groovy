package com.maxymkondratenko.kafkalistener.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.lang.Void as Should

@SpringBootTest(properties = 'kafka.topic=test-topic')
class SimpleProducerSpec extends Specification {
    static final String MSG_1 = 'one'
    static final String MSG_2 = 'two'
    static final String MSG_3 = 'three'
    @Autowired
    SimpleProducer producer
    @Autowired
    SimpleConsumer consumer

    Should "work"() {
        println 'g'
        when:
        producer.sendWoResponse(MSG_1, MSG_2, MSG_3)
        Thread.sleep(3000)

        then:
        1 * consumer.simpleReceive(_)
        1 * consumer.simpleReceive(MSG_2)
        1 * consumer.simpleReceive(MSG_3)
    }

/*
    @Configuration
    static class Config {
        def factory = new DetachedMockFactory()

        @Bean
        SimpleProducer producerStub() {
            factory.Stub(SimpleProducer, name: 'simpleProducer') as SimpleProducer
        }

        @Bean
        Consumer consumerStub() {
            factory.Stub(Consumer)
        }
    }
*/
}
