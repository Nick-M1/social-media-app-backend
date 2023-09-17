package com.nick.socialgraphservice.model;

import com.nick.socialgraphservice.model.relationships.UserFollow;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@Node
public class User {
    @Id @GeneratedValue
    private String id;

    private String userId;

    @Relationship(type = "IS_FOLLOWING", direction = Relationship.Direction.OUTGOING)
    private Set<UserFollow> following;
    @Relationship(type = "POST_LIKED", direction = Relationship.Direction.OUTGOING)
    private Set<Post> likedPosts;   //todo bookmarked posts

    public User(String userId) {
        this.userId = userId;
        this.following = new HashSet<>();
        this.likedPosts = new HashSet<>();
    }
}
