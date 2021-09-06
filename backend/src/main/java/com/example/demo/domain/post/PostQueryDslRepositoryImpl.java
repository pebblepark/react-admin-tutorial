package com.example.demo.domain.post;

import com.example.demo.common.QueryDslUtil;
import com.example.demo.domain.post.tag.QPostTag;
import com.example.demo.dto.common.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.domain.post.QPost.post;
import static com.example.demo.domain.post.tag.QPostTag.postTag;
import static com.example.demo.domain.user.QUser.user;

@RequiredArgsConstructor
public class PostQueryDslRepositoryImpl implements PostQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long findAllCount(Search search) {
        return jpaQueryFactory
                .selectFrom(post)
                .where(getSearchCondition(search.getFilter()))
                .fetchCount();
    }

    @Override
    public List<Post> findAllBy(Search search) {
        return jpaQueryFactory
                .selectFrom(post)
                .join(post.user, user).fetchJoin()
                .join(post.tags, postTag).fetchJoin()
                .where(getSearchCondition(search.getFilter()))
                .offset(search.getOffset())
                .limit(search.getLimit())
                .orderBy(getOrderSpecifier(search.getOrder(), search.getSortId()))
                .fetch();
    }

    private BooleanBuilder getSearchCondition(String filter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, Object> search = objectMapper.readValue(filter, new TypeReference<Map<String, Object>>() {});

            if (search.get("all") != null) {
                String searchStr = search.get("all").toString();
                booleanBuilder.and(post.title.contains(searchStr));
                booleanBuilder.or(post.body.contains(searchStr));
            }

            if (search.get("userId") != null) {
                Long userId = Long.valueOf(search.get("userId").toString());
                booleanBuilder.and(post.user.id.eq(userId));
            }

            if (search.get("startTime") != null) {
                LocalDateTime time = convertStringToLocalDateTime(search.get("startTime").toString() + " 00:00:00");
                booleanBuilder.and(post.creTime.after(time));
            }

            if (search.get("endTime") != null) {
                LocalDateTime time = convertStringToLocalDateTime(search.get("endTime").toString() + " 23:59:59");
                booleanBuilder.and(post.creTime.before(time));
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return booleanBuilder;
    }

    private LocalDateTime convertStringToLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private OrderSpecifier<?> getOrderSpecifier(Order order, String fieldName) {
        if ("userId".equals(fieldName)) {
            fieldName = "user.id";
        }
        return QueryDslUtil.getSortedColumn(order, fieldName, post);
    }
}
