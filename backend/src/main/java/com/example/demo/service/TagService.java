package com.example.demo.service;

import com.example.demo.domain.tag.Tag;
import com.example.demo.domain.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Transactional
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    public Tag update(Long id, Tag tag) {
        Tag findTag = findById(id);
        findTag.patch(tag);
        return save(findTag);
    }

    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 태그입니다."));
    }

    @Transactional
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(List<Long> ids) {
        tagRepository.deleteAllById(ids);
    }

}
