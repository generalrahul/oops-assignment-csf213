package com.bits.csf213.roombooking.controller;

import com.bits.csf213.roombooking.model.Booking;
import com.bits.csf213.roombooking.model.User;
import com.bits.csf213.roombooking.model.Room;
import com.bits.csf213.roombooking.repository.*;
import com.bits.csf213.roombooking.dto.*;
import com.bits.csf213.roombooking.service.BookingService;
import com.bits.csf213.roombooking.exception.BookingException;
import com.bits.csf213.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.findAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody CreateBookingRequest bookingRequest) {
        try {
            User user = userRepository.findById(bookingRequest.getUserID())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Room room = roomRepository.findById(bookingRequest.getRoomID())
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setRoom(room);
            booking.setDateOfBooking(bookingRequest.getDateOfBooking());
            booking.setTimeFrom(bookingRequest.getTimeFrom());
            booking.setTimeTo(bookingRequest.getTimeTo());
            booking.setPurpose(bookingRequest.getPurpose());

            bookingService.createBooking(booking);

            return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @PatchMapping
    public ResponseEntity<String> editBooking(@RequestBody EditBookingRequest bookingRequest) {
        try {
            // Find the existing booking
            System.out.println("Fetching user with ID: " + bookingRequest.getBookingID());
            System.out.println("Fetching user with ID: " + bookingRequest.getRoomID().get());
            Booking booking = bookingRepository.findById(bookingRequest.getBookingID())
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            // Update user if provided
            bookingRequest.getUserID().ifPresent(userId -> {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                booking.setUser(user);
            });

//            // Update room if provided
            bookingRequest.getRoomID().ifPresent(roomId -> {
                Room room = roomRepository.findById(roomId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                booking.setRoom(room);
            });


            // Update other booking details if provided
            bookingRequest.getDateOfBooking().ifPresent(booking::setDateOfBooking);
            bookingRequest.getTimeFrom().ifPresent(booking::setTimeFrom);
            bookingRequest.getTimeTo().ifPresent(booking::setTimeTo);
            bookingRequest.getPurpose().ifPresent(booking::setPurpose);

            // Validate the edit booking request
            bookingService.validateBooking(booking);

            // Update the booking
            bookingService.editBooking(bookingRequest.getBookingID(), booking);

            return ResponseEntity.status(HttpStatus.CREATED).body("Booking modified successfully");
        } catch (BookingException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }


    @DeleteMapping
    public ResponseEntity<String> deleteBooking(@RequestParam Long bookingID) {
        try {
            bookingService.deleteBooking(bookingID);
            return ResponseEntity.ok("Booking deleted successfully");
        } catch (BookingException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
