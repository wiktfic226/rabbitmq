package pl.fis.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class ArticleConsumerConfig {
//    @Bean
//    public Consumer<String> articleConsumer() {
//        return (msg) -> log.info("Article consumer: Received message: {}", msg);
//    }
}
