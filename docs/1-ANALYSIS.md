# Analysis

## Problem Analysis

The process for solving the A-Maze-ingly Retro Route Puzzle challenge began with a thorough analysis of the problem. 
This initial phase involved understanding the key components of the puzzle:

- **Map Structure:** The maze is represented as a JSON object containing a list of rooms. 
Each room has an ID, a name, and connections to other rooms in the four cardinal directions (north, south, east, west). 
Additionally, rooms may contain objects that need to be collected.

- **Object Collection:** The challenge requires navigating through the maze to collect a specified list of objects. 
These objects are located in various rooms throughout the maze.

- **Starting Point:** The solution needs to begin from a specified starting room, and from there, 
find a route that collects all required objects in the most efficient way possible.

## Approach

To address these challenges, the following approach was planned:

1. **Parsing the JSON Map:**
   - **Implementation Goal:** Develop a parser that accurately reads and converts the JSON representation of the maze into a usable data structure.
   - **Model Creation:** Plan to create model classes:
      - `MazeMap`: Represents the overall map of the maze.
      - `Room`: Represents individual rooms within the maze.
      - `RoomObject`: Represents objects located within each room.
   - **Utilizing Jackson:** Use the Jackson library to facilitate the mapping of the JSON data to these model classes.

2. **Navigating the Maze:**
   - **Algorithm Development:** Develop an algorithm to navigate through the maze.
   - **Room Accessibility:** Ensure the algorithm accounts for all accessible rooms based on their connections.
   - **Item Collection:** Implement logic within the navigation algorithm to collect all specified items.


## Graph-Based Maze Navigation

Navigating the maze can be viewed as a graph traversal problem, where each room represents a node, 
and connections between rooms represent edges. 
I approached this challenge by implementing a graph traversal algorithm (Depth-First Search with Backtracking).
Here's an overview of the approach in simple terms and pseudocode:

### Pseudocode for Graph-Based Maze Navigation

```
Function findPath(map, startRoomId, itemsToCollect):
    itemsLeftToCollect = copy of itemsToCollect
    visited = empty set
    pathStack = empty stack
    completePath = empty list

    // Check if the starting room exists in the map
    If startRoomId is in map:
        startRoom = room in map identified by startRoomId
    Else:
        Raise an error "Starting room not found"

    Push startRoom onto pathStack

    // Traverse the maze while there are rooms to visit and items left to collect
    While pathStack is not empty and itemsLeftToCollect is not empty:
        current = top room of pathStack
        Add current to visited
        Add current to completePath

        // Collect items in the current room
        For each object in current room:
            If object is in itemsLeftToCollect:
                Remove object from itemsLeftToCollect

        // Check if all items have been collected and return the path if so
        If itemsLeftToCollect is empty:
            Return completePath

        // Explore adjacent unvisited rooms
        hasUnvisitedNeighbour = false
        For each neighbour of current in map:
            If neighbour is not in visited:
                Push neighbour onto pathStack
                hasUnvisitedNeighbour = true
                Break

        // Backtrack if there are no unvisited neighbours
        If not hasUnvisitedNeighbour:
            Pop from pathStack

    // Return the path if all items are collected, else return empty path
    Return completePath
```

### Description of the Algorithm
- **Initialization**: Sets up a list to track items to collect (not a set because there may be duplicates),
a set to track visited rooms, a stack to track the rooms and the path, and a list to track the complete path.
- **Start Room Check**: Validates the existence of the starting room.
- **Maze Traversal**: Uses a while loop to explore the maze, 
considering each room at the top of the stack as the current room.
- **Item Collection**: In each room, collects necessary items and removes them from the list of items to collect.
- **Path Completion**: Returns the complete path once all items are collected.
- **Exploration and Backtracking**: Iterates through neighbours of the current room, 
pushing the first unvisited one onto the stack for exploration. Backtracks when no unvisited neighbours are found.
- **Result**: Returns the complete path if all items are collected; 
otherwise, returns an empty path indicating no solution.
