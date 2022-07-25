package pl.fis.rabbitmq.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {
    @PostMapping
    public String createArticle() {
        return "Article created!";
    }

    @PutMapping
    public String updateArticle() {
        return "Article updated!";
    }
}
