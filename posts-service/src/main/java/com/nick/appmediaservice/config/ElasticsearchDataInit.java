package com.nick.appmediaservice.config;

import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ElasticsearchDataInit {

    @Bean
    public CommandLineRunner elasticsearchDataInitializer(PostRepository postRepository) {
        return args -> {
            postRepository.deleteAll().block();

            var post1 = new Post("postid_1", "userid_1", "description_1", List.of("tag_1", "tag_2"), List.of());
            var post2 = new Post("postid_2", "userid_1", "hello", List.of("tag_2"), List.of());
            var post3 = new Post("postid_3", "userid_1", "zzzz zzzz", List.of("tag_1"), List.of());
            postRepository.saveAll(List.of(post1, post2, post3)).collectList().block();

            postRepository.findAll().collectList().doOnNext(System.out::println).block();
            postRepository.findPostsByTextSearch("hello").collectList().doOnNext(System.out::println).block();
            postRepository.findPostsByTextSearch("helli").collectList().doOnNext(System.out::println).block();
            postRepository.findPostsByTextSearch("hel").collectList().doOnNext(System.out::println).block();
            postRepository.findPostsByTextSearch("he").collectList().doOnNext(System.out::println).block();

            postRepository.findPostsByTextSearch("hewww").collectList().doOnNext(System.out::println).block();
            postRepository.findPostsByTextSearch("sdsds").collectList().doOnNext(System.out::println).block();


            postRepository.findPostsByTextSearch("tag_1").collectList().doOnNext(System.out::println).block();
        };
    }
}
