
package com.bits.csf213.roombooking.controller;

import com.bits.csf213.roombooking.model.Room;
import com.bits.csf213.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<String> addRoom(@RequestBody Room room) {
        if (room.getCapacity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid capacity");
        }

        boolean roomExists = roomService.checkRoomExistsbyName(room.getRoomName());
        if (roomExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room already exists");
        }

        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body("Room created successfully");
    }

    @PatchMapping
    public ResponseEntity<String> editRoom(@RequestBody Room room) {
        if (room.getCapacity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid capacity");
        }

        boolean roomExists = roomService.checkRoomExistsbyID(room.getRoomId());
        if (!roomExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room does not exist");
        }

        boolean roomWithSameNameExists = roomService.checkRoomWithSameNameExists(room.getRoomName(), room.getRoomId());
        if (roomWithSameNameExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room with given name already exists");
        }

        Room updatedRoom = roomService.updateRoom(room);
        return ResponseEntity.ok("Room edited successfully");
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(@RequestParam(required = false) Integer capacity) {
        List<Room> rooms;
        if (capacity != null) {
            // If capacity parameter is provided, fetch rooms with specified capacity
            rooms = roomService.findRoomsByCapacity(capacity);
        } else {
            // Otherwise, fetch all rooms
            rooms = roomService.findAllRooms();
        }
        return ResponseEntity.ok(rooms);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRoomById(@RequestParam Long roomID) {
        boolean roomExists = roomService.checkRoomExistsbyID(roomID);
        if (!roomExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room does not exist");
        }

        roomService.deleteRoom(roomID);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
