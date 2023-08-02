package com.example.carsharing.strategy;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public interface TotalAmountHandler {
    BigDecimal getTotalAmount(Rental rental);

    boolean isApplicable(Payment.Type type);

    default Long getRentHours(Rental rental) {
        return rental.getRentalDate().until(rental.getActualReturnDate(), ChronoUnit.HOURS);
    }
}
