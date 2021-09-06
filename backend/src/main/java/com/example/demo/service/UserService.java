package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.dto.common.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long findAllCount(Search search) {
        return userRepository.findAllCount(search);
    }

    public List<User> findAllBy(Search search) {
        return userRepository.findAllBy(search);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User updateUser) {
        User findUser = findById(id);
        findUser.patch(updateUser);
        return save(findUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
}
