package com.bits.csf213.roombooking.service;

import com.bits.csf213.roombooking.model.Booking;
import com.bits.csf213.roombooking.model.Room;
import com.bits.csf213.roombooking.model.User;
import com.bits.csf213.roombooking.exception.BookingException;
import com.bits.csf213.roombooking.repository.BookingRepository;
import com.bits.csf213.roombooking.dto.EditBookingRequest;
import com.bits.csf213.roombooking.repository.UserRepository;
import com.bits.csf213.roombooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;



@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<Booking> findAllBookings() {
        System.out.println("findAllBookings() called"); // Temporary logging
        List<Booking> bookings = bookingRepository.findAll();
        System.out.println("Found " + bookings.size() + " bookings"); // Temporary logging
        return bookings;
    }

    @Override
    public List<Booking> findBookingsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return bookingRepository.findByUser(user);
        }
        return List.of();
    }

    @Override
    public List<Booking> findBookingHistoryByUserId(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return bookingRepository.findByUserAndTimeFromBefore(user, now);
        }
        return List.of();
    }

    @Override
    public List<Booking> findUpcomingBookingsByUserId(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return bookingRepository.findByUserAndTimeFromAfter(user, now);
        }
        return List.of();
    }

    public void validateBooking(Booking booking) throws BookingException {
        // Check if the user exists
        if (!userRepository.existsById(booking.getUser().getUserId())) {
            throw new BookingException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        // Check if the room exists
        if (!roomRepository.existsById(booking.getRoom().getRoomId())) {
            throw new BookingException(HttpStatus.NOT_FOUND, "Room does not exist");
        }
    }

    public void editBooking(Long bookingId, Booking updatedBooking) throws BookingException {
        // Check if the booking exists
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingException(HttpStatus.NOT_FOUND, "Booking does not exist"));

        // Update the fields of the existing booking with the new values
        existingBooking.setDateOfBooking(updatedBooking.getDateOfBooking());
        existingBooking.setTimeFrom(updatedBooking.getTimeFrom());
        existingBooking.setTimeTo(updatedBooking.getTimeTo());
        existingBooking.setPurpose(updatedBooking.getPurpose());

        // Save the updated booking
        bookingRepository.save(existingBooking);
    }


    private boolean isDateTimeValid(LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo) {
        // Ensure date is not in the past
        if (dateOfBooking.isBefore(LocalDate.now())) {
            return false;
        }
        if (timeFrom.isAfter(timeTo)) {
            return false;
        }
        return true;
    }

}
