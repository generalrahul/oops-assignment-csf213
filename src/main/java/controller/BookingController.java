package controller;

import model.Booking;
import service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.findAllBookings();
        return ResponseEntity.ok(bookings);
    }

//    @PostMapping
//    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
//        Booking createdBooking = bookingService.createBooking(booking);
//        return ResponseEntity.ok(createdBooking);
//    }
//
//    @PutMapping("/{bookingId}")
//    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking booking) {
//        booking.setBookingId(bookingId);
//        Booking updatedBooking = bookingService.updateBooking(booking);
//        return ResponseEntity.ok(updatedBooking);
//    }
//
//    @GetMapping("/{userId}/history")
//    public ResponseEntity<List<Booking>> getBookingHistoryByUserId(@PathVariable Long userId) {
//        List<Booking> bookings = bookingService.findBookingHistoryByUserId(userId);
//        return ResponseEntity.ok(bookings);
//    }
//
//    @GetMapping("/{userId}/upcoming")
//    public ResponseEntity<List<Booking>> getUpcomingBookingsByUserId(@PathVariable Long userId) {
//        List<Booking> bookings = bookingService.findUpcomingBookingsByUserId(userId);
//        return ResponseEntity.ok(bookings);
//    }
//
//    @DeleteMapping("/{bookingId}")
//    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) {
//        bookingService.deleteBooking(bookingId);
//        return ResponseEntity.ok().build();
//    }
}
