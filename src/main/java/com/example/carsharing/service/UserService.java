package com.example.carsharing.service;

import com.example.carsharing.model.User;

public interface UserService {
    User add(User user);

    User getById(Long userId);

    User getUserByEmail(String email);

    User updateUserRole(Long id, User.Role role);

    User updateProfileInfo(User user);
}
