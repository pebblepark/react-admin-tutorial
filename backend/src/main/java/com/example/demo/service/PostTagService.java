package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.tag.PostTag;
import com.example.demo.domain.post.tag.PostTagRepository;
import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostTagService {

    private final PostTagRepository postTagRepository;

    public List<Tag> findByPost(Post post) {
        return postTagRepository.findByPost(post);
    }

    @Transactional
    public List<PostTag> saveAll(Post post, List<PostTag> tags) {
        List<PostTag> savedTags = new ArrayList<>();
        postTagRepository.deleteByPost(post);
        for (PostTag postTag : tags) {
            savedTags.add(postTagRepository.save(postTag));
        }
        return savedTags;
    }

    public List<Post> findPostByTag(Tag tag) {
        return postTagRepository.findPostByTag(tag);
    }
}
