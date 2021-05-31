package model;

import java.io.IOException;

public interface Loadable {
    void load(Room room) throws IOException;
}
