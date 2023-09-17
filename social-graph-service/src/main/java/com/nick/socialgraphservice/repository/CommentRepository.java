package com.nick.socialgraphservice.repository;

import com.nick.socialgraphservice.model.Comment;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentRepository extends ReactiveNeo4jRepository<Comment, String> {

    // BY POST:
    @Query("MATCH (p:Post)<-[r:COMMENT_TO_POST]-(c:Comment) where ELEMENTID(p) = $postId RETURN COUNT(r)")
    Mono<Long> countOfAllCommentsByPost(String postId);

    @Query("""
            MATCH (post:Post)<-[pr:COMMENT_TO_POST]-(comment:Comment)
            WHERE NOT EXISTS {
              MATCH (comment)-[:COMMENT_TO_COMMENT]->(otherComment:Comment)
              WHERE otherComment <> comment
            }
            AND ELEMENTID(post) = $postId
            RETURN COUNT(pr)
    """)
    Mono<Long> countOfDirectCommentsByPost(String postId);

    @Query("""
            MATCH (post:Post)<-[pr:COMMENT_TO_POST]-(comment:Comment)
            WHERE ELEMENTID(post) = $postId
            RETURN comment
    """)
    Flux<Comment> findAllCommentsByPost(String postId);

    @Query("""
            MATCH (post:Post)<-[pr:COMMENT_TO_POST]-(comment:Comment)
            WHERE ELEMENTID(post) = $postId
            AND NOT EXISTS {
                MATCH (comment:Comment)-[:COMMENT_TO_COMMENT]->(otherComment:Comment)
                WHERE otherComment <> comment
            }
            RETURN comment
    """)
    Flux<Comment> findDirectCommentsByPost(String postId);


    // BY COMMENT:
    @Query("""
            MATCH (comment:Comment)<-[r:COMMENT_TO_COMMENT]-(childComment:Comment)
            WHERE ELEMENTID(comment) = $commentId
            RETURN COUNT(childComment)
    """)
    Mono<Long> countChildComments(String commentId);

    @Query("""
            MATCH (parentComment:Comment)<-[r:COMMENT_TO_COMMENT]-(comment:Comment)
            WHERE ELEMENTID(comment) = $commentId
            RETURN parentComment
    """)
    Mono<Comment> findParentComment(String commentId);

    @Query("""
            MATCH (comment:Comment)<-[r:COMMENT_TO_COMMENT *1..]-(childComment:Comment)
            WHERE ELEMENTID(comment) = $commentId
            RETURN childComment
    """)
    Flux<Comment> findChildComments(String commentId);


    // BY USER:
    @Query("""
            MATCH (user:User)<-[:CREATED_BY]-(comment:Comment)
            WHERE ELEMENTID(user) = $userId
            RETURN comment
    """)
    Flux<Comment> findCommentsByUser(String userId);
}
