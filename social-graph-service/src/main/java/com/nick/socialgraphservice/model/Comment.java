package com.nick.socialgraphservice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Node
public class Comment {
    @Id
    @GeneratedValue
    private String id;

    @CreatedDate
    private long createdAt;

    private String description;     // and title, image... in elastic
    private Boolean isDeleted;

    @Relationship(type = "COMMENT_TO_POST", direction = Relationship.Direction.OUTGOING)
    private Post post;

    @Relationship(type = "COMMENT_TO_COMMENT", direction = Relationship.Direction.OUTGOING)
    private Comment parentComment;

    @Relationship(type = "CREATED_BY", direction = Relationship.Direction.OUTGOING)
    private User userCreatedBy;

    public Comment(String description, Post post, Comment parentComment, User userCreatedBy) {
        this.description = description;
        this.isDeleted = false;
        this.post = post;
        this.parentComment = parentComment;
        this.userCreatedBy = userCreatedBy;
    }
}
