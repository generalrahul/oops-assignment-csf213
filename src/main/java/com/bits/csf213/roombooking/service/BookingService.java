package com.bits.csf213.roombooking.service;

import com.bits.csf213.roombooking.model.Booking;
import com.bits.csf213.roombooking.dto.EditBookingRequest;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(Long bookingId);
    List<Booking> findBookingsByUserId(Long userId);
    List<Booking> findBookingHistoryByUserId(Long userId);
    List<Booking> findUpcomingBookingsByUserId(Long userId);
    List<Booking> findAllBookings();  // Add this method\
    void validateBooking(Booking booking);
    void editBooking(Long BookingID, Booking booking);
}