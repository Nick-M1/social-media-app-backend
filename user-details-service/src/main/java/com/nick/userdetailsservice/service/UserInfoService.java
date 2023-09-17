package com.nick.userdetailsservice.service;

import com.nick.userdetailsservice.dto.UserInfoRequest;
import com.nick.userdetailsservice.model.UserInfo;
import com.nick.userdetailsservice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfo getUserById(UUID userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User by id doesn't exist"));
    }

    public UserInfo getUserByUsername(String username) {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(""));
    }

    public Map<UUID, UserInfo> getUsersByIds(List<UUID> userIds) {
        return userInfoRepository.findAllById(userIds)
                .stream().map(userInfo -> Map.entry(userInfo.getId(), userInfo))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Page<UserInfo> getAllUsers() {
        var pageable = Pageable.ofSize(10);
        return userInfoRepository.findAll(pageable);
    }

    public UserInfo createUser(UserInfoRequest userInfoRequest) {
        if (userInfoRepository.existsByEmail(userInfoRequest.email()))
            throw new RuntimeException("Email already exists");

        var user = userInfoRequest.mapToUserInfo();
        user.setPassword(passwordEncoder.encode(userInfoRequest.password()));

        return userInfoRepository.save(user);
    }
}
