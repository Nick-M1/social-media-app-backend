package com.nick.socialgraphservice.dto;

import com.nick.socialgraphservice.model.User;

public record UserRequest(
        String userId
) {
    public User mapToUser() {
        return new User(userId);
    }
}
