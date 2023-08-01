package com.example.carsharing.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    List<Rental> findRentalByUserAndActualReturnDateIsNull(User user);

    List<Rental> findRentalByUserAndActualReturnDateIsNotNull(User user);
}
