package com.trunghieu.producer.stream;

import com.trunghieu.producer.payload.Student;
import com.trunghieu.producer.producer.DemoJsonProducer;
import com.trunghieu.producer.producer.DemoProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class StreamDemoConsumer {

    private final WebClient webClient;

    private final DemoProducer producer;

    private final DemoJsonProducer jsonProducer;

    public StreamDemoConsumer(WebClient.Builder webClientBuilder, DemoProducer producer, DemoJsonProducer jsonProducer) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8086/api/trunghieu/student")
                .build();
        this.producer = producer;
        this.jsonProducer = jsonProducer;
    }

    public void ConsumerStreamAndPublish(String token) {
        webClient.get()
                .uri("/students")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(producer::send);
    }

    public void ConsumerStreamAndPublishJson(String token) {
        webClient.get()
                .uri("/students")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(Student.class)
                .subscribe(jsonProducer::send);
    }
}
