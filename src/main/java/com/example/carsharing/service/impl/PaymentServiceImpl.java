package com.example.carsharing.service.impl;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment.Type findType(Rental rental) {
        if (rental.getActualReturnDate().isAfter(rental.getReturnDate())) {
            return Payment.Type.FINE;
        }
        return Payment.Type.PAYMENT;
    }
}
