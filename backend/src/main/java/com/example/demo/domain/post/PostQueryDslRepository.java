package com.example.demo.domain.post;

import com.example.demo.dto.common.Search;

import java.util.List;

public interface PostQueryDslRepository {
    Long findAllCount(Search search);
    List<Post> findAllBy(Search search);
}
