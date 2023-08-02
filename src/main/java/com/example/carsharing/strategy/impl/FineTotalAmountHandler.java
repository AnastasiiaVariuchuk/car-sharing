package com.example.carsharing.strategy.impl;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.strategy.TotalAmountHandler;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class FineTotalAmountHandler implements TotalAmountHandler {
    private static final BigDecimal PER_HOUR = BigDecimal.valueOf(5);
    private static final BigDecimal FINE_MULTIPLIER = BigDecimal.valueOf(1.25);

    @Override
    public BigDecimal getTotalAmount(Rental rental) {
        BigDecimal rentHours = BigDecimal.valueOf(getRentHours(rental));
        return rentHours.multiply(PER_HOUR).multiply(FINE_MULTIPLIER);
    }

    @Override
    public boolean isApplicable(Payment.Type type) {
        return type == Payment.Type.FINE;
    }
}
