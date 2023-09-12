package com.nick.appmediaservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Photo {
    @Id
    private String id;

    @CreatedDate
    private long createdAt;
    @LastModifiedDate
    private long modifiedAt;

    private String title;
    private Binary image;

    public Photo(String title, Binary image) {
        this.title = title;
        this.image = image;
    }
}
