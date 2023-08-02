package com.example.carsharing.service;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;

public interface PaymentService {
    public Payment.Type findType(Rental rental);
}
