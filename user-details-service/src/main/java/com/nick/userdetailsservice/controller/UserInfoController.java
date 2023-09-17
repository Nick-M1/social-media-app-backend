package com.nick.userdetailsservice.controller;

import com.nick.userdetailsservice.model.UserInfo;
import com.nick.userdetailsservice.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/user/info")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("{userId}")
    public UserInfo getUserInfoById(@PathVariable UUID userId, @RequestHeader("x-user") String user, @RequestHeader("x-scope") String scope) {
        System.out.println(user);
        System.out.println(scope);
        return userInfoService.getUserById(userId);
    }
}
