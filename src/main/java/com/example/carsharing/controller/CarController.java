package com.example.carsharing.controller;

import com.example.carsharing.dto.request.CarRequestDto;
import com.example.carsharing.dto.response.CarResponseDto;
import com.example.carsharing.service.CarService;
import com.example.carsharing.service.mapper.CarMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private CarService carService;
    private CarMapper carMapper;
    // POST /cars

    @PostMapping
    public CarResponseDto add(@RequestBody CarRequestDto carRequestDto) {
        return carMapper.mapToDto(carService.add(carMapper.mapToEntity(carRequestDto)));
    }
    // GET /cars

    @GetMapping
    public List<CarResponseDto> getAll() {
        return carService.getAll().stream()
                .map(carMapper::mapToDto)
                .toList();
    }
    // GET /cars?car_id=<id>

    @GetMapping
    public CarResponseDto getDetailedInfo(@RequestParam("car_id") Long carId) {
        return carMapper.mapToDto(carService.getById(carId));
    }
    // PATCH /cars?car_id=<id>

    @PatchMapping
    public CarResponseDto updateCar(@RequestParam("car_id") Long carId,
                                    @RequestBody CarRequestDto carRequestDto) {
        return carMapper.mapToDto(carService.update(carId, carMapper.mapToEntity(carRequestDto)));
    }
    //DELETE / cars?car_id=<id>

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("car_id") Long carId) {
        carService.delete(carId);
    }
}
