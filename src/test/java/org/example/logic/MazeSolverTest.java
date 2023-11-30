package org.example.logic;

import org.example.model.MazeMap;
import org.example.model.Room;
import org.example.model.RoomObject;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.util.Utils.readJsonFromFile;
import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {

    private final MapParser parser = new MapParser();
    private final MazeSolver solver = new MazeSolver();

    @Test
    public void shouldFindPathForExample1StartingFromRoom1() throws IOException {
        performTest("maps/example1.json", 1, Arrays.asList("Knife", "Potted Plant"));
    }

    @Test
    public void shouldFindPathForExample1StartingFromRoom2() throws IOException {
        performTest("maps/example1.json", 2, Arrays.asList("Knife", "Potted Plant"));
    }

    @Test
    public void shouldFindPathForExample1StartingFromRoom3() throws IOException {
        performTest("maps/example1.json", 3, Arrays.asList("Knife", "Potted Plant"));
    }

    @Test
    public void shouldFindPathForExample1StartingFromRoom4() throws IOException {
        performTest("maps/example1.json", 4, Arrays.asList("Knife", "Potted Plant"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom1() throws IOException {
        performTest("maps/example2.json", 1, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom2() throws IOException {
        performTest("maps/example2.json", 2, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom3() throws IOException {
        performTest("maps/example2.json", 3, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom4() throws IOException {
        performTest("maps/example2.json", 4, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom5() throws IOException {
        performTest("maps/example2.json", 5, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom6() throws IOException {
        performTest("maps/example2.json", 6, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldFindPathForExample2StartingFromRoom7() throws IOException {
        performTest("maps/example2.json", 7, Arrays.asList("Knife", "Potted Plant", "Pillow"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNonExistentStartingRoomInExample1() throws IOException {
        String json = readJsonFromFile("maps/example1.json");
        MazeMap map = parser.parse(json);

        List<String> itemsToCollect = Arrays.asList("Knife", "Potted Plant");
        int nonExistentStartRoomId = 0;

        assertThrows(IllegalArgumentException.class, () -> solver.findPath(map, nonExistentStartRoomId, itemsToCollect));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNonExistentStartingRoomInExample2() throws IOException {
        String json = readJsonFromFile("maps/example2.json");
        MazeMap map = parser.parse(json);

        List<String> itemsToCollect = Arrays.asList("Knife", "Potted Plant");
        int nonExistentStartRoomId = 0;

        assertThrows(IllegalArgumentException.class, () -> solver.findPath(map, nonExistentStartRoomId, itemsToCollect));
    }

    @Test
    public void shouldFindEmptyPathForNonExistentObjectsInExample1() throws IOException {
        String json = readJsonFromFile("maps/example1.json");
        MazeMap map = parser.parse(json);

        List<String> itemsToCollect = Arrays.asList("Knife", "Pillow");
        int startRoomId = 1;

        List<Room> path = solver.findPath(map, startRoomId, itemsToCollect);

        assertTrue(path.isEmpty(), "Path should be empty for non-existent objects");
    }

    @Test
    public void shouldFindEmptyPathForNonExistentObjectsInExample2() throws IOException {
        String json = readJsonFromFile("maps/example2.json");
        MazeMap map = parser.parse(json);

        List<String> itemsToCollect = Arrays.asList("Knife", "Sofa");
        int startRoomId = 1;

        List<Room> path = solver.findPath(map, startRoomId, itemsToCollect);

        assertTrue(path.isEmpty(), "Path should be empty for non-existent objects");
    }

    private void performTest(String jsonFile, int startRoomId, List<String> itemsToCollect) throws IOException {
        String json = readJsonFromFile(jsonFile);
        MazeMap map = parser.parse(json);
        List<Room> path = solver.findPath(map, startRoomId, itemsToCollect);

        assertNotNull(path, "Path should not be null");
        assertTrue(pathContainsAllItems(path, itemsToCollect), "Path should contain all items");
        assertTrue(isPathValid(path, map), "Path should be valid");
    }

    private boolean pathContainsAllItems(List<Room> path, List<String> items) {
        List<String> itemsToFind = new ArrayList<>(items);
        for (Room room : path) {
            for (RoomObject object : room.getObjects()) {
                itemsToFind.remove(object.getName());
            }
        }
        return itemsToFind.isEmpty();
    }

    private boolean isPathValid(List<Room> path, MazeMap map) {
        for (int i = 0; i < path.size() - 1; i++) {
            Room currentRoom = path.get(i);
            Room nextRoom = path.get(i + 1);
            if (!isRoomConnected(currentRoom, nextRoom, map)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRoomConnected(Room current, Room next, MazeMap map) {
        List<Room> adjacentRooms = map.getAdjacentRooms(current);
        return adjacentRooms.contains(next);
    }
}
