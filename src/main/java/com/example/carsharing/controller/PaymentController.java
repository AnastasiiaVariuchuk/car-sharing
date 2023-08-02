package com.example.carsharing.controller;

//<<<<<<< HEAD

import com.example.carsharing.dto.SessionResponseDto;
import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.payment.PaymentProvider;
import com.example.carsharing.service.CarService;
import com.example.carsharing.service.PaymentService;
import com.example.carsharing.service.RentalService;
import com.example.carsharing.service.mapper.SessionMapper;
import com.example.carsharing.strategy.TotalAmountHandlerStrategy;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("payments/")
public class PaymentController {
    private PaymentProvider paymentProvider;
    private RentalService rentalService;
    private PaymentService paymentService;
    private TotalAmountHandlerStrategy strategy;
    private SessionMapper sessionMapper;

    @GetMapping("success/")
    public String successPayment() {
        return "Successful payment";
    }

    @GetMapping("cancel/")
    public String cancelPayment() {
        return "Something went wrong";
    }

    @PostMapping
    public SessionResponseDto createSession(@RequestParam Long rentalId)
            throws StripeException {
        Rental rental = rentalService.getById(rentalId);
        Payment payment = new Payment();
        payment.setId(rental.getId());
        payment.setRental(rental);
        payment.setStatus(Payment.Status.PENDING);
        payment.setType(paymentService.findType(rental));
        payment.setAmountToPay(strategy.getToTalAmountHandler(payment.getType())
                .getTotalAmount(rental));
        Session session = paymentProvider.createSession(payment, rental.getCar());
        payment.setSessionId(session.getId());
        payment.setSessionUrl(session.getUrl());
        paymentService.add(payment);
        return sessionMapper.mapToDto(session);
    }

}
