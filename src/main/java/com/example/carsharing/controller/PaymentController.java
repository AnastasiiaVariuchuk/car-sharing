package com.example.carsharing.controller;

import com.example.carsharing.dto.SessionResponseDto;
import com.example.carsharing.dto.request.RentalRequestDto;
import com.example.carsharing.mapper.RentalMapper;
import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.payment.PaymentProvider;
import com.example.carsharing.service.PaymentService;
import com.example.carsharing.strategy.TotalAmountHandlerStrategy;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("payments/")
public class PaymentController {
    private PaymentProvider paymentProvider;
    private RentalMapper rentalMapper;
    private PaymentService paymentService;
    private TotalAmountHandlerStrategy strategy;

    @GetMapping("success/")
    public String successPayment() {
        return "Successful payment";
    }

    @GetMapping("cancel/")
    public String cancelPayment() {
        return "Something went wrong";
    }

    @PostMapping
    public Session createSession(@RequestBody RentalRequestDto rentalRequestDto) throws StripeException {
        Rental rental = rentalMapper.mapToEntity(rentalRequestDto);
        Payment payment = new Payment();
        payment.setId(rental.getId());
        payment.setStatus(Payment.Status.PENDING);
        payment.setType(paymentService.findType(rental));
        payment.setAmountToPay(strategy.getToTalAmountHandler(payment.getType())
                .getTotalAmount(rental));
        Session session = paymentProvider.createSession(payment);
        payment.setSessionId(session.getId());
        payment.setSessionUrl(session.getUrl());
        // TODO: create payment service and dao and save payment
        return session; //TODO: crate SessionResponseDto
    }

}
