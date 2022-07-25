package pl.fis.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class GlobalConsumerConfig {
    @Bean
    public Consumer<String> consumer() {
        return (msg) -> log.info("Received message: {}", msg);
    }
}
