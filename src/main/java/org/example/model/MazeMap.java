package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MazeMap {

    private List<Room> rooms;

    public Room findRoomById(Integer roomId){
        return rooms.stream()
                .filter(room -> room.getId().equals(roomId))
                .findFirst()
                .orElse(null);
    }

    public List<Room> getAdjacentRooms(Room room){
        List<Room> adjacentRooms = new ArrayList<>();
        if (room.getNorth() != null){
            adjacentRooms.add(this.findRoomById(room.getNorth()));
        }
        if (room.getSouth() != null){
            adjacentRooms.add(this.findRoomById(room.getSouth()));
        }
        if (room.getWest() != null){
            adjacentRooms.add(this.findRoomById(room.getWest()));
        }
        if (room.getEast() != null){
            adjacentRooms.add(this.findRoomById(room.getEast()));
        }
        return adjacentRooms;
    }
}
