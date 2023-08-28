package com.nick.appmediaservice.config;

import com.nick.appmediaservice.model.UserDetails;
import com.nick.appmediaservice.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostrgesDataInit {
    @Bean
    public CommandLineRunner postgresDataInitializer(UserDetailsRepository userDetailsRepository) {
        return args -> {
            userDetailsRepository.deleteAll();

            var user1 = new UserDetails("username", "password", "firstname", "lastname", "email@gmail.com", "phonenumber", "profileimg");
            userDetailsRepository.save(user1);

            System.out.println(userDetailsRepository.findAll());
        };
    }
}
