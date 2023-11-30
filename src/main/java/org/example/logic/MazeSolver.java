package org.example.logic;

import org.example.model.MazeMap;
import org.example.model.Room;
import org.example.model.RoomObject;

import java.util.*;

public class MazeSolver {

    public List<Room> findPath(MazeMap map, Integer startRoomId, List<String> itemsToCollect) {
        List<String> itemsLeftToCollect = new ArrayList<>(itemsToCollect);
        Set<Room> visited = new HashSet<>();
        Deque<Room> pathStack = new ArrayDeque<>();
        List<Room> completePath = new ArrayList<>();

        Room startRoom;
        if (map.findRoomById(startRoomId) != null) {
            startRoom = map.findRoomById(startRoomId);
        } else {
            throw new IllegalArgumentException("Starting room not found");
        }

        pathStack.push(startRoom);

        while (!pathStack.isEmpty() && !itemsLeftToCollect.isEmpty()) {
            Room current = pathStack.peek();
            visited.add(current);
            completePath.add(current);

            List<RoomObject> objectsInRoom = current.getObjects();
            for (RoomObject object : objectsInRoom) {
                itemsLeftToCollect.remove(object.getName());
            }

            if (itemsLeftToCollect.isEmpty()) {
                return completePath;
            }

            List<Room> neighbours = map.getAdjacentRooms(current);
            boolean hasUnvisitedNeighbour = false;
            for (Room neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    pathStack.push(neighbour);
                    hasUnvisitedNeighbour = true;
                    break;
                }
            }

            if (!hasUnvisitedNeighbour) {
                if (pathStack.size() > 1) {
                    pathStack.pop();
                } else {
                    break;
                }
            }
        }

        return new ArrayList<>();
    }

}

