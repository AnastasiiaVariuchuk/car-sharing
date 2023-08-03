package com.example.carsharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.carsharing.exception.AlreadyTerminatedRentalException;
import com.example.carsharing.exception.NotEnoughCarInventoryException;
import com.example.carsharing.model.Car;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.repository.RentalRepository;
import com.example.carsharing.service.CarService;
import com.example.carsharing.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RentalServiceImplTest {
    public static final Long CAR_ID = 1L;
    public static final Long USER_ID = 2L;
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

    @BeforeEach
    public void setUp() {
        car = new Car();
        car.setId(USER_ID);

        user = new User();
        user.setId(USER_ID);
    }

    @Test
    public void add_enoughCarInventory_ok() {
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
    public void add_notEnoughCarInventory_ok() {
        car.setInventory(0);
        when(carService.getById(CAR_ID)).thenReturn(car);

        assertThrows(NotEnoughCarInventoryException.class,
                () -> rentalService.add(CAR_ID, USER_ID, LocalDateTime.now()));
    }

    @Test
    public void getByUserAndActiveness_rentalIsActive_ok() {
        boolean isActive = true;
        when(rentalRepository.findRentalByUserAndActualReturnDateIsNull(user))
                .thenReturn(List.of(new Rental()));
        List<Rental> rentals = rentalService.getByUserAndActiveness(user, isActive);
        assertNotNull(rentals);
        assertFalse(rentals.isEmpty());
    }

    @Test
    public void getByUserAndActiveness_rentalIsNotActive_ok() {
        boolean isActive = false;
        when(rentalRepository.findRentalByUserAndActualReturnDateIsNotNull(user))
                .thenReturn(List.of(new Rental()));
        List<Rental> rentals = rentalService.getByUserAndActiveness(user, isActive);
        assertNotNull(rentals);
        assertFalse(rentals.isEmpty());
    }

    @Test
    public void getById_rentalIsPresentInRepositoryById_ok() {
        Long rentalId = 1L;
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(new Rental()));
        Rental rental = rentalService.getById(rentalId);
        assertNotNull(rental);
    }

    @Test
    public void getById_rentalIsAbsentInRepositoryById_notOk() {
        Long rentalId = 1L;
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> rentalService.getById(rentalId));
    }

    @Test
    public void terminate_rentalIsAlreadyTerminated_notOk() {
        Long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        rental.setActualReturnDate(LocalDateTime.now());
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        assertThrows(AlreadyTerminatedRentalException.class,
                () -> rentalService.terminate(rentalId));
    }

    @Test
    public void terminate_rentalIsNotTerminated_ok() {
        Long rentalId = 1L;
        Rental rental = new Rental();
        Car car = new Car();
        car.setInventory(1);
        rental.setCar(car);
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        Rental terminatedRental = rentalService.terminate(rentalId);
        assertEquals(rental, terminatedRental);
    }
}
