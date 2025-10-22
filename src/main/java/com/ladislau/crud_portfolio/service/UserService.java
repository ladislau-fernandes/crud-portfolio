package com.ladislau.crud_portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ladislau.crud_portfolio.model.User;
import com.ladislau.crud_portfolio.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
