package com.example.carsharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.PaymentRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentServiceImpl paymentService;
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setStatus(Payment.Status.PENDING);
        payment.setType(Payment.Type.PAYMENT);
        payment.setAmountToPay(BigDecimal.TEN);
    }

    @Test
    void add_validPayment_ok() {
        Long expected = 1L;
        when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment(expected));
        Long actual = paymentService.add(payment).getId();
        assertEquals(expected, actual);
    }

    @Test
    void getByUser_validUser_ok() {
        User user = new User(1L);
        payment.setId(1L);
        when(paymentRepository.getByUser(any(User.class))).thenReturn(List.of(payment));
        List<Payment> actual = paymentService.getByUser(user);
        int expectedSize = 1;
        assertEquals(expectedSize, actual.size());
        assertEquals(payment.getId(), actual.get(0).getId());
    }

    @Test
    void findType_paymentType_ok() {
        Rental rental = new Rental();
        rental.setReturnDate(LocalDateTime.of(2023, 8, 2, 10,0));
        rental.setActualReturnDate(LocalDateTime.of(2023, 8, 2, 9, 0));
        Payment.Type expected = Payment.Type.PAYMENT;
        Payment.Type actual = paymentService.findType(rental);
        assertEquals(expected, actual);
    }

    @Test
    void findType_fineType_ok() {
        Rental rental = new Rental();
        rental.setReturnDate(LocalDateTime.of(2023, 8, 2, 10,0));
        rental.setActualReturnDate(LocalDateTime.of(2023, 8, 3, 10, 0));
        Payment.Type expected = Payment.Type.FINE;
        Payment.Type actual = paymentService.findType(rental);
        assertEquals(expected, actual);
    }

    @Test
    void setPaid_validSession_ok() {
        String sessionId = "session_id";
        when(paymentRepository.getBySessionId(sessionId)).thenReturn(payment);
        Payment.Status expected = Payment.Status.PAID;
        Payment actual = paymentService.setPaid(sessionId);
        assertEquals(expected, actual.getStatus());
    }
}
