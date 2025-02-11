package com.trunghieu.producer.controller;

import com.trunghieu.producer.stream.StreamDemoConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trunghieu/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final StreamDemoConsumer streamDemoConsumer;

    @PostMapping
    @RequestMapping("publish")
    public ResponseEntity<? extends HttpStatus> StartPublish(@RequestHeader(name = "KeycloakToken") String token) {
        streamDemoConsumer.ConsumerStreamAndPublish(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("publish-json")
    public ResponseEntity<? extends HttpStatus> StartPublishJson(@RequestHeader(name = "KeycloakToken") String token) {
        streamDemoConsumer.ConsumerStreamAndPublishJson(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
