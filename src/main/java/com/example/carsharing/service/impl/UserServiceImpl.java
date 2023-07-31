package com.example.carsharing.service.impl;

import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.UserService;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
