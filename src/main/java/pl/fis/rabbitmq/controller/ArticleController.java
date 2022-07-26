package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/article")
@RequiredArgsConstructor
public class ArticleController {

    private final AmqpTemplate amqpTemplate;
    @PostMapping
    public String createArticle() {
        amqpTemplate.convertAndSend("article.topic.exchange", "article.example.*", "ARTICLE_CREATED");
        return "Article created!";
    }

    @PutMapping
    public String updateArticle() {
        amqpTemplate.convertAndSend("article.topic.exchange", "article.example.articleQueue", "ARTICLE_UPDATED");
        return "Article updated!";
    }
}
