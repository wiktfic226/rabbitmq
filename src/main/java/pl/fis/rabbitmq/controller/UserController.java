package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final StreamBridge streamBridge;

    @PostMapping
    public String createUser() {
        streamBridge.send("userCreated-out-0", "USER_CREATED");
        log.info("Message sent: USER_CREATED");
        return "User created!";
    }
}
