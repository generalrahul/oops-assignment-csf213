package service;

import model.Room;
import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Room room);
    Room findRoomById(Long roomId);
    List<Room> findAllRooms();
    void deleteRoom(Long roomId);
    List<Room> findRoomsByCapacity(int capacity);
}
