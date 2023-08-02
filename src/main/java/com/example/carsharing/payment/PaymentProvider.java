package com.example.carsharing.payment;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentProvider {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }
    private String currency = "usd";
    private String successUrl = "https://localhost:8080/payments/success/";
    private String cancelUrl = "https://localhost:8080/payments/cancel/";

    public Session createSession(Payment payment) throws StripeException {
        SessionCreateParams.Builder builder = new SessionCreateParams.Builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(currency)
                                                .setUnitAmount(payment.getAmountToPay().longValue())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Payment")
                                                                .setDescription("Description")
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity(1L)
                                .build()
                )
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl);

        SessionCreateParams createParams = builder.build();
        return Session.create(createParams);
    }
}
