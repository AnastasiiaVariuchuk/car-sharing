package com.example.carsharing.strategy.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.strategy.TotalAmountHandler;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class FineTotalAmountHandler implements TotalAmountHandler {
    private static final BigDecimal FINE_MULTIPLIER = BigDecimal.valueOf(1.25);

    @Override
    public BigDecimal getTotalAmount(Rental rental, BigDecimal dailyFee) {
        BigDecimal rentDays = getRentDays(rental);
        BigDecimal finePay = getOverdueDays(rental).multiply(dailyFee).multiply(FINE_MULTIPLIER);
        return rentDays.multiply(dailyFee).add(finePay);
    }

    private BigDecimal getOverdueDays(Rental rental) {
        return BigDecimal.valueOf(DAYS.between(rental.getReturnDate(),
                rental.getActualReturnDate()));
    }

    @Override
    public boolean isApplicable(Payment.Type type) {
        return type == Payment.Type.FINE;
    }
}
