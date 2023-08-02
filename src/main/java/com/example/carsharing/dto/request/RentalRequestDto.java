package com.example.carsharing.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RentalRequestDto {
    private Long id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    private Long carId;
    private Long userId;
}
