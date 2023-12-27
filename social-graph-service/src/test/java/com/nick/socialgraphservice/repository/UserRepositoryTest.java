package com.nick.socialgraphservice.repository;

import com.nick.socialgraphservice.config.Neo4jTransactionManager;
import com.nick.socialgraphservice.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataNeo4jTest
@Testcontainers
@ContextConfiguration(classes={ Neo4jTransactionManager.class, UserRepository.class })
class UserRepositoryTest {

    @Container
    @ServiceConnection
    static Neo4jContainer<?> neo4j = new Neo4jContainer<>("neo4j:5");

    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll().block();
    }

    @Test
    void findUserByUserId() {
        var userId1 = "user_id_1";
        var user1 = new User(userId1);

        StepVerifier.create(underTest.save(user1).then(underTest.findUserByUserId(userId1)))
                .assertNext(entity -> assertThat(entity).isEqualTo(user1))
                .verifyComplete();
    }

    @Test
    void countOfFollowing() {
    }

    @Test
    void countOfFollowers() {
    }

    @Test
    void countOfPostsLikedByUser() {
    }

    @Test
    void isFollowing() {
    }

    @Test
    void isLikedPost() {
    }

    @Test
    void findFollowers() {
    }

    @Test
    void findFollowing() {
    }

    @Test
    void updateFollowRelationship() {
    }
}