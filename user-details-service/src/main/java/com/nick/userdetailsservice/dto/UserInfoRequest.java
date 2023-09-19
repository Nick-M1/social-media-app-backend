package com.nick.userdetailsservice.dto;

import com.nick.userdetailsservice.model.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        String profileImage,
        @NotBlank
        String headerImage,
        @NotBlank
        String descriptionBio,
        @NotBlank
        String websiteUrl
) {
    public UserInfo mapToUserInfo(PasswordEncoder passwordEncoder) {
        return new UserInfo(username, passwordEncoder.encode(password), firstName, lastName, email, phoneNumber, profileImage, headerImage, descriptionBio, websiteUrl);
    }
}
