package com.bits.csf213.roombooking.service;

import com.bits.csf213.roombooking.model.Room;
import com.bits.csf213.roombooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<Room> findRoomsByCapacity(int capacity) {
        return roomRepository.findByCapacityGreaterThanEqual(capacity);
    }

    @Override
    public boolean checkRoomExistsbyName(String roomName) {
        return roomRepository.existsByRoomName(roomName);
    }

    @Override
    // Method to check if a room with a given ID already exists
    public boolean checkRoomExistsbyID(Long roomId) {
        return roomRepository.existsByRoomId(roomId);
    }

    @Override
    // Method to check if a room with a given name already exists excluding the given room ID
    public boolean checkRoomWithSameNameExists(String roomName, Long roomId) {
        return roomRepository.existsByRoomNameAndRoomIdNot(roomName, roomId);
    }

}
