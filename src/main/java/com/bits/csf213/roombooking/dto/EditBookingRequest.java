package com.bits.csf213.roombooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class EditBookingRequest {
    private Long bookingID;
    private Optional<Long> userID = Optional.empty();
    private Optional<Long> roomID = Optional.empty();
    private Optional<LocalDate> dateOfBooking = Optional.empty();
    private Optional<LocalTime> timeFrom = Optional.empty();
    private Optional<LocalTime> timeTo = Optional.empty();
    private Optional<String> purpose = Optional.empty();

    // Getters and Setters
    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingId) {
        this.bookingID = bookingId;
    }

    public Optional<Long> getUserID() {
        return userID;
    }

    public void setUserId(Long userId) {
        this.userID = Optional.ofNullable(userId);
    }

    public Optional<Long> getRoomID() {
        return roomID;
    }

    public void setRoomId(Long roomId) {
        this.roomID = Optional.ofNullable(roomId);
    }

    public Optional<LocalDate> getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = Optional.ofNullable(dateOfBooking);
    }

    public Optional<LocalTime> getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = Optional.ofNullable(timeFrom);
    }

    public Optional<LocalTime> getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = Optional.ofNullable(timeTo);
    }

    public Optional<String> getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = Optional.ofNullable(purpose);
    }
}
