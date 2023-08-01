package com.example.carsharing.service;

import com.example.carsharing.model.User;

public interface UserService {
    User updateUserRole(Long id, User.Role role);

    User getProfileInfo(User user);

    User updateProfileInfo(User user);
}
