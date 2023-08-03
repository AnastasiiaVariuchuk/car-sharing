package com.example.carsharing.dto.response;

import com.example.carsharing.model.Car;
import com.example.carsharing.model.User;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RentalResponseDto {
    private Long id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    private Car car;
    private User user;
}
