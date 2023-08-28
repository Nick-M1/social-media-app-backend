package com.nick.appmediaservice.service;

import com.nick.appmediaservice.model.UserDetails;
import com.nick.appmediaservice.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    public Page<UserDetails> getAllUsers() {
        var pageable = Pageable.ofSize(10);
        return userDetailsRepository.findAll(pageable);
    }
}
