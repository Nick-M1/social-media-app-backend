package com.nick.appmediaservice.repository;

import com.nick.appmediaservice.model.Post;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PostRepository extends ReactiveElasticsearchRepository<Post, String> {
    @Query(query = """
            {
              "bool": {
                "should": [
                  {
                    "wildcard": {
                      "description": {
                        "value": "*?0*",
                        "boost": 2.0,
                        "rewrite": "constant_score"
                      }
                    }
                  },
                  {
                    "wildcard": {
                      "tags": {
                        "value": "*?0*",
                        "boost": 5.0,
                        "rewrite": "constant_score"
                      }
                    }
                  },
                  {
                    "multi_match": {
                      "query": "*?0*",
                      "fields": [ "description", "tags^0.3" ],
                      "fuzziness": "auto",
                      "prefix_length": 2
                    }
                  }
                ]
              }
            }
    """)
    Flux<Post> findPostByTextSearch(Mono<String> queryString);

    Flux<Post> findPostsByUserId(Mono<String> userId);
}
