package com.nick.socialgraphservice.dto;

import reactor.core.publisher.Mono;

public record CommentRequest(
        String userId,
        String postId,
        Mono<String> parentCommentId,
        String description
) {
}
