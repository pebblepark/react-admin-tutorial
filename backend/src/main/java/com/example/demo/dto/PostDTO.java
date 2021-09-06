package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.common.FileDTO;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.TagMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private Integer views;
    private UserDTO user;
    private List<TagDTO> tags;
    private FileDTO file;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime creTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updTime;

    public Post toEntity() {
        Post post = PostMapper.MAPPER.toEntity(this);
        if(tags != null) {
            List<Tag> tags = this.tags.stream().map(TagMapper.MAPPER::toEntity).collect(Collectors.toList());
            post.addTags(tags);
        }

        return post;
    }

    public static PostDTO toDto(Post post) {
        PostDTO postDTO = PostMapper.MAPPER.toDto(post);

        if(post.getTags() != null) {
            List<TagDTO> tagDtos = post.getTags().stream()
                    .map(postTag -> TagMapper.MAPPER.toDto(postTag.getTag()))
                    .collect(Collectors.toList());
            postDTO.setTags(tagDtos);
        }
        return postDTO;
    }

}
