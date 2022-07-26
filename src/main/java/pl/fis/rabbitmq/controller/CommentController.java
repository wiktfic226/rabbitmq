package pl.fis.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final StreamBridge streamBridge;

    @PostMapping
    public String createComment() {
        streamBridge.send("commentCreated-out-0", "COMMENT_CREATED");
        return "Comment created!";
    }

    @PutMapping
    public String updateComment() {
        streamBridge.send("commentUpdated-out-0", "COMMENT_UPDATED");
        return "Comment updated!";
    }

    @DeleteMapping
    public String deleteComment() {
        streamBridge.send("commentDeleted-out-0", "COMMENT_DELETED");
        return "Comment deleted!";
    }
}
