package com.nick.appmediaservice.dto;

public record LoginResponse(String jwtToken, String role, String id) {}
