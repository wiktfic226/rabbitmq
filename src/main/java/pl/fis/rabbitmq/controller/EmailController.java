package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/emailToUser")
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final StreamBridge streamBridge;
    @PostMapping
    public String sendEmailToUser() {
        streamBridge.send("sendEmailToUser-out-0", "SEND_EMAIL_TO_USER");
        log.info("Message sent: SEND_EMAIL_TO_USER");
        return "Send email to user";
    }
}
