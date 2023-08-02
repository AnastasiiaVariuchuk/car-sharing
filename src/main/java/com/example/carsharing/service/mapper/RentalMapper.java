package com.example.carsharing.service.mapper;

import com.example.carsharing.config.MapperConfig;
import com.example.carsharing.dto.request.RentalRequestDto;
import com.example.carsharing.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RentalMapper {
    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "userId", target = "user.id")
    Rental mapToEntity(RentalRequestDto rentalRequestDto);
}
