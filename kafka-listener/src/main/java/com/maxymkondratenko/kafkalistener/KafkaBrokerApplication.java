package com.maxymkondratenko.kafkalistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaBrokerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaBrokerApplication.class, args);
    }
}
