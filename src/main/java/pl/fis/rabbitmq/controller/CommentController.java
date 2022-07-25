package pl.fis.rabbitmq.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {
    @PostMapping
    public String createComment() {
        return "Comment created!";
    }

    @PutMapping
    public String updateComment() {
        return "Comment updated!";
    }

    @DeleteMapping
    public String deleteComment() {
        return "Comment deleted!";
    }
}
