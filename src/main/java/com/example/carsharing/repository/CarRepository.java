package com.example.carsharing.repository;

import com.example.carsharing.model.Car;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
