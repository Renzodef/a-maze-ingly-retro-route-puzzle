# A-Maze-ingly Retro Route Puzzle

### Please, be sure to read the instructions entirely. At the bottom, you will find requirements and troubleshooting.

You can solve this challenge in the Web IDE, a Local IDE (with  `git`), or a hybrid fashion. If you want to use your
Local IDE, be sure you are working in an environment similar to the one you will submit the test. We suggest you check
the versions of the languages supported by the environment and edit the  `scripts/install.sh` accordingly.

Moreover, the Local IDE approach can give you bonus points because it proves you can use  `git` effectively.

## Problem:

Write a program that will output a valid route to collect all specified items within a map. The map is a JSON
description of rooms with allowed paths and contained objects.

The project will take as input:

- A JSON representation of the map;
- The starting room;
- The list of objects to collect.

```
Room type allowed fields

  id: Integer
  name: String
  north: Integer //referring to a connected room
  south: Integer //referring to a connected room
  west: Integer //referring to a connected room
  east: Integer //referring to a connected room
  objects: List //of Objects
  
Object type allowed fields
  name: String //Object Name
```

## Example 1

Map (you can find this map in `maps/example1.json`)

 ```json
{
  "rooms": [
    {
      "id": 1,
      "name": "Hallway",
      "north": 2,
      "objects": []
    },
    {
      "id": 2,
      "name": "Dining Room",
      "south": 1,
      "west": 3,
      "east": 4,
      "objects": []
    },
    {
      "id": 3,
      "name": "Kitchen",
      "east": 2,
      "objects": [
        {
          "name": "Knife"
        }
      ]
    },
    {
      "id": 4,
      "name": "Sun Room",
      "west": 2,
      "objects": [
        {
          "name": "Potted Plant"
        }
      ]
    }
  ]
}
```

Input

```
Start Room ID = 2
Objects To Collect = Knife, Potted Plant
```

Output

| ID  | Room        | Object collected |
|-----|-------------|------------------|
| 2   | Dining Room | None             |
| 1   | Hallway     | None             |
| 2   | Dining Room | None             |
| 3   | Kitchen     | Knife            |
| 2   | Dining Room | None             |
| 4   | Sun Room    | Potted Plant     |

## Example 2

Map (you can find this map in `maps/example2.json`)

```json
{
  "rooms": [
    {
      "id": 1,
      "name": "Hallway",
      "north": 2,
      "east": 7,
      "objects": []
    },
    {
      "id": 2,
      "name": "Dining Room",
      "north": 5,
      "south": 1,
      "west": 3,
      "east": 4,
      "objects": []
    },
    {
      "id": 3,
      "name": "Kitchen",
      "east": 2,
      "objects": [
        {
          "name": "Knife"
        }
      ]
    },
    {
      "id": 4,
      "name": "Sun Room",
      "west": 2,
      "north": 6,
      "south": 7,
      "objects": []
    },
    {
      "id": 5,
      "name": "Bedroom",
      "south": 2,
      "east": 6,
      "objects": [
        {
          "name": "Pillow"
        }
      ]
    },
    {
      "id": 6,
      "name": "Bathroom",
      "west": 5,
      "south": 4,
      "objects": []
    },
    {
      "id": 7,
      "name": "Living room",
      "west": 1,
      "north": 4,
      "objects": [
        {
          "name": "Potted Plant"
        }
      ]
    }
  ]
}
```

Input

```
Start Room ID = 4
Objects To Collect = Knife, Potted Plant, Pillow
```

Output

| ID  | Room        | Object collected |
|-----|-------------|------------------|
| 4   | Sun Room    | None             |
| 6   | Bathroom    | None             |
| 4   | Sun Room    | None             |
| 7   | Living Room | Potted Plant     |
| 4   | Sun Room    | None             |
| 2   | Dining Room | None             |
| 5   | Bedroom     | Pillow           |
| 2   | Dining Room | None             |
| 1   | Hallway     | None             |
| 2   | Dining Room | None             |
| 3   | Kitchen     | Knife            |

## Requirements:

### Language

To solve the challenge, you can use a programming language of your choice.

The programming language directly supported by this environment are:

- Golang v1.20 (**preferred**);
- Python 3.10.6;

Moreover, we have provided some commands to help you in the  `scripts/install.sh` file to set up an environment for the
following languages:

- Java;
- Kotlin;
- Javascript/Typescript (NodeJS);
- Ruby;
- Rust;

If you want to use another language, you must document the choice and configure the environment editing
the  `scripts/install.sh` script accordingly.

To successfully run the  `run`,  `test`, and  `install` commands in the HackerRank GUI, you must edit the bash scripts
in the  `scripts` folder.

- `run`: this script must run the project (**mandatory**);
- `test`: this script must run some tests (**mandatory**);
- `install`: this script must build the project and its dependencies if needed;

### Tests & Documentation

**Tests are mandatory.** We evaluate how well you write tests and how well-covered the codebase is.

You can see that there is a folder called  `docs`. This folder should be used for documentation purposes only.

Here, we expect to see a list of commands useful to set up the environment and run your project. This is useful for
reviewing your project better.

We definitely give you a bonus if you provide a brief and clear technical description of the choice to solve the
project.

## Known Issues:

- Sometimes the HackerRank IDE can hang. Please refer
  to [this FAQ](https://candidatesupport.hackerrank.com/hc/en-us/articles/4402913899027-Front-end-Back-end-Full-stack-Assessments-FAQs)
  for help.
- If you want to copy/paste some code into the IDE terminal, you can use  `CTRL(CMD)+SHIFT+C/V`.
