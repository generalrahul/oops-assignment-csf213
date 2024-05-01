package com.bits.csf213.roombooking.controller;

import com.bits.csf213.roombooking.dto.BookingHistoryResponse;
import com.bits.csf213.roombooking.dto.LoginDTO;
import com.bits.csf213.roombooking.model.Booking;
import com.bits.csf213.roombooking.model.User;
import com.bits.csf213.roombooking.repository.BookingRepository;
import com.bits.csf213.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User user = userService.registerUser(newUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        } catch (Exception e) { // Catch other potential exceptions
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByIdQueryParam(@RequestParam Long userId) {
        User user = userService.findUserById(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<BookingHistoryResponse>> getBookingHistory(@RequestParam Long userID) {
        try {

            LocalDateTime currentTime = LocalDateTime.now();

            List<Booking> bookings = bookingRepository.findByUserUserID(userID);

            System.out.println(ChronoLocalDate.from(currentTime));
            bookings = bookings.stream()
                    .filter(booking -> booking.getDateOfBooking().atStartOfDay().isBefore(currentTime))
                    .collect(Collectors.toList());

            // Transform bookings into BookingHistoryResponse objects
            List<BookingHistoryResponse> historyResponses = bookings.stream()
                    .map(booking -> new BookingHistoryResponse(
                            booking.getRoom().getRoomName(),
                            booking.getRoom().getRoomId(),
                            booking.getBookingId(),
                            booking.getDateOfBooking(),
                            booking.getTimeFrom(),
                            booking.getTimeTo(),
                            booking.getPurpose()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(historyResponses);
        } catch (Exception e) {
            // Handle exceptions and return appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<BookingHistoryResponse>> getUpcomingBookings(@RequestParam Long userID) {
        try {
            LocalDateTime currentTime = LocalDateTime.now();

            List<Booking> bookings = bookingRepository.findByUserUserID(userID);

            // Filter bookings to include only those with booking dates after the current time
            bookings = bookings.stream()
                    .filter(booking -> booking.getDateOfBooking().atStartOfDay().isAfter(currentTime))
                    .collect(Collectors.toList());

            // Transform filtered bookings into BookingHistoryResponse objects
            List<BookingHistoryResponse> upcomingResponses = bookings.stream()
                    .map(booking -> new BookingHistoryResponse(
                            booking.getRoom().getRoomName(),
                            booking.getRoom().getRoomId(),
                            booking.getBookingId(),
                            booking.getDateOfBooking(),
                            booking.getTimeFrom(),
                            booking.getTimeTo(),
                            booking.getPurpose()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(upcomingResponses);
        } catch (Exception e) {
            // Handle exceptions and return appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
