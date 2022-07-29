package pl.fis.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class GlobalConsumerConfig {
    @Bean
    public Consumer<String> createdConsumer(Tracer tracer) {
        return (msg) -> log.info("CREATED - Received message: {}, <TRACE:{}> ", msg, tracer.currentSpan().context().traceId());
    }

    @Bean
    public Consumer<String> updatedConsumer(Tracer tracer) {
        return (msg) -> log.info("UPDATED - Received message: {}, <TRACE:{}> ", msg, tracer.currentSpan().context().traceId());
    }

    @Bean
    public Consumer<String> deletedConsumer(Tracer tracer) {
        return (msg) -> log.info("DELETED - Received message: {}, <TRACE:{}> ", msg, tracer.currentSpan().context().traceId());
    }

    @Bean
    public Consumer<String> emailConsumer(Tracer tracer) {
        return (msg) -> log.info("EMAIL - Received message: {}, <TRACE:{}> ", msg, tracer.currentSpan().context().traceId());
    }

    @Bean
    public Consumer<String> modelConsumer(Tracer tracer) {
        return (msg) -> log.info("Received message: {}, <TRACE:{}> ", msg, tracer.currentSpan().context().traceId());
       // Uncomment to force message to fail and sent it to DLQ
//        {
//            throw new AmqpRejectAndDontRequeueException("failed");
//        };
    }

    @Bean
    public Consumer<String> dlqConsumer(Tracer tracer) {
        return (msg) -> log.info("DLQ - Received message from DLQ: {}", msg);
    }

}
