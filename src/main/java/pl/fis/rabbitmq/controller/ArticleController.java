package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final StreamBridge streamBridge;
    @PostMapping
    public String createArticle() {
        streamBridge.send("articleCreated-out-0", "ARTICLE_CREATED");
        log.info("Message sent: ARTICLE_CREATED");
        return "Article created!";
    }

    @PutMapping
    public String updateArticle() {
        streamBridge.send("articleUpdated-out-0", "ARTICLE_UPDATED");
        log.info("Message sent: ARTICLE_UPDATED");
        return "Article updated!";
    }
}
