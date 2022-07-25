package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/article")
@RequiredArgsConstructor
public class ArticleController {

    private final StreamBridge streamBridge;
    @PostMapping
    public String createArticle() {
        streamBridge.send("article-out-0", "ARTICLE_CREATED");
        return "Article created!";
    }

    @PutMapping
    public String updateArticle() {
        streamBridge.send("article-out-0", "ARTICLE_UPDATED");
        return "Article updated!";
    }
}
