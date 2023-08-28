package com.nick.appmediaservice.dto;

public record UserRequest(
        String username,
        String firstName,
        String lastName,
        String profilePicture
) {
}
