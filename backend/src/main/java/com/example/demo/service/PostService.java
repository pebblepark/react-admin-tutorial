package com.example.demo.service;

import com.example.demo.domain.file.File;
import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.post.tag.PostTag;
import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.common.FileDTO;
import com.example.demo.dto.common.Search;
import com.example.demo.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileService fileService;
    private final PostTagService postTagService;

    public Long findAllCount(Search search) {
        return postRepository.findAllCount(search);
    }

    public List<Post> findAllBy(Search search) {
        return postRepository.findAllBy(search);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));
    }

    public Post save(Post post, FileDTO file) {
        if(file != null) {
            File savedFile = (file.getId() != null)
                 ? FileMapper.MAPPER.toEntity(file)
                 : fileService.registFile(file);
            post.addFile(savedFile);
        }
        if(post.getTags() != null) {
            postTagService.saveAll(post, post.getTags());
        }
        return postRepository.save(post);
    }

    public List<Tag> findTags(Post post) {
        return postTagService.findByPost(post);
    }

    @Transactional
    public Post update(Long id, Post updatePost, FileDTO file) {
        Post findPost = findById(id);
        findPost.patch(updatePost);
        return save(findPost, file);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(List<Long> ids) {
        postRepository.deleteAllById(ids);
    }




}
