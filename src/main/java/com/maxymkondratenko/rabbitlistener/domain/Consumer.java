package com.maxymkondratenko.rabbitlistener.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    public void receiveMessage(String message) {
        log.info("Received message: [" + message + "].");
    }
}
