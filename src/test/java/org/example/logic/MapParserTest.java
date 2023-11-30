package org.example.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.example.model.MazeMap;
import org.example.model.Room;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.example.util.Utils.readJsonFromFile;
import static org.junit.jupiter.api.Assertions.*;

public class MapParserTest {

    private final MapParser parser = new MapParser();

    @Test
    public void shouldParseExample1MapCorrectly() throws IOException {
        String json = readJsonFromFile("maps/example1.json");
        MazeMap map = parser.parse(json);

        assertNull(map.findRoomById(0), "Room with id 0 should not exist");
        assertNull(map.findRoomById(5), "Room with id 5 should not exist");

        performGeneralMapAssertions(map, 4);
        performSpecificRoomAssertions(map, 1, "Hallway", 2, null, null, null, 0, Collections.emptyList());
        performSpecificRoomAssertions(map, 2, "Dining Room", null, 1, 4, 3, 0, Collections.emptyList());
        performSpecificRoomAssertions(map, 3, "Kitchen", null, null, 2, null, 1, List.of("Knife"));
        performSpecificRoomAssertions(map, 4, "Sun Room", null, null, null, 2, 1, List.of("Potted Plant"));
    }

    @Test
    public void shouldParseExample2MapCorrectly() throws IOException {
        String json = readJsonFromFile("maps/example2.json");
        MazeMap map = parser.parse(json);

        performGeneralMapAssertions(map, 7);

        assertNull(map.findRoomById(0), "Room with id 0 should not exist");
        assertNull(map.findRoomById(8), "Room with id 8 should not exist");

        performSpecificRoomAssertions(map, 1, "Hallway", 2, null, 7, null, 0, Collections.emptyList());
        performSpecificRoomAssertions(map, 2, "Dining Room", 5, 1, 4, 3, 0, Collections.emptyList());
        performSpecificRoomAssertions(map, 3, "Kitchen", null, null, 2, null, 1, List.of("Knife"));
        performSpecificRoomAssertions(map, 4, "Sun Room", 6, 7, null, 2, 0, Collections.emptyList());
        performSpecificRoomAssertions(map, 5, "Bedroom", null, 2, 6, null, 1, List.of("Pillow"));
        performSpecificRoomAssertions(map, 6, "Bathroom", null, 4, null, 5, 0, Collections.emptyList());
        performSpecificRoomAssertions(map, 7, "Living room", 4, null, null, 1, 1, List.of("Potted Plant"));
    }

    @Test
    public void shouldFailWithInvalidJsonFormat() {
        String json = "{invalid json}";
        assertThrows(JsonParseException.class, () -> parser.parse(json));
    }

    @Test
    public void shouldFailWithMissingFields() {
        String json = "{\"id\": 1, \"name\": \"Hallway\"}";
        assertThrows(UnrecognizedPropertyException.class, () -> parser.parse(json));
    }

    @Test
    public void shouldFailWithIncorrectDataTypes() {
        String json = "{\"rooms\": [{\"id\": \"one\", \"name\": \"Hallway\", \"north\": 2}]}";
        assertThrows(InvalidFormatException.class, () -> parser.parse(json));
    }

    private void performGeneralMapAssertions(MazeMap map, int expectedRoomCount) {
        assertNotNull(map, "Map should not be null");
        assertNotNull(map.getRooms(), "Rooms list should not be null");
        assertEquals(expectedRoomCount, map.getRooms().size(), "Map should contain the expected number of rooms");
    }

    private void performSpecificRoomAssertions(MazeMap map, int roomId, String name,
                                               Integer north, Integer south,
                                               Integer east, Integer west,
                                               int expectedObjectCount, List<String> expectedObjectNames) {
        Room room = map.findRoomById(roomId);
        assertNotNull(room, "Room with id " + roomId + " should exist");

        assertEquals(name, room.getName(), "Room name should match");
        assertEquals(north, room.getNorth(), "North connection should match for Room " + roomId);
        assertEquals(south, room.getSouth(), "South connection should match for Room " + roomId);
        assertEquals(east, room.getEast(), "East connection should match for Room " + roomId);
        assertEquals(west, room.getWest(), "West connection should match for Room " + roomId);

        assertEquals(expectedObjectCount, room.getObjects().size(), "Number of objects should match in Room " + roomId);
        if (!room.getObjects().isEmpty()) {
            assertEquals(expectedObjectCount, expectedObjectNames.size(), "Expected object names count should match in Room " + roomId);
            performSpecificObjectAssertions(room, expectedObjectNames);
        }
    }

    private void performSpecificObjectAssertions(Room room, List<String> objectNames) {
        for (String name : objectNames) {
            assertTrue(room.getObjects().stream().anyMatch(o -> o.getName().equals(name)),
                    "Object '" + name + "' should be present in Room with id " + room.getId());
        }
    }
}
