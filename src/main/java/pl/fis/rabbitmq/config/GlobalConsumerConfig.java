package pl.fis.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class GlobalConsumerConfig {
    @Bean
    public Consumer<String> createdConsumer() {
        return (msg) -> log.info("CREATED - Received message: {}", msg);
    }

    @Bean
    public Consumer<String> updatedConsumer() {
        return (msg) -> log.info("UPDATED - Received message: {}", msg);
    }

    @Bean
    public Consumer<String> deletedConsumer() {
        return (msg) -> log.info("DELETED - Received message: {}", msg);
    }
}
