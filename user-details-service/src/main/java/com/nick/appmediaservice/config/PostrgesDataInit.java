package com.nick.appmediaservice.config;

import com.nick.appmediaservice.model.UserDetails;
import com.nick.appmediaservice.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Configuration
public class PostrgesDataInit {
    @Bean
    public CommandLineRunner postgresDataInitializer(UserDetailsRepository userDetailsRepository) {
        return args -> {
            System.out.println("HELLO");
            userDetailsRepository.deleteAll();

            var user1 = new UserDetails("username", "password", "firstname", "lastname", "email@gmail.com", "phonenumber", "profileimg");
            var user2 = new UserDetails("hello", "password", "firstname", "lastname", "email2@gmail.com", "phonenumber", "profileimg");
            userDetailsRepository.saveAll(List.of(user1, user2));

            System.out.println(userDetailsRepository.findAll());

            var pageable = Pageable.ofSize(10);
            System.out.println(userDetailsRepository.findAllBySearchQuery("username", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("usernam", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("userna", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("usern", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("user", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("dsdsdsd", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("firstname", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("firstna", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("lastn", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("userjame", pageable).getContent());
            System.out.println(userDetailsRepository.findAllBySearchQuery("usernamk", pageable).getContent());
        };
    }
}
