package com.example.demo.mapper;

import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper extends GenericMapper<TagDTO, Tag> {
    TagMapper MAPPER = Mappers.getMapper(TagMapper.class);
}
