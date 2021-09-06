package com.example.demo.common;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {

    public static <T> ResponseEntity<List<T>> getListResponse(List<T> data, long count) {
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(count)).body(data);
    }

}
