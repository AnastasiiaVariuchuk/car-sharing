package com.example.carsharing.repository;

import com.example.carsharing.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
