package com.nick.socialgraphservice.model.relationships;

import com.nick.socialgraphservice.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;

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
