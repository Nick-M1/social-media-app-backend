package com.nick.userdetailsservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "user_email_unique", columnNames = "email") })
public class UserInfo {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String profileImage;
    @NotBlank
    private String headerImage;
    @NotBlank
    private String descriptionBio;
    @NotBlank
    private String websiteUrl;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public UserInfo(String username, String password, String firstName, String lastName, String email, String phoneNumber, String profileImage, String headerImage, String descriptionBio, String websiteUrl) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.headerImage = headerImage;
        this.descriptionBio = descriptionBio;
        this.websiteUrl = websiteUrl;
    }
}


