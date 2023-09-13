package com.nick.appmediaservice.model;

import com.nick.appmediaservice.model.relationships.UserFollow;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
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
