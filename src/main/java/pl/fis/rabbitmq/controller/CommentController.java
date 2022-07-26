package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final AmqpTemplate amqpTemplate;

    @PostMapping
    public String createComment() {
        amqpTemplate.convertAndSend("comment.topic.exchange", "comment.example.commentQueue", "COMMENT_CREATED");
        return "Comment created!";
    }

    @PutMapping
    public String updateComment() {
        amqpTemplate.convertAndSend("comment.topic.exchange", "comment.example.commentQueue2", "COMMENT_UPDATED");
        return "Comment updated!";
    }

    @DeleteMapping
    public String deleteComment() {
        amqpTemplate.convertAndSend("comment.topic.exchange", "comment.example.commentQueue", "COMMENT_DELETED");
        return "Comment deleted!";
    }
}
