package com.example.demo.dto.common;

import lombok.Data;

@Data
public class FileDTO {
    private String id;
    private String base64;
    private String fileName;
    private long fileSize;
}
