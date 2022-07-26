package pl.fis.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class GlobalConsumerConfig {
    @RabbitListener(queues = {"userQueue", "userQueue2"})
    public void listenOnUserQueues(String in) {
        log.info("User: Received message: {}", in);
    }

    @RabbitListener(queues = {"articleQueue", "articleQueue2"})
    public void listenOnArticleQueues(String in) {
        log.info("Article: Received message: {}", in);
    }

    @RabbitListener(queues = {"commentQueue", "commentQueue2"})
    public void listenOnCommentQueues(String in) {
        log.info("Comment: Received message: {}", in);
    }
}
