package com.example.demo.controller;

import com.example.demo.common.ResponseUtil;
import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.common.Search;
import com.example.demo.service.PostTagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post-tags")
@RequiredArgsConstructor
public class PostTagController {

    private final PostTagService postTagService;

    @CrossOrigin(exposedHeaders = "X-Total-Count")
    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts(@ModelAttribute Search search) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> filter = objectMapper.readValue(search.getFilter(), new TypeReference<Map<String, Object>>() {});
        Long id = Long.valueOf(filter.get("tag").toString());

        List<PostDTO> posts = postTagService.findPostByTag(Tag.builder().id(id).build())
                .stream().map(PostDTO::toDto).collect(Collectors.toList());
        return ResponseUtil.getListResponse(posts, posts.size());
    }

}
