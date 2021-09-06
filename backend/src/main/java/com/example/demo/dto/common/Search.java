package com.example.demo.dto.common;

import com.querydsl.core.types.Order;
import lombok.Data;

@Data
public class Search {
    private String sortId;
    private String sort;
    private long offset;
    private long limit;
    private String filter;

    public Order getOrder() {
        if("ASC".equals(this.sort)) {
            return Order.ASC;
        }else if("DESC".equals(this.sort)) {
            return Order.DESC;
        }

        return null;
    }
}