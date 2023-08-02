package com.example.carsharing.service;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import java.util.List;

public interface PaymentService {
    Payment add(Payment payment);

    List<Payment> getByUser(User user);

    Payment.Type findType(Rental rental);

    void setPaid(String sessionId);
}
