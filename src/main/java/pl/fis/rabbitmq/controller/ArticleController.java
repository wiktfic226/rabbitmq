package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fis.rabbitmq.enums.ArticleMessage;

@RestController
@RequestMapping(path = "/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final StreamBridge streamBridge;
    private final Tracer tracer;
    @PostMapping
    public ResponseEntity<String> createArticle() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("create-article").start())) {
            log.info("Message sent: {}, TRACE: {}", ArticleMessage.ARTICLE_CREATED, this.tracer.currentSpan().context().traceId());
            streamBridge.send("articleCreated-out-0", ArticleMessage.ARTICLE_CREATED);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(ArticleMessage.ARTICLE_CREATED.name());
    }

    @PutMapping
    public ResponseEntity<String> updateArticle() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("update-article").start())) {
            log.info("Message sent: {}, TRACE: {}", ArticleMessage.ARTICLE_UPDATED, this.tracer.currentSpan().context().traceId());
            streamBridge.send("articleUpdated-out-0", ArticleMessage.ARTICLE_UPDATED);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(ArticleMessage.ARTICLE_UPDATED.name());
    }
}
