package com.nick.appmediaservice.model;

import com.nick.appmediaservice.dto.Post;
import com.nick.appmediaservice.dto.UserDetails;

public record FeedItem (
        UserDetails userDetails,
        Post post
) {
}
