package com.example.carsharing.service;

import com.example.carsharing.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User getById(Long userId);

    Optional<User> getUserByEmail(String email);

    boolean isUserPresentByEmail(String email);

    User updateUserRole(Long id, User.Role role);

    User updateProfileInfo(User user);
}
