package pl.fis.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleTopicExchangeConfig {
    @Bean
    Queue articleQueue() {
        return new Queue("articleQueue", false);
    }

    @Bean
    Queue articleQueue2() {
        return new Queue("articleQueue2", false);
    }

    @Bean
    Queue allArticleQueues() {
        return new Queue("allArticleQueues", false);
    }

    @Bean
    TopicExchange articleTopicExchange() {
        return new TopicExchange("article.topic.exchange");
    }

    @Bean
    Binding articleBinding(Queue articleQueue, TopicExchange articleTopicExchange) {
        return BindingBuilder.bind(articleQueue).to(articleTopicExchange).with("article.example.articleQueue");
    }

    @Bean
    Binding articleBinding2(Queue articleQueue2, TopicExchange articleTopicExchange) {
        return BindingBuilder.bind(articleQueue2).to(articleTopicExchange).with("article.example.*");
    }

    @Bean
    Binding articleBinding3(Queue allArticleQueues, TopicExchange articleTopicExchange) {
        return BindingBuilder.bind(allArticleQueues).to(articleTopicExchange).with("article.#");
    }
}
