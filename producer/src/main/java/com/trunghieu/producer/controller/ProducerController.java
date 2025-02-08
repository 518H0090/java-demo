package com.trunghieu.producer.controller;

import com.trunghieu.producer.stream.StreamDemoConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trunghieu/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final StreamDemoConsumer streamDemoConsumer;

    @PostMapping
    @RequestMapping("publish")
    public ResponseEntity<? extends HttpStatus> StartPublish() {
        streamDemoConsumer.ConsumerStreamAndPublish();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("publish-json")
    public ResponseEntity<? extends HttpStatus> StartPublishJson() {
        streamDemoConsumer.ConsumerStreamAndPublishJson();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
