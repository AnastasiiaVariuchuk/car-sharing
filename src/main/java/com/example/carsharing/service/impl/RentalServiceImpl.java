package com.example.carsharing.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.carsharing.model.Car;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.RentalRepository;
import com.example.carsharing.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    @Override
    public Rental add(Car car, User user, LocalDateTime returnDate) {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setReturnDate(returnDate);
        rental.setCar(car);
        rental.setUser(user);
        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getByUserAndActiveness(User user, boolean isActive) {
        return isActive ? rentalRepository.findRentalByUserAndActualReturnDateIsNull(user) :
                rentalRepository.findRentalByUserAndActualReturnDateIsNotNull(user);
    }

    @Override
    public Rental getById(Long id) {
        return rentalRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't get rental with id: " + id));
    }

    @Override
    public Rental terminateRent(Long id) {
        Rental rental = getById(id);
        rental.setActualReturnDate(LocalDateTime.now());
        rentalRepository.save(rental);
        return rental;
    }
}
