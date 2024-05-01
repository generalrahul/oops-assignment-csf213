package com.bits.csf213.roombooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateBookingRequest {
    private LocalDate dateOfBooking;
    private String purpose;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private Long roomID;
    private Long userID;

    // Getters and Setters
    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserId(Long userID) {
        this.userID = userID;
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
}

