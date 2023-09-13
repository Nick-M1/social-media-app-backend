package com.nick.appmediaservice.dto;

public record UserDetails (
        String id,
        long createdAt,
        long modifiedAt,
        String username,
        String profileImage
) {
}
