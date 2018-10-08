package com.maxymkondratenko.rabbitlistener.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class Listener {
    @Getter
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
      log.info("Received message: [" + message + "].");
      latch.countDown();
    }
}
