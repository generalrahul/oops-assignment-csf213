package service;

import model.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(Long bookingId);
    List<Booking> findBookingsByUserId(Long userId);
    List<Booking> findBookingHistoryByUserId(Long userId);
    List<Booking> findUpcomingBookingsByUserId(Long userId);
}