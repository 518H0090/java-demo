package com.trunghieu.producer.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("trunghieu", message);
    }
}
