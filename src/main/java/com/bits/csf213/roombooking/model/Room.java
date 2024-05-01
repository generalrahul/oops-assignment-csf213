package com.bits.csf213.roombooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false, unique = true)
    private String roomName;

    @Column(nullable = false)
    private Long capacity;

    // Constructors
    public Room() {}

    // Getter and setter for roomId
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    // Getter and setter for roomName
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    // Getter and setter for capacity
    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

}
