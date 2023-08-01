package com.example.carsharing.service.impl;

import com.example.carsharing.model.Car;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;


    @Override
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found car with id: " + id));
    }

    @Override
    @Transactional
    public Car update(Long id, Car car) {
        Car carFromDb = carRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found car with id: " + id));
        carFromDb.setId(car.getId());
        carFromDb.setModel(car.getModel());
        carFromDb.setBrand(car.getBrand());
        carFromDb.setType(car.getType());
        carFromDb.setInventory(car.getInventory());
        carFromDb.setDailyFee(car.getDailyFee());
        return carFromDb;
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
