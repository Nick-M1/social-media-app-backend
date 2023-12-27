package com.nick.socialgraphservice.repository;

import com.nick.socialgraphservice.model.User;
import com.nick.socialgraphservice.model.relationships.UserFollow;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserFollowRepository extends ReactiveNeo4jRepository<UserFollow, String> {
}
