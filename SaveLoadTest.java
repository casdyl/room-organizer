package modelTests;

import exceptions.FurnitureDoesNotFit;
import model.Furniture;
import model.Loadable;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SaveLoadTest {
    private Room room;
    private Room loadedRoom;

    @BeforeEach
    void runBefore() {
        room = new Room();
        loadedRoom = new Room();
    }

    @Test
    void testSaveAndLoad() throws IOException {
        Furniture f1 = new Furniture("Bed", 40, 40, 40);
        f1.setFl(40);
        f1.setFh(40);
        f1.setFw(40);
        Furniture f2 = new Furniture("Desk", 60, 20, 30);
        f2.setFw(60);
        f2.setFh(20);
        f2.setFl(30);
        try {
            room.addFurniture(f1);
            room.addFurniture(f2);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        room.save(room);
        loadedRoom.load(loadedRoom);
        for (int i = 0; i <= room.getCurrentFurniture().size(); i++) {
            Furniture furniture = room.getCurrentFurniture().get(i);
            Furniture loadFurniture = loadedRoom.getCurrentFurniture().get(i);
            assertEquals(furniture, loadFurniture);
        }
    }

    @Test
    void testLoad() throws IOException {
        Loadable l = new Room();
        l.load(room);
    }
}

