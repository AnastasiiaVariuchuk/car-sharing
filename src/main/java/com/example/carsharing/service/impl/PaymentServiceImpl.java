package com.example.carsharing.service.impl;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.PaymentRepository;
import com.example.carsharing.service.PaymentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Override
    public Payment add(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getByUser(User user) {
        return paymentRepository.getByUser(user);
    }


    @Override
    public Payment.Type findType(Rental rental) {
        if (rental.getActualReturnDate().isAfter(rental.getReturnDate())) {
            return Payment.Type.FINE;
        }
        return Payment.Type.PAYMENT;
    }
}
