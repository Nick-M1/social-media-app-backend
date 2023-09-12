package com.nick.appmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableReactiveElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@SpringBootApplication
@EnableReactiveElasticsearchRepositories
@EnableReactiveElasticsearchAuditing
public class PostsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostsServiceApplication.class, args);
    }
}
