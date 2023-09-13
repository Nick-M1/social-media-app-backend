package com.nick.appmediaservice.dto;

import java.util.Set;

public record UserNode (
        String id,
        String userId,
        Set<UserNodeRelationshipFollow> following
) {
}
