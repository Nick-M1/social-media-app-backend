package com.nick.appmediaservice.controller;

import com.nick.appmediaservice.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/info/user")
@RequiredArgsConstructor
public class UserDetailsController {
    private final UserDetailsService userDetailsService;
}
