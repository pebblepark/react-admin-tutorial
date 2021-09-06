package com.example.demo.common;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

public class QueryDslUtil {

    public static <T> OrderSpecifier<?> getSortedColumn(Order order, String fieldName, Path<T> qClass) {
        if (order == null || fieldName == null || qClass == null) return null;
        Path<Object> fieldPath = Expressions.path(Object.class, qClass, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

}
