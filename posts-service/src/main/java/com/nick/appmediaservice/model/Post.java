package com.nick.appmediaservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Document(indexName = "posts")
public class Post {
    @Id
    private String id;

    @CreatedDate
    private long createdAt;

    @LastModifiedDate
    private long modifiedAt;

    private String userId;
    private String description;
    private List<String> tags;
    private List<String> images;


    public Post(String postId, String userId, String description, List<String> tags, List<String> images) {
        this.id = postId;
        this.userId = userId;
        this.description = description;
        this.tags = tags;
        this.images = images;
    }
}
