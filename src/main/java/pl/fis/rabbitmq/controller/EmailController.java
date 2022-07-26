package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/emailToUser")
@RequiredArgsConstructor
public class EmailController {
    private final StreamBridge streamBridge;
    @PostMapping
    public String sendEmailToUser() {
        streamBridge.send("sendEmailToUser-out-0", "SEND_EMAIL_TO_USER");
        return "Send email to user";
    }
}
