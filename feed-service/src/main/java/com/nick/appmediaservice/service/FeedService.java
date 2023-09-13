package com.nick.appmediaservice.service;

import com.nick.appmediaservice.client.PostDetailsClient;
import com.nick.appmediaservice.client.SocialGraphClient;
import com.nick.appmediaservice.client.UserDetailsClient;
import com.nick.appmediaservice.dto.Post;
import com.nick.appmediaservice.dto.PostNode;
import com.nick.appmediaservice.model.FeedItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final PostDetailsClient postDetailsClient;
    private final SocialGraphClient socialGraphClient;
    private final UserDetailsClient userDetailsClient;

    //todo cache this for 5 mins
    public Flux<FeedItem> createFeed(String userId) {       //todo only collect items from last 3 days
        var posts = socialGraphClient
                .findPostsByUserFollowing(userId)
                .map(PostNode::postId)
                .collectList()
                .flatMapMany(postDetailsClient::findPostsByIdsList)
                .cache();

        return posts.map(Post::userId)          //todo test if this even works as 'posts' used twice
                .collectList()
                .flatMap(userDetailsClient::getUsersByIds)
                .flatMapMany(userDetailsMap -> posts.map(post -> new FeedItem(userDetailsMap.getOrDefault(post.userId(), null), post)));
    }
}
