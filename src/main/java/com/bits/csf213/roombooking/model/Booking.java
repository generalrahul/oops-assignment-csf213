package com.bits.csf213.roombooking.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "roomID", nullable = false)
    private Room room;

    @Column(nullable = false)
    private LocalDate dateOfBooking;

    @Column(nullable = false)
    private LocalTime timeFrom;

    @Column(nullable = false)
    private LocalTime timeTo;

    @Column(nullable = false)
    private String purpose;

    // Constructors
    public Booking() {}

    // Getters and Setters
    public Long getBookingId() {
        return bookingID;
    }

    public void setBookingId(Long bookingId) {
        this.bookingID = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getUserId() {
        return user != null ? user.getUserId() : null;
    }

    public Long getRoomId() {
        return room != null ? room.getRoomId() : null;
    }


}




