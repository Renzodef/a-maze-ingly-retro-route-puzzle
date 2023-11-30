# Main Application Development

## Overview

The `Main` class is the entry point of the application, handling input parsing, maze solving, and output formatting.

## Input Parameters

- **Maze Map JSON**: The user is prompted to enter the JSON representation of the maze map. 
The input is terminated by typing 'END' on a new line.
- **Starting Room ID**: The user needs to enter the ID of the room where the path finding should start. 
It should be an integer or the provided error message will be displayed.
- **Items to Collect**: A comma-separated list of items that need to be collected in the maze (e.g. Knife,Potted Plant).

## Application Flow

1. **Parsing JSON**: The application reads lines from standard input
to construct the JSON string representing the maze map.
2. **Parsing Starting Room ID**: Reads the starting room ID and validates it as an integer.
3. **Collecting Items**: Reads the list of items to be collected, split by commas.
4. **Maze Solving**: Uses `MapParser` to parse the JSON into a `MazeMap` object
and `MazeSolver` to find a path to collect all items in the maze.
5. **Error Handling**: Catches and displays errors if any occur during parsing or solving.

## Output Format

- The output is formatted as a table displaying the path taken to collect all items.
- Columns include the room ID, room name, and the object collected in that room.
- If no path is found (`MazeSolver` returned an empty list),
a message is displayed indicating that all items can't be collected.

```plaintext
Output:

| ID  | Room        | Object collected |
|-----|-------------|------------------|
// Table content
```

- The `printPathTable` method handles the formatting of this output.

## Testing the Application

To test the application on HackerRank:
1. **Execute install.sh**: Execute the `install.sh` script in the `scripts` folder to set up the environment.
2. **Execute run.sh**: Execute the `run.sh` script to compile and run the application.
3. **Provide Input**: Enter the maze JSON (you can copy and paste it from the files provided in the `maps` folder), starting room ID, and items to collect as prompted.
4. **Review Output**: The output will be displayed in the console in the table format specified above.
