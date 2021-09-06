package com.example.demo.domain.user;

import com.example.demo.dto.common.Search;

import java.util.List;

public interface UserQueryDslRepository {
    Long findAllCount(Search search);
    List<User> findAllBy(Search search);
}
