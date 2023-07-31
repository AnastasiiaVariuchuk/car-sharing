package com.example.carsharing.service.impl;

import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
