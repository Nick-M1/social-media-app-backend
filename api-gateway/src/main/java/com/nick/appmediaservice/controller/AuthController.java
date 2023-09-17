package com.nick.appmediaservice.controller;

import com.nick.appmediaservice.dto.LoginRequest;
import com.nick.appmediaservice.dto.LoginResponse;
import com.nick.appmediaservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping("/token")
    public Mono<LoginResponse> token(@RequestBody LoginRequest userLogin) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()))
                .map(authentication -> {
                    var userAsString = authentication.getPrincipal().toString();
                    var jwtToken = tokenService.generateToken(authentication);
                    var role = tokenService.getRoleFromToken(userAsString);
                    var id = tokenService.getIdFromToken(userAsString);

                    return new LoginResponse(jwtToken, role, id);
                });
    }
}
