package com.nick.appmediaservice.repository;

import com.nick.appmediaservice.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;

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
    Flux<Post> findPostsByTextSearch(String queryString);

    Flux<Post> findPostsByUserId(String userId, Pageable pageable);
    Flux<Post> findPostsByUserIdInOrderByCreatedAt(List<String> userId, Pageable pageable);
    Flux<Post> findPostsByTagsContainsIgnoreCaseOrderByCreatedAt(List<String> tags, Pageable pageable);
}
