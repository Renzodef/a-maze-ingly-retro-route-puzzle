# Test-Driven Development Process

## MapParser Implementation

## Step 1: Initial Test Implementation
- **MapParserTest Creation**: Implemented in the `logic` package to assess JSON parsing functionality.
- **Test Cases**: Tests to parse JSON defined in `maps/example1.json` and `maps/example2.json`.
- **Test Structure**: Included various methods and assertions to validate the structure of the JSON.

## Step 2: Models and Class Implementation
- **Development of Models**: Created `MazeMap`, `Room`, and `RoomObject` models that use Lombok
to automatically generate getters, setters, constructors, and other methods.
- **MapParser Class**: Implemented using Jackson to map JSON data to model classes.

## Step 3: Expanding Test Coverage
- **Negative Testing**: Added cases for invalid JSON, missing fields, and incorrect data types.
- **Test Refinement**: Enhanced suite to cover a broad range of scenarios.

## MazeSolver Implementation

## Step 1: Initial Test Implementation
- **Purpose**: Implemented `MazeSolverTest` in the `logic` package to test the maze navigation logic.
- **Test Cases**: Tests for navigating mazes defined in `maps/example1.json` and `maps/example2.json` 
from various starting rooms.
- **Test Structure**: Included methods to validate the correct path,
ensuring all specified items are collected and the path is valid.

## Step 2: Models and Class Implementation
- **Development of Models**: Enhanced `MazeMap` class to include methods to retrieve a Room by ID and to get 
the neighbors of a given Room.
- **MazeSolver Class**: Developed to navigate the maze and collect specified items using the algorithm explained in
[Analysis](1-ANALYSIS.md) and the methods added in the `MazeMap` class.

## Step 3: Expanding Test Coverage
- **Negative Testing**: Cases to handle non-existent starting rooms and objects.
- **Test Refinement**: Enhanced suite to cover a broad range of scenarios.
