package com.bits.csf213.roombooking.service;

import com.bits.csf213.roombooking.model.Room;
import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Room room);
    Room findRoomById(Long roomId);
    List<Room> findAllRooms();
    void deleteRoom(Long roomId);
    List<Room> findRoomsByCapacity(int capacity);
    boolean checkRoomExistsbyName(String roomName);
    boolean checkRoomExistsbyID(Long roomId);
    boolean checkRoomWithSameNameExists(String roomName, Long roomId);
}
