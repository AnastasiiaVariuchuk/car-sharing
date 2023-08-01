package com.example.carsharing.service.impl;

import java.util.NoSuchElementException;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.UserService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Not found user by id: " + userId)
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(
                () -> new NoSuchElementException("Not found user by email: " + email)
        );
    }

    @Override
    @Transactional
    public User updateUserRole(Long id, User.Role role) {
        User userFromDb = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found user with id: " + id)
        );
        userFromDb.setRole(role);
        return userFromDb;
    }

    @Override
    @Transactional
    public User updateProfileInfo(User user) {
        User userFromDb = userRepository.findById(user.getId()).orElseThrow(
                () -> new RuntimeException("Not found profile info for user: " + user));
        userFromDb.setId(user.getId());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setRole(user.getRole());
        return userFromDb;
    }
}
