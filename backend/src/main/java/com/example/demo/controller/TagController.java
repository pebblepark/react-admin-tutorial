package com.example.demo.controller;

import com.example.demo.common.ResponseUtil;
import com.example.demo.domain.tag.Tag;
import com.example.demo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @CrossOrigin(exposedHeaders = "X-Total-Count")
    @GetMapping
    public ResponseEntity<List<Tag>> getPosts() {
        List<Tag> tags = tagService.findAll();
        return ResponseUtil.getListResponse(tags, tags.size());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getPost(@PathVariable Long id) {
        Tag tag = tagService.findById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tag> save(@RequestBody Tag tag) {
        Tag savedTag = tagService.save(tag);
        return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody Tag tag) {
        Tag updatedTag = tagService.update(id, tag);
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        tagService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> deleteMany(@RequestParam List<Long> ids) {
        tagService.deleteAll(ids);
        return new ResponseEntity<>(ids, HttpStatus.OK);
    }
}
