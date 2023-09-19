package com.nick.userdetailsservice.config;

import com.nick.userdetailsservice.model.UserInfo;
import com.nick.userdetailsservice.repository.UserInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class PostrgesDataInit {
    @Bean
    public CommandLineRunner postgresDataInitializer(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("HELLO");
            userInfoRepository.deleteAll();

            var user1 = new UserInfo("username", passwordEncoder.encode("password"), "firstname", "lastname", "email@gmail.com", "phonenumber", "profileimg", "headerimg", "working <b>Twitter</b> clone", "www.google.com");
            var user2 = new UserInfo("hello", passwordEncoder.encode("password"), "firstname", "lastname", "email2@gmail.com", "phonenumber", "profileimg", "headerimg", "working <b>Twitter</b> clone", "www.youtube.com");
            userInfoRepository.saveAll(List.of(user1, user2));

            System.out.println(userInfoRepository.findAll());

            var pageable = Pageable.ofSize(10);
            System.out.println(userInfoRepository.findAllBySearchQuery("username", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("usernam", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("userna", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("usern", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("user", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("dsdsdsd", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("firstname", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("firstna", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("lastn", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("userjame", pageable).getContent());
            System.out.println(userInfoRepository.findAllBySearchQuery("usernamk", pageable).getContent());
        };
    }
}
