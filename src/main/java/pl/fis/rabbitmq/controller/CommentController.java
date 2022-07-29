package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fis.rabbitmq.enums.CommentMessage;

@RestController
@RequestMapping(path = "/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final StreamBridge streamBridge;
    private final Tracer tracer;

    @PostMapping
    public ResponseEntity<String> createComment() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("create-comment").start())) {
            log.info("Message sent: {}, TRACE: {}", CommentMessage.COMMENT_CREATED, this.tracer.currentSpan().context().traceId());
            streamBridge.send("commentCreated-out-0", CommentMessage.COMMENT_CREATED);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(CommentMessage.COMMENT_CREATED.name());
    }

    @PutMapping
    public ResponseEntity<String> updateComment() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("update-comment").start())) {
            log.info("Message sent: {}, TRACE: {}", CommentMessage.COMMENT_UPDATED, this.tracer.currentSpan().context().traceId());
            streamBridge.send("commentUpdated-out-0", CommentMessage.COMMENT_UPDATED);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(CommentMessage.COMMENT_UPDATED.name());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteComment() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("delete-comment").start())) {
            log.info("Message sent: {}, TRACE: {}", CommentMessage.COMMENT_DELETED, this.tracer.currentSpan().context().traceId());
            streamBridge.send("commentDeleted-out-0", CommentMessage.COMMENT_DELETED);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(CommentMessage.COMMENT_DELETED.name());
    }
}
