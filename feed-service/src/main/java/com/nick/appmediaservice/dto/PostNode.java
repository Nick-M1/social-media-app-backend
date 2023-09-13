package com.nick.appmediaservice.dto;

public record PostNode (
        String postId,
        UserNode userCreatedBy
) {
}
