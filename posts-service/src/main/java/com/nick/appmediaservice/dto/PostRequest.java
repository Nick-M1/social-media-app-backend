package com.nick.appmediaservice.dto;

import com.nick.appmediaservice.model.Post;

import java.util.List;
import java.util.UUID;

public record PostRequest (
        String userId,
        String description,
        List<String> tags,
        List<String> images
) {
    public Post mapToPost(String postId) {
        return new Post(postId, userId, description, tags, images);
    }
}
