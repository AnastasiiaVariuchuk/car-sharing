package com.example.carsharing.mapper;

import com.example.carsharing.dto.request.RentalRequestDto;
import com.example.carsharing.model.Car;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public Rental mapToEntity(RentalRequestDto rentalRequestDto) {
        Rental rental = new Rental();

        rental.setId(rentalRequestDto.getId());
        rental.setRentalDate(rentalRequestDto.getRentalDate());
        rental.setReturnDate(rentalRequestDto.getReturnDate());
        rental.setActualReturnDate(rentalRequestDto.getActualReturnDate());
        rental.setUser(new User(rentalRequestDto.getUserId()));
        rental.setCar(new Car(rentalRequestDto.getCarId()));
        return rental;
    }
}
