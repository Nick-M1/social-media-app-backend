package com.nick.socialgraphservice.service;

import com.nick.socialgraphservice.dto.CommentRequest;
import com.nick.socialgraphservice.model.Comment;
import com.nick.socialgraphservice.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public Mono<Comment> findCommentById(String commentId) {
        return commentRepository.findById(commentId);
    }

    public Mono<Long> countOfAllCommentsByPost(String postId) {
        return commentRepository.countOfAllCommentsByPost(postId);
    }
    public Mono<Long> countOfDirectCommentsByPost(String postId) {
        return commentRepository.countOfDirectCommentsByPost(postId);
    }
    public Flux<Comment> findAllCommentsByPost(String postId) {
        return commentRepository.findAllCommentsByPost(postId);
    }
    public Flux<Comment> findDirectCommentsByPost(String postId) {
        return commentRepository.findDirectCommentsByPost(postId);
    }
    public Mono<Long> countChildComments(String commentId) {
        return commentRepository.countChildComments(commentId);
    }
    public Mono<Comment> findParentComment(String commentId) {
        return commentRepository.findParentComment(commentId);
    }
    public Flux<Comment> findChildComments(String commentId) {
        return commentRepository.findChildComments(commentId);
    }
    public Flux<Comment> findCommentsByUser(String userId) {
        return commentRepository.findCommentsByUser(userId);
    }

    public Mono<Comment> createComment(CommentRequest commentRequest) {
        var parentComment = commentRequest.parentCommentId().flatMap(this::findCommentById);
        var post = postService.findPostById(commentRequest.postId());
        var user = userService.findUserByUserId(commentRequest.userId());

        return Mono.zip(parentComment, post, user)
                .map(tuple -> new Comment(commentRequest.description(), tuple.getT2(), tuple.getT1(), tuple.getT3()))
                .flatMap(commentRepository::save);
    }

    @Transactional
    public Mono<Comment> updateComment(String commentId, Optional<String> description, Optional<Boolean> isDeleted) {
        return findCommentById(commentId)
                .doOnNext(c -> description.ifPresent(c::setDescription))
                .doOnNext(c -> isDeleted.ifPresent(c::setIsDeleted));
    }
}
