package com.example.demo.mapper;

import com.example.demo.domain.user.User;
import com.example.demo.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends GenericMapper<UserDTO, User> {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

}
