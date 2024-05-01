package com.bits.csf213.roombooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingHistoryResponse {
    private String roomName;
    private Long roomID;
    private Long bookingID;
    private LocalDate dateOfBooking;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private String purpose;

    // Constructors, Getters, and Setters

    public BookingHistoryResponse() {
    }

    public BookingHistoryResponse(String roomName, Long roomID, Long bookingID, LocalDate dateOfBooking,
                                  LocalTime timeFrom, LocalTime timeTo, String purpose) {
        this.roomName = roomName;
        this.roomID = roomID;
        this.bookingID = bookingID;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
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

