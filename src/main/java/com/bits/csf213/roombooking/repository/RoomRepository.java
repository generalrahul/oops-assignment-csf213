package com.bits.csf213.roombooking.repository;

import com.bits.csf213.roombooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomName(String roomName);
    List<Room> findByCapacityGreaterThanEqual(int capacity);
    boolean existsByRoomName(String roomName);
    // Method to check if a room with a given ID already exists
    boolean existsByRoomId(Long roomId);
    // Method to check if a room with a given name already exists excluding the given room ID
    boolean existsByRoomNameAndRoomIdNot(String roomName, Long roomId);
}
