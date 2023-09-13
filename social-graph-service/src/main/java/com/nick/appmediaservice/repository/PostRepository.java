package com.nick.appmediaservice.repository;

import com.nick.appmediaservice.model.Post;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveNeo4jRepository<Post, String> {

    @Query("MATCH ()-[r:POST_LIKED]->(n) where ELEMENTID(n) = $id RETURN COUNT(r)")
    Mono<Long> countOfUserLikesOnPost(String id);

    @Query("""
            MATCH (post:Post)<-[r:POST_LIKED]-(user:User)
            WHERE ELEMENTID(user) = $userId
            RETURN post
    """)
    Flux<Post> findPostsLikedByUser(String userId);

    @Query("""
            MATCH (post:Post)<-[pr:COMMENT_TO_POST]-(comment:Comment)
            WHERE ELEMENTID(comment) = $commentId
            RETURN post
    """)
    Mono<Post> findPostByComment(String commentId);

    @Query("""
            MATCH (user:User)<-[:CREATED_BY]-(post:Post)
            WHERE ELEMENTID(user) = $userId
            RETURN post
    """)
    Flux<Post> findPostsByUser(String userId);
}
