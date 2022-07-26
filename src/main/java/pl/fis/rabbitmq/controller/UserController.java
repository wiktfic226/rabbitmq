package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final AmqpTemplate amqpTemplate;

    @PostMapping
    public String createUser() {
        amqpTemplate.convertAndSend("user.topic.exchange", "user.example.userQueue", "USER_CREATED");
        return "User created!";
    }
}
