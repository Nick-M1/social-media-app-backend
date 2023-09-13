package com.nick.appmediaservice.config;

import com.nick.appmediaservice.model.Comment;
import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.model.User;
import com.nick.appmediaservice.model.relationships.UserFollow;
import com.nick.appmediaservice.repository.CommentRepository;
import com.nick.appmediaservice.repository.PostRepository;
import com.nick.appmediaservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class Neo4jDataInit {
//    @Bean
//    public CommandLineRunner userDataInitializer(UserRepository userRepository) {
//        return args -> {
//            userRepository.deleteAll().block();
//
//            var user1 = new User("username_1", "1", "1", "1", new HashSet<>());
//            var user2 = new User("username_2", "2", "2", "2", new HashSet<>());
//            user2.setFollowing(Set.of(new UserFollow(user1)));
//
//            userRepository.saveAll(List.of(user1, user2)).collectList().doOnNext(System.out::println).block();
//
//            System.out.println("-------------");
//
//            userRepository.findAll()
//                    .collectList()
//                    .doOnNext(System.out::println)
//                    .block();
//            System.out.println("Followers");
//            userRepository.findFollowers(user1.getId())
////                    .buffer(10, 1)
//                    .collectList().doOnNext(System.out::println).block();
//            userRepository.findFollowers(user2.getId())
////                    .buffer(10, 1)
//                    .collectList().doOnNext(System.out::println).block();
//
//            System.out.println("Following");
//            userRepository.findFollowing(user1.getId())
////                    .buffer(10, 1)
//                    .collectList().doOnNext(System.out::println).block();
//            userRepository.findFollowing(user2.getId())
////                    .buffer(10, 1)
//                    .collectList().doOnNext(System.out::println).block();
//        };
//    }

    @Bean
    public CommandLineRunner postCommentDataInitializer(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        return args -> {
            userRepository.deleteAll().block();
            postRepository.deleteAll().block();
            commentRepository.deleteAll().block();

            var user1 = new User("1");
            var user2 = new User("2");
            user2.setFollowing(Set.of(new UserFollow(user1)));
            userRepository.saveAll(List.of(user1, user2)).collectList().block();

            var post1 = new Post("post_1", user1);
            var comment1 = new Comment("comment_1", post1, null, user1);
            var comment2 = new Comment("comment_2", post1, null, user1);
            var comment3 = new Comment("comment_3", post1, comment2, user2);

            postRepository.saveAll(List.of(post1)).collectList().block();
            commentRepository.saveAll(List.of(comment1, comment2, comment3)).collectList().block();

            System.out.println("------");
//            postRepository.findPostByComment(comment2.getId()).doOnNext(System.out::println).block();
            commentRepository.findCommentsByUser(user1.getId()).collectList().doOnNext(System.out::println).block();
        };
    }
}
