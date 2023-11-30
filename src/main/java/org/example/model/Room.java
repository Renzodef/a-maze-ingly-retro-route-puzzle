package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Room {

    private Integer id;

    private String name;

    private Integer north;

    private Integer south;

    private Integer west;

    private Integer east;

    private List<RoomObject> objects;
}

