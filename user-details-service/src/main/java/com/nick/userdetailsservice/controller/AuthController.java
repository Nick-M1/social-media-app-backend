package com.nick.userdetailsservice.controller;

import com.nick.userdetailsservice.dto.UserInfoRequest;
import com.nick.userdetailsservice.model.UserInfo;
import com.nick.userdetailsservice.service.UserInfoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserInfoService userInfoService;

    @GetMapping
    public UserInfo getUserInfoByUsername(@RequestParam @NotBlank String username) {
        return userInfoService.getUserByUsername(username);
    }

    @PostMapping
    private UserInfo createUserInfo(@RequestBody @Valid UserInfoRequest userInfoRequest) {
        return userInfoService.createUser(userInfoRequest);
    }
}
