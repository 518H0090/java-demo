package com.trunghieu.producer.producer;

import com.trunghieu.producer.payload.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoJsonProducer {

    private final KafkaTemplate<String, Student> kafkaTemplate;

    public void send(Student student) {

        Message<Student> message = MessageBuilder
                .withPayload(student)
                .setHeader(KafkaHeaders.TOPIC, "trunghieu")
                .build();

        kafkaTemplate.send(message);
    }
}
