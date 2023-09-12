package com.nick.appmediaservice.repository;

import com.nick.appmediaservice.model.Photo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface PhotoRepository extends ReactiveMongoRepository<Photo, String> {
}
