package com.nick.appmediaservice.dto;

public record UserNodeRelationshipFollow (
        UserNode followedUser,
        long createdAt
) {
}
