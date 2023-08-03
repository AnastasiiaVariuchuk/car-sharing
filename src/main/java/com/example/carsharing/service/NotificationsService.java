package com.example.carsharing.service;

import com.example.carsharing.model.Payment;
import com.example.carsharing.model.Rental;

import java.util.List;

public interface NotificationsService {
    void notify(String message);

    void newRentalParser(Rental rental);

    void overdueRentalsNotify(List<Rental> rentalOverdueList);
}
