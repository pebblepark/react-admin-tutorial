package com.example.demo.domain.user;

import com.example.demo.common.QueryDslUtil;
import com.example.demo.dto.common.Search;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.demo.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserQueryDslRepositoryImpl implements UserQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long findAllCount(Search search) {
        return jpaQueryFactory.selectFrom(user)
                .fetchCount();
    }

    @Override
    public List<User> findAllBy(Search search) {
        return jpaQueryFactory.selectFrom(user)
                .offset(search.getOffset())
                .limit(search.getLimit())
                //.orderBy(getOrderSpecifier(search.getOrder(), search.getSortId()))
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifier(Order order, String fieldName) {
        return QueryDslUtil.getSortedColumn(order, fieldName, user);
    }
}
