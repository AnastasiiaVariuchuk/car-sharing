package com.example.carsharing.strategy;

import static java.time.temporal.ChronoUnit.DAYS;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import java.math.BigDecimal;

public interface TotalAmountHandler {
    BigDecimal getTotalAmount(Rental rental, BigDecimal dailyFee);

    boolean isApplicable(Payment.Type type);

    default Long getRentDays(Rental rental) {
        return DAYS.between(rental.getRentalDate(), rental.getActualReturnDate());
    }
}
