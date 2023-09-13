package com.nick.appmediaservice.dto;

import java.util.List;

public record Post (
        String id,
        long createdAt,
        long modifiedAt,
        String userId,
        String description,
        List<String> tags,
        List<String> images
) {
}
