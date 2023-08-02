package com.example.carsharing.controller;

import java.time.LocalDateTime;
import java.util.List;
import com.example.carsharing.model.Rental;
import com.example.carsharing.model.User;
import com.example.carsharing.service.RentalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // POST: /rentals
    @PostMapping
    public ResponseEntity<Rental> addRental(@RequestParam Long carId,
                                            @RequestParam Long userId,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                                LocalDateTime returnDate) {
        Rental rental = rentalService.add(carId, userId, returnDate);
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }

    // GET: /rentals?user_id=...&is_active=...
    @GetMapping
    public ResponseEntity<List<Rental>> getRentals(@RequestParam Long user_id,
                                                   @RequestParam boolean is_active) {
        User user = new User();
        user.setId(user_id);
        List<Rental> rentals = rentalService.getByUserAndActiveness(user, is_active);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    // GET: /rentals/<id>
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    // POST: /rentals/<id>/return
    @PostMapping("/{id}/return")
    public ResponseEntity<Rental> returnRental(@PathVariable Long id) {
        Rental rental = rentalService.terminate(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }
}
