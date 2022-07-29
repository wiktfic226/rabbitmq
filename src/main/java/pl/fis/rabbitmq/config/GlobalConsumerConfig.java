package pl.fis.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class GlobalConsumerConfig {
    @Bean
    public Consumer<String> createdConsumer(Tracer tracer) {
        return (msg) -> log.info("<TRACE:{}> CREATED - Received message: {}", tracer.currentSpan().context().traceId(), msg);
    }

    @Bean
    public Consumer<String> updatedConsumer(Tracer tracer) {
        return (msg) -> log.info("<TRACE:{}> UPDATED - Received message: {}", tracer.currentSpan().context().traceId(), msg);
    }

    @Bean
    public Consumer<String> deletedConsumer(Tracer tracer) {
        return (msg) -> log.info("<TRACE:{}> DELETED - Received message: {}", tracer.currentSpan().context().traceId(), msg);
    }

    @Bean
    public Consumer<String> emailConsumer(Tracer tracer) {
        return (msg) -> log.info("<TRACE:{}> EMAIL - Received message: {}", tracer.currentSpan().context().traceId(), msg);
    }

    @Bean
    public Consumer<String> modelConsumer(Tracer tracer) {
        return (msg) ->
                log.info("<TRACE:{}> Received message: {}", tracer.currentSpan().context().traceId(), msg);
    }
}
