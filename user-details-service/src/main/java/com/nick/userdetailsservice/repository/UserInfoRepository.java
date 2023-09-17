package com.nick.userdetailsservice.repository;

import com.nick.userdetailsservice.model.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {

    Optional<UserInfo> findByUsername(@NotBlank String username);
    Page<UserInfo> findAllByUsernameLike(@NotBlank String username, Pageable pageable);
    Boolean existsByEmail(@Email String email);

    @SuppressWarnings("all")
    @Query(value = """
        SELECT u,
        ts_rank(
           setweight(to_tsvector('simple', COALESCE(u.username, '')), 'A') ||
           setweight(to_tsvector('simple', COALESCE(u.firstName, '')), 'B') ||
           setweight(to_tsvector('simple', COALESCE(u.lastName, '')), 'C'),
           to_tsquery('simple', :queryString)
        ) AS rank
        FROM UserInfo u
        WHERE (
           (u.username ILIKE '%' || :queryString || '%') OR
           (u.firstName ILIKE '%' || :queryString || '%') OR
           (u.lastName ILIKE '%' || :queryString || '%')
        )
        ORDER BY rank DESC
    """)
    Page<UserInfo> findAllBySearchQuery(@Param("queryString") String queryString, Pageable pageable);
}
