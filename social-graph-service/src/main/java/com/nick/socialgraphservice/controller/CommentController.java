package com.nick.socialgraphservice.controller;

import com.nick.socialgraphservice.dto.CommentRequest;
import com.nick.socialgraphservice.model.Comment;
import com.nick.socialgraphservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/social/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping(path = "{commentId}")
    public Mono<Comment> findCommentByCommentId(@PathVariable String commentId) {
        return commentService.findCommentById(commentId);
    }

    @PostMapping
    public Mono<Comment> createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }
}
