package repository;

import model.Booking;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByUserAndStartTimeBefore(User user, LocalDateTime now);
    List<Booking> findByUserAndStartTimeAfter(User user, LocalDateTime now);
}