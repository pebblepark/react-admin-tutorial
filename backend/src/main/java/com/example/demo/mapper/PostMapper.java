package com.example.demo.mapper;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.tag.PostTag;
import com.example.demo.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        uses = {UserMapper.class, FileMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PostMapper extends GenericMapper<PostDTO, Post> {
    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

//    @Mapping(target="tags", ignore = true)
//    PostDTO toDto(Post e);
//
//    @Mapping(target="tags", ignore = true)
//    Post toEntity(PostDTO d);
//
//    default PostDTO toMappingDto(Post e) {
//        PostDTO d = toDto(e);
//        List<Long> tags = e.getTags().stream().map(postTag -> postTag.getTag().getId()).collect(Collectors.toList());
//        d.setTags(tags);
//        return d;
//    }
//
//    default Post toMappingEntity(PostDTO d) {
//        Post e = toEntity(d);
//        Set<PostTag> tags = d.getTags().stream().map(tagId -> new PostTag(d.getId(), tagId)).collect(Collectors.toSet());
//        e.addTags(tags);
//        return e;
//    }
}
