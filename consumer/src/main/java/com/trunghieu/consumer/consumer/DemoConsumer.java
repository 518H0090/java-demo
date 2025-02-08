package com.trunghieu.consumer.consumer;

import com.trunghieu.consumer.converter.JsonConverter;
import com.trunghieu.consumer.payload.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoConsumer {
    @KafkaListener(topics = "trunghieu", groupId = "demoGroup")
    public void listenJson(String record) {
        Student student = JsonConverter.convertStringToStudent(record);
        System.out.println(student);
    }
}
