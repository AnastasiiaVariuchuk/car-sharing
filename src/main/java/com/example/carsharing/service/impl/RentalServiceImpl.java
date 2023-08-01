package com.example.carsharing.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.carsharing.model.Car;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.RentalRepository;
import com.example.carsharing.service.CarService;
import com.example.carsharing.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final int MIN_AMOUNT_TO_RENT = 1;
    private final RentalRepository rentalRepository;
    private final CarService carService;

    @Override
    public Rental add(Car car, User user, LocalDateTime returnDate) {
        if (car.getInventory() < MIN_AMOUNT_TO_RENT) {
            throw new RuntimeException("Can't rent car with id: " + car.getId());
        }
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
    public Rental terminate(Long id) {
        Rental rental = getById(id);
        if (rental.getActualReturnDate() != null) {
            throw new RuntimeException("Rental with id " + id + " already terminated");
        }
        rental.setActualReturnDate(LocalDateTime.now());
        rentalRepository.save(rental);
        Car car = rental.getCar();
        car.setInventory(car.getInventory() + 1);
        carService.update(car.getId(), car);
        return rental;
    }
}
