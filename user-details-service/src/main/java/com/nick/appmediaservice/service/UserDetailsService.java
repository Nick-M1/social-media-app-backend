package com.nick.appmediaservice.service;

import com.nick.appmediaservice.model.UserDetails;
import com.nick.appmediaservice.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    public UserDetails getUserById(String userId) {
        return userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User by id doesn't exist"));
    }

    public Map<UUID, UserDetails> getUsersByIds(List<String> userIds) {
        return userDetailsRepository.findAllById(userIds)
                .stream().map(userDetails -> Map.entry(userDetails.getId(), userDetails))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Page<UserDetails> getAllUsers() {
        var pageable = Pageable.ofSize(10);
        return userDetailsRepository.findAll(pageable);
    }
}
