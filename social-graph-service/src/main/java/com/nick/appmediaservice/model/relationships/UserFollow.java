package com.nick.appmediaservice.model.relationships;

import com.nick.appmediaservice.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;

@Data
@RelationshipProperties
public class UserFollow {       //todo isRequestPending...

    @RelationshipId
    private String id;

    @TargetNode
    private User followedUser;

    @CreatedDate
    private long createdAt;

    public UserFollow(User followedUser) {
        this.followedUser = followedUser;
    }
}
