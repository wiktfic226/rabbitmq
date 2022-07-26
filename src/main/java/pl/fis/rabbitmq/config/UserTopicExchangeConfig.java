package pl.fis.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserTopicExchangeConfig {
    @Bean
    Queue userQueue() {
        return new Queue("userQueue", false);
    }

    @Bean
    Queue userQueue2() {
        return new Queue("userQueue2", false);
    }

    @Bean
    Queue allUserQueues() {
        return new Queue("allUserQueues", false);
    }

    @Bean
    TopicExchange userTopicExchange() {
        return new TopicExchange("user.topic.exchange");
    }

    @Bean
    Binding userBinding(Queue userQueue, TopicExchange userTopicExchange) {
        return BindingBuilder.bind(userQueue).to(userTopicExchange).with("user.example.userQueue");
    }


    @Bean
    Binding userBinding2(Queue userQueue2, TopicExchange userTopicExchange) {
        return BindingBuilder.bind(userQueue2).to(userTopicExchange).with("user.example.userQueue2");
    }

    @Bean
    Binding anotherUserBinding(Queue allUserQueues, TopicExchange userTopicExchange) {
        return BindingBuilder.bind(allUserQueues).to(userTopicExchange).with("user.#");
    }
}
