package com.bits.csf213.roombooking.repository;

import com.bits.csf213.roombooking.model.Booking;
import com.bits.csf213.roombooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByUserUserID(Long userId);
    // Updated Method Names
    List<Booking> findByUserAndTimeFromBefore(User user, LocalDateTime now);
    List<Booking> findByUserAndTimeFromAfter(User user, LocalDateTime now);
}
