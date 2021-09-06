package com.example.demo.controller;

import com.example.demo.common.ResponseUtil;
import com.example.demo.domain.user.User;
import com.example.demo.dto.common.Search;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@ModelAttribute Search search) {
        Long count = userService.findAllCount(search);
        if (search.getLimit() == 0) {
            search.setLimit(count);
        }
        List<UserDTO> users = userService.findAllBy(search)
                .stream().map(this::convertEntityToDto).collect(Collectors.toList());

        return ResponseUtil.getListResponse(users, count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(convertEntityToDto(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        User savedUser = userService.save(convertDtoToEntity(userDTO));
        return new ResponseEntity<>(convertEntityToDto(savedUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.update(id, convertDtoToEntity(userDTO));
        return new ResponseEntity<>(convertEntityToDto(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> deleteMany(@RequestParam List<Long> ids) {
        userService.deleteAll(ids);
        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

    private UserDTO convertEntityToDto(User user) {
        return UserMapper.MAPPER.toDto(user);
    }

    private User convertDtoToEntity(UserDTO userDTO) {
        return UserMapper.MAPPER.toEntity(userDTO);
    }
}
