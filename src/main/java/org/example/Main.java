package org.example;

import org.example.logic.MapParser;
import org.example.logic.MazeSolver;
import org.example.model.MazeMap;
import org.example.model.Room;
import org.example.model.RoomObject;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Paste the maze map JSON (type 'END' on a new line when done):");
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            jsonBuilder.append(line);
        }
        String json = jsonBuilder.toString();

        int startRoomId;
        System.out.println("Enter the starting room ID:");
        try {
            startRoomId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for room ID. Please enter a valid number.");
            return;
        }

        System.out.println("Enter the items to collect (comma-separated):");
        List<String> itemsToCollect = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));

        try {
            MazeMap map = new MapParser().parse(json);
            MazeSolver solver = new MazeSolver();
            List<Room> path = solver.findPath(map, startRoomId, itemsToCollect);

            if (path.isEmpty()) {
                System.out.println("There isn't a path to collect all the items.");
            } else {
                printPathTable(path, itemsToCollect);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void printPathTable(List<Room> path, List<String> itemsToCollect) {
        System.out.println();
        System.out.println("Output:");
        System.out.println();
        System.out.println("| ID  | Room        | Object collected |");
        System.out.println("|-----|-------------|------------------|");

        Set<String> itemsLeft = new HashSet<>(itemsToCollect);
        for (Room room : path) {
            String objectCollected = room.getObjects().stream()
                    .map(RoomObject::getName)
                    .filter(itemsLeft::remove)
                    .collect(Collectors.joining(", "));

            objectCollected = objectCollected.isEmpty() ? "None" : objectCollected;
            System.out.printf("| %-3d | %-11s | %-16s |%n", room.getId(), room.getName(), objectCollected);
        }
        System.out.println();
    }
}
