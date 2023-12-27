package com.nick.socialgraphservice.config;

import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableReactiveNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableReactiveNeo4jRepositories;

@TestConfiguration
@EnableReactiveNeo4jRepositories
public class Neo4jTransactionManager {
//    @Bean
//    public org.neo4j.cypherdsl.core.renderer.Configuration cypherDslConfiguration() {
//        return org.neo4j.cypherdsl.core.renderer.Configuration.newConfig()
//                .withDialect(Dialect.NEO4J_5).build();
//    }
}
