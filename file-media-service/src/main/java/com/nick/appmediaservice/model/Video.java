package com.nick.appmediaservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;
// todo: https://www.baeldung.com/spring-boot-mongodb-upload-file
//@Data
//@NoArgsConstructor
//@Document
//public class Video {
//    @Id
//    private String id;
//
//    @CreatedDate
//    private long createdAt;
//    @LastModifiedDate
//    private long modifiedAt;
//
//    private String title;
//    private InputStream stream;
//
//    public Video(String title, InputStream stream) {
//        this.title = title;
//        this.stream = stream;
//    }
//}
