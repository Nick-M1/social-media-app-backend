package com.nick.appmediaservice.config;

import com.nick.appmediaservice.model.Photo;
import com.nick.appmediaservice.repository.PhotoRepository;
import org.bson.types.Binary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MongoDataInit {

    @Bean
    public CommandLineRunner mongoDataInitializer(PhotoRepository photoRepository) {
        return args -> {
            photoRepository.deleteAll()
                    .block()
            ;

            var post1 = new Photo("title_1", new Binary(new byte[]{ }));
            var post2 = new Photo("title_2", new Binary(new byte[]{ }));
            var post3 = new Photo("title_3", new Binary(new byte[]{ }));
            photoRepository.saveAll(List.of(post1, post2, post3))
                    .collectList().block()
            ;

            photoRepository.findAll()
                    .collectList().doOnNext(System.out::println).block()
            ;
        };
    }
}
