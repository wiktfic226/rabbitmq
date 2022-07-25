package pl.fis.rabbitmq.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/emailToUser")
public class EmailController {
    @PostMapping
    public String sendEmailToUser() {
        return "Send email to user";
    }
}
