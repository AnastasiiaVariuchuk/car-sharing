package com.example.carsharing.service.mapper;

import com.example.carsharing.config.MapperConfig;
import com.example.carsharing.dto.request.RentalRequestDto;
import com.example.carsharing.dto.response.RentalResponseDto;
import com.example.carsharing.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RentalMapper {
    RentalResponseDto mapToDto(Rental rental);

    @Mapping(target = "id", ignore = true)
    Rental mapToEntity(RentalRequestDto rentalRequestDto);
}
