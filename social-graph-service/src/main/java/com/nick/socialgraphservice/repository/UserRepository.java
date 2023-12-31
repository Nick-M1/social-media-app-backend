package com.nick.socialgraphservice.repository;

import com.nick.socialgraphservice.model.User;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveNeo4jRepository<User, String> {
    @Query("MATCH (user:`User`) WHERE user.userId = $userId RETURN user")
    Mono<User> findUserByUserId(String userId);

    @Query("MATCH (n)-[r:IS_FOLLOWING]->() where ELEMENTID(n) = $id RETURN COUNT(r)")
    Mono<Long> countOfFollowing(String id);

    @Query("MATCH (n)<-[r:IS_FOLLOWING]-() where ELEMENTID(n) = $id RETURN COUNT(r)")
    Mono<Long> countOfFollowers(String id);

    @Query("MATCH (n)-[r:POST_LIKED]->() where ELEMENTID(n) = $id RETURN COUNT(r)")
    Mono<Long> countOfPostsLikedByUser(String id);

    @Query("""
        MATCH (n1) WHERE ELEMENTID(n1) = $id1
        MATCH (n2) WHERE ELEMENTID(n2) = $id2
        RETURN EXISTS((n1)-[:IS_FOLLOWING]->(n2))
    """)
    Mono<Boolean> isFollowing(String id1, String id2);

    @Query("""
        MATCH (n1) WHERE ELEMENTID(n1) = $userId
        MATCH (n2) WHERE ELEMENTID(n2) = $postId
        RETURN EXISTS((n1)-[:POST_LIKED]->(n2))
    """)
    Mono<Boolean> isLikedPost(String userId, String postId);

    @Query("""
        MATCH (n:User) WHERE ELEMENTID(n) = $id
        MATCH (f:User)-[:IS_FOLLOWING]->(n)
        RETURN f
    """)
    Flux<User> findFollowers(String id);

    @Query("""
        MATCH (n:User) WHERE ELEMENTID(n) = $id
        MATCH (f:User)<-[:IS_FOLLOWING]-(n)
        RETURN f
    """)
    Flux<User> findFollowing(String id);

    @Query("""
        MATCH (user1: User) WHERE ELEMENTID(user1) = $userId1
        MATCH (user2: User) WHERE ELEMENTID(user2) = $userId2
        CREATE (user1)-[rel:IS_FOLLOWING]->(user2)
    """)
    Mono<Void> updateFollowRelationship(String userId1, String userId2);
}
