package com.maxymkondratenko.kafkalistener.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/message")
@RequiredArgsConstructor
public class Controller {
    private final SimpleProducer simpleProducer;

    @PostMapping("/{messages}")
    public ResponseEntity<String[]> addNewMessage(@PathVariable("messages") String... messages) {
        simpleProducer.sendWoResponse(messages);
        return ResponseEntity.ok(messages);
    }
}
