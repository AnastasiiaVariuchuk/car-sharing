package com.example.carsharing.service.mapper;

import com.example.carsharing.config.MapperConfig;
import com.example.carsharing.dto.SessionResponseDto;
import com.stripe.model.checkout.Session;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SessionMapper {
    SessionResponseDto mapToDto(Session session);
}
