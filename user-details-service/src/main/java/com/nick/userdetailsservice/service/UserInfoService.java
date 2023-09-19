package com.nick.userdetailsservice.service;

import com.nick.userdetailsservice.client.SocialGraphServiceClient;
import com.nick.userdetailsservice.dto.SocialGraphUserNodeRequest;
import com.nick.userdetailsservice.dto.UserInfoRequest;
import com.nick.userdetailsservice.model.UserInfo;
import com.nick.userdetailsservice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final SocialGraphServiceClient socialGraphServiceClient;

    public UserInfo getUserById(UUID userId) {
        log.info("Get user by id = {}", userId);
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User by id doesn't exist"));
    }

    public UserInfo getUserByUsername(String username) {
        log.info("Get user by username = {}", username);
        return userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(""));
    }

    public Map<UUID, UserInfo> getUsersByIds(List<UUID> userIds) {
        log.info("Get users by ids = {}", userIds.toString());
        return userInfoRepository.findAllById(userIds)
                .stream().map(userInfo -> Map.entry(userInfo.getId(), userInfo))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Page<UserInfo> getAllUsers() {
        log.info("Get all users");
        var pageable = Pageable.ofSize(10);
        return userInfoRepository.findAll(pageable);
    }

    public UserInfo createUser(UserInfoRequest userInfoRequest) {
        log.info("Create user with {}", userInfoRequest);
        if (userInfoRepository.existsByEmail(userInfoRequest.email()))
            throw new RuntimeException("Email already exists");

        var mappedUser = userInfoRequest.mapToUserInfo(passwordEncoder);
        var savedUser = userInfoRepository.save(mappedUser);

        var socialGraphServiceRequest = new SocialGraphUserNodeRequest(savedUser.getId().toString());
        socialGraphServiceClient.createUser(socialGraphServiceRequest);

        return savedUser;
    }
}
