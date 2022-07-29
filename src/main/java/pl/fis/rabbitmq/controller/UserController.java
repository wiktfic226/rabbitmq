package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fis.rabbitmq.enums.UserMessage;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final StreamBridge streamBridge;
    private final Tracer tracer;

    @PostMapping
    public ResponseEntity<String> createUser() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("create-user").start())) {
            log.info("Message sent: {}, TRACE: {}", UserMessage.USER_CREATED, this.tracer.currentSpan().context().traceId());
            streamBridge.send("userCreated-out-0", UserMessage.USER_CREATED);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(UserMessage.USER_CREATED.name());
    }
}
