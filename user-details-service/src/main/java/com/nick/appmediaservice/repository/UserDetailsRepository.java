package com.nick.appmediaservice.repository;

import com.nick.appmediaservice.model.UserDetails;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    Page<UserDetails> findAllByUsernameLike(@NotBlank String username, Pageable pageable);

    @SuppressWarnings("all")
    @Query(value = """
        SELECT u,
        ts_rank(
           setweight(to_tsvector('simple', COALESCE(u.username, '')), 'A') ||
           setweight(to_tsvector('simple', COALESCE(u.firstName, '')), 'B') ||
           setweight(to_tsvector('simple', COALESCE(u.lastName, '')), 'C'),
           to_tsquery('simple', :queryString)
        ) AS rank
        FROM UserDetails u
        WHERE (
           (u.username ILIKE '%' || :queryString || '%') OR
           (u.firstName ILIKE '%' || :queryString || '%') OR
           (u.lastName ILIKE '%' || :queryString || '%')
        )
        ORDER BY rank DESC
    """)
    Page<UserDetails> findAllBySearchQuery(@Param("queryString") String queryString, Pageable pageable);
}
