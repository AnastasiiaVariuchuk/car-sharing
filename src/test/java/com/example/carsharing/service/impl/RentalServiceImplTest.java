package com.example.carsharing.service.impl;

import com.example.carsharing.exception.NotEnoughCarInventoryException;
import com.example.carsharing.model.Car;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.RentalRepository;
import com.example.carsharing.service.CarService;
import com.example.carsharing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceImplTest {
    public static final Long CAR_ID = 1L;
    public static final Long USER_ID = 2L;
    public static final Long RENTAL_ID = 1L;
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private CarService carService;
    @Mock
    private UserService userService;

    @InjectMocks
    private RentalServiceImpl rentalService;
    private Car car;
    private User user;
    private Rental rental;

    @BeforeEach
    public void setUp() {
        car = new Car();
        car.setId(USER_ID);

        user = new User();
        user.setId(USER_ID);

        rental = new Rental();
        rental.setId(RENTAL_ID);
    }

    @Test
    public void testAddRental() {
        //Given
        car.setInventory(2);

        when(carService.getById(CAR_ID)).thenReturn(car);
        when(userService.getById(USER_ID)).thenReturn(user);
        //lightweit
        //intergration tests
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());

        //When
        Rental rental = rentalService.add(CAR_ID, USER_ID, LocalDateTime.now());

        //Then
        assertNotNull(rental);
    }

    @Test
    public void testAddRentalWhenInventoryIsNotEnough() {
        car.setInventory(0);
        when(carService.getById(CAR_ID)).thenReturn(car);

        assertThrows(NotEnoughCarInventoryException.class,
                () -> rentalService.add(CAR_ID, USER_ID, LocalDateTime.now()));
    }

    @Test
    public void testGetByUserAndActiveness() {
        boolean isActive = true;
        when(rentalRepository.findRentalByUserAndActualReturnDateIsNull(user))
                .thenReturn(List.of(new Rental()));
        List<Rental> rentals = rentalService.getByUserAndActiveness(user, isActive);
        assertNotNull(rentals);
        assertFalse(rentals.isEmpty());
    }

    @Test
    public void testGetById() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(new Rental()));
        Rental rental = rentalService.getById(RENTAL_ID);
        assertNotNull(rental);
    }

    @Test
    public void testGetById_NotFound() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> rentalService.getById(RENTAL_ID));
    }
}
