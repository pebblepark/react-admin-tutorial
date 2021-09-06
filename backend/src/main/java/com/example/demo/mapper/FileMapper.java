package com.example.demo.mapper;

import com.example.demo.domain.file.File;
import com.example.demo.dto.common.FileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper extends GenericMapper<FileDTO, File> {
    FileMapper MAPPER = Mappers.getMapper(FileMapper.class);
}
