package com.trunghieu.consumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic TrungHieuTopic() {
        return TopicBuilder.name("trunghieu").build();
    }
}
