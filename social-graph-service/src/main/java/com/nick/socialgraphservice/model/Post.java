package com.nick.socialgraphservice.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Node
public class Post {
    @Id
    @GeneratedValue
    private String id;

    @Relationship(type = "CREATED_BY", direction = Relationship.Direction.OUTGOING)
    private User userCreatedBy;

    public Post(User userCreatedBy) {
        this.userCreatedBy = userCreatedBy;
    }
}
