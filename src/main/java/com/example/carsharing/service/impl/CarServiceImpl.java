package com.example.carsharing.service.impl;

import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
}
