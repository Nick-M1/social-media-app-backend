package com.nick.userdetailsservice.dto;

import com.nick.userdetailsservice.model.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserInfoRequest (
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String profileImage
) {
    public UserInfo mapToUserInfo() {
        return new UserInfo(username, password, firstName, lastName, email, phoneNumber, profileImage);
    }
}
