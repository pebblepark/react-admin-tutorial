package com.example.demo.dto;

import lombok.Data;

@Data
public class TagDTO {

    private Long id;
    private String name;
    private String useYn;
    private Long parentId;

}
