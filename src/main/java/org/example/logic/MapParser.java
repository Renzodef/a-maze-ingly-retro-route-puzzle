package org.example.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.MazeMap;
import java.io.IOException;

public class MapParser {

    public MazeMap parse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, MazeMap.class);
    }
}

