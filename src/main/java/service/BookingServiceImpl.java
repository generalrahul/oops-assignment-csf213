package service;

import model.Booking;
import model.User;
import repository.BookingRepository;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

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
            return bookingRepository.findByUserAndStartTimeBefore(user, now);
        }
        return List.of();
    }

    @Override
    public List<Booking> findUpcomingBookingsByUserId(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return bookingRepository.findByUserAndStartTimeAfter(user, now);
        }
        return List.of();
    }
}
