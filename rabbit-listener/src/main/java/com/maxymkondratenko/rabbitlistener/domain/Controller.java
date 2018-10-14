package com.maxymkondratenko.rabbitlistener.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amqp/message")
@RequiredArgsConstructor
public class Controller {
    private final Producer producer;

    @PostMapping("/{messages}")
    public ResponseEntity<String[]> addNewMessage(@PathVariable("messages") String... messages) {
        producer.send(messages);
        return ResponseEntity.ok(messages);
    }
}
