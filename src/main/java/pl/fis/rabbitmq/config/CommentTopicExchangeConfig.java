package pl.fis.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentTopicExchangeConfig {
    @Bean
    Queue commentQueue() {
        return new Queue("commentQueue", false);
    }

    @Bean
    Queue commentQueue2() {
        return new Queue("commentQueue2", false);
    }

    @Bean
    Queue allCommentQueues() {
        return new Queue("allCommentQueues", false);
    }

    @Bean
    TopicExchange commentTopicExchange() {
        return new TopicExchange("comment.topic.exchange");
    }

    @Bean
    Binding commentBinding(Queue commentQueue, TopicExchange commentTopicExchange) {
        return BindingBuilder.bind(commentQueue).to(commentTopicExchange).with("comment.example.commentQueue");
    }

    @Bean
    Binding commentBinding2(Queue commentQueue2, TopicExchange commentTopicExchange) {
        return BindingBuilder.bind(commentQueue2).to(commentTopicExchange).with("comment.example.*");
    }

    @Bean
    Binding commentBinding3(Queue allCommentQueues, TopicExchange commentTopicExchange) {
        return BindingBuilder.bind(allCommentQueues).to(commentTopicExchange).with("comment.#");
    }
}
