package com.nick.appmediaservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder jwtEncoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getRoleFromToken(String userAsString) {
        return getItemFromToken(userAsString, "role=([^,]+)");
    }
    public String getIdFromToken(String userAsString) {
        return getItemFromToken(userAsString, "id=([^,]+)");
    }

    private String getItemFromToken(String userAsString, String regexExpression) {
        var pattern = Pattern.compile(regexExpression);
        var matcher = pattern.matcher(userAsString);
return "";
//        if (!matcher.find())      //todo reimplement
//            throw new RuntimeException("Error finding role from JWT Token");        //todo: make custom exception
//
//        return matcher.group(1);
    }
}
