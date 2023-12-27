package com.nick.socialgraphservice.model;

import com.nick.socialgraphservice.model.relationships.UserFollow;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void addFollowing(User user) {
        var userFollowRelationship = new UserFollow(user);
        following.add(userFollowRelationship);
    }

    public void removeFollowing(User user) {
        following = following.stream()
                .filter(relationships -> !Objects.equals(relationships.getFollowedUser().getUserId(), user.getUserId()))
                .collect(Collectors.toSet());
    }

    public void addLikedPost(Post post) {
        likedPosts.add(post);
    }

    public void removeLikedPost(Post post) {
        likedPosts.remove(post);
    }
}
