package com.example.demo.controller;

import com.example.demo.common.ResponseUtil;
import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.tag.PostTag;
import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.common.FileDTO;
import com.example.demo.dto.common.Search;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.TagMapper;
import com.example.demo.service.FileService;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    @CrossOrigin(exposedHeaders = "X-Total-Count")
    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts(@ModelAttribute Search search) {
        Long count = postService.findAllCount(search);
        if (search.getLimit() == 0) {
            search.setLimit(count);
        }
        List<PostDTO> posts = postService.findAllBy(search)
                .stream().map(PostDTO::toDto).collect(Collectors.toList());
        return ResponseUtil.getListResponse(posts, count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        Post post = postService.findById(id);
        PostDTO postDTO = PostDTO.toDto(post);
        if(post.getFile()!= null) {
            FileDTO fileDTO = postDTO.getFile();
            fileDTO.setBase64(fileService.encodeFileToBase64(post.getFile()));
        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
        Post post = postDTO.toEntity();
        FileDTO fileDTO = postDTO.getFile();
        Post savedPost = postService.save(post, fileDTO);

        PostDTO savedPostDTO = PostDTO.toDto(savedPost);
        return new ResponseEntity<>(savedPostDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        FileDTO fileDTO = postDTO.getFile();

        Post updatedPost = postService.update(id, postDTO.toEntity(), fileDTO);
        return new ResponseEntity<>(PostDTO.toDto(updatedPost), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> deleteMany(@RequestParam List<Long> ids) {
        postService.deleteAll(ids);
        return new ResponseEntity<>(ids, HttpStatus.OK);
    }
}
