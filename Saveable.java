package model;

import org.json.JSONObject;

import java.io.IOException;

public interface Saveable {
    void save(Room room) throws IOException;
}
