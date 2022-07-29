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
import pl.fis.rabbitmq.enums.EmailMessage;

@RestController
@RequestMapping(path = "/emailToUser")
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final StreamBridge streamBridge;
    private final Tracer tracer;
    @PostMapping
    public ResponseEntity<String> sendEmailToUser() {
        Span span = this.tracer.nextSpan();
        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.name("send-email").start())) {
            log.info("Message sent: {}, TRACE: {}", EmailMessage.SEND_EMAIL_TO_USER, this.tracer.currentSpan().context().traceId());
            streamBridge.send("sendEmailToUser-out-0", EmailMessage.SEND_EMAIL_TO_USER);
        } finally {
            span.end();
        }
        return ResponseEntity.ok().body(EmailMessage.SEND_EMAIL_TO_USER.name());
    }
}
