package com.nick.appmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.config.EnableReactiveNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableReactiveNeo4jRepositories;

@SpringBootApplication
//@EnableReactiveNeo4jRepositories
@EnableReactiveNeo4jAuditing
public class SocialGraphServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialGraphServiceApplication.class, args);
    }

}
