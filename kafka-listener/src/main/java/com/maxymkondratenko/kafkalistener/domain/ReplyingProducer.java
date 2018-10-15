package com.maxymkondratenko.kafkalistener.domain;

import com.maxymkondratenko.kafkalistener.infrastructure.Props;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ReplyingProducer {
    private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;
    private final Props props;

    public List<ListenableFuture<SendResult<String, String>>> sendWithReply(String... messages) {
        RecordHeader header = new RecordHeader(KafkaHeaders.REPLY_TOPIC, props.getSentToTopic().getBytes());
        return Arrays
                .stream(messages)
                .map(msg -> "w/ reply: " + msg)
                .map(msg -> {
                    ProducerRecord<String, String> record = new ProducerRecord<>(props.getReplyTopic(), msg);
                    record.headers().add(header);
                    return record;
                })
                .map(replyingKafkaTemplate::sendAndReceive)
                .map(RequestReplyFuture::getSendFuture)
                .collect(toList());
    }
}
