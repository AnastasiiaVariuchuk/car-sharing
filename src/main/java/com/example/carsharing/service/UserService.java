package com.example.carsharing.service;

import com.example.carsharing.model.User;

public interface UserService {
    User add(User user);

    User updateUserRole(Long id, User.Role role);

    User getProfileInfo(User user);

    User updateProfileInfo(User user);

    User getById(Long userId);
}
