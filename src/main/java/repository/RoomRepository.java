package repository;

import model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomName(String roomName);
    List<Room> findByCapacityGreaterThanEqual(int capacity);
}
