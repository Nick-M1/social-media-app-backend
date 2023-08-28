package com.nick.appmediaservice.repository;

import com.nick.appmediaservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
}
