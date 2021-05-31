package modelTests;

import exceptions.FurnitureDoesNotFit;
import exceptions.FurnitureNotFound;
import model.Furniture;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    public static final int ROOM_WIDTH = 200;
    public static final int ROOM_HEIGHT = 200;
    public static final int ROOM_LENGTH = 200;
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room();
    }

    @Test
    public void testGetFurniture() {
        Furniture f = new Furniture("Sofa", 100, 50, 50);
        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        assertEquals(room.getFurniture().get("Sofa"), f);
    }

    @Test
    public void testAddFurniture() throws FurnitureDoesNotFit {
        Furniture f = new Furniture("Sofa", 100, 50, 50);
        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        assertEquals(1, room.countFurniture());
        assertTrue(room.currentContains(f));
        assertTrue(room.fitsRoom(100,50,50));
    }

    @Test
    public void testAddOneFurniture() throws FurnitureDoesNotFit {
        Furniture table = new Furniture("Table", 30, 30, 30);

        try {
            room.addFurniture(table);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        assertTrue(room.fitsRoom(30,30,30));
    }

    @Test
    public void testAddMoreThanOneFurniture() throws FurnitureDoesNotFit {
        Furniture f1 = new Furniture("Bed", 100, 60, 90);
        Furniture f2 = new Furniture("Side Table", 20, 20, 20);

        try {
            room.addFurniture(f1);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        try {
            room.addFurniture(f2);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        assertTrue(room.fitsRoom(60,100,90));
        assertTrue(room.fitsRoom(20,20,20));
    }

    @Test
    public void testFurnitureFitsAdd() throws FurnitureDoesNotFit {
        Furniture f = new Furniture("Bed", 60, 30, 50);

        room.fitsRoom( 60, 30, 50);
        room.addFurniture(f);
    }

    @Test
    public void testFurnitureFits() {
        Furniture f = new Furniture("Bed", 60, 30, 50);
        f.setFw(260);
        f.setFh(60);
        f.setFl(100);

        assertTrue(room.containsFurnitureHeight(60, ROOM_HEIGHT));
        assertFalse(room.containsFurnitureWidth(260, ROOM_WIDTH));
        assertTrue(room.containsFurnitureLength(100, ROOM_LENGTH));
    }

    @Test
    public void testFurnitureDoesNotFit() {
        assertFalse(room.containsFurnitureHeight(250, 200));
    }

    @Test
    public void testIfFurnitureFits() {
        assertTrue(room.containsFurnitureLength(10,20));
        assertTrue(room.containsFurnitureWidth(10,20));
        assertTrue(room.containsFurnitureHeight(10, 20));
    }

    @Test
    public void testAddFurnitureDoesNotFit() throws FurnitureDoesNotFit {
        Furniture f = new Furniture("Bed", 250, 30, 50);

        try {
            room.addFurniture(f);
            fail("Furniture is too wide");
        } catch (FurnitureDoesNotFit dnf) {
            //
        }
        assertFalse(room.containsFurnitureWidth(250,200));
    }

    @Test
    public void testFurnitureTooLong() throws FurnitureDoesNotFit {
        Furniture f = new Furniture("Desk", 20, 40, 250);

        try {
            room.addFurniture(f);
            fail("Furniture is too long");
        } catch (FurnitureDoesNotFit dnf) {
            //
        }
        assertTrue(room.containsFurnitureHeight(40, 200));
        assertTrue(room.containsFurnitureWidth(20, 200));
        assertFalse(room.containsFurnitureLength(250, 200));
    }

    @Test
    public void testFurnitureTooHigh() throws FurnitureDoesNotFit {
        int fw = 20;
        int fh = 600;
        int fl = 50;

        Furniture f = new Furniture("Desk", fw, fl, fh);

        try {
            room.addFurniture(f);
            fail();
        } catch (FurnitureDoesNotFit dnf) {
            //
        }
        assertTrue(room.containsFurnitureWidth(fw, ROOM_WIDTH));
        assertFalse(room.containsFurnitureHeight(fh, ROOM_HEIGHT));
    }

    @Test
    public void testFurnitureTooWide() throws FurnitureDoesNotFit {
        int fw = 600;
        int fh = 60;
        int fl = 50;

        Furniture f = new Furniture("Desk", fw, fl, fh);

        try {
            room.addFurniture(f);
            fail();
        } catch (FurnitureDoesNotFit dnf) {
            //
        }
        assertFalse(room.containsFurnitureWidth(fw, ROOM_WIDTH));
        assertTrue(room.containsFurnitureHeight(fh, ROOM_HEIGHT));
        assertTrue(room.containsFurnitureLength(fl, ROOM_LENGTH));
    }

    @Test
    public void testFurnitureNotHigh() throws FurnitureDoesNotFit {
        int fw = 20;
        int fh = 20;
        int fl = 50;

        Furniture f = new Furniture("Desk", fw, fl, fh);

        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail("Furniture is too high");
        }
        assertTrue(room.containsFurnitureWidth(fw, ROOM_WIDTH));
        assertTrue(room.containsFurnitureHeight(fh, ROOM_HEIGHT));
        assertTrue(room.fitsRoom(fh, fw, fl));
    }

    @Test
    public void testRemoveFurniture() {
        Furniture f = new Furniture("Bed", 60, 30, 50);
        f.setFw(60);
        f.setFh(30);
        f.setFl(50);

        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        try {
            room.removeFurniture("Bed");
        } catch (FurnitureNotFound e) {
            fail("Furniture was not found when it should be in room");
        }
        assertFalse(room.currentContains(f));
    }

    @Test
    public void testRemoveOneFurniture() {
        Furniture f = new Furniture("Bed", 30, 20, 30);

        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        try {
            room.removeFurniture("Bed");
        } catch (FurnitureNotFound e) {
            fail("Furniture was not found when it should be in room");
        }
        assertFalse(room.currentContains(f));
    }

    @Test
    public void testRemoveSingleFurniture() {
        Furniture f1 = new Furniture("Sofa", 20, 50, 60);
        Furniture f2 = new Furniture("Table", 30, 30, 30);

        try {
            room.addFurniture(f1);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        try {
            room.addFurniture(f2);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        try {
            room.removeFurniture("Sofa");
        } catch (FurnitureNotFound e) {
            fail("Furniture was not found when it should be in room");
        }
        assertFalse(room.currentContains(f1));
        assertTrue(room.currentContains(f2));
    }

    @Test
    public void testRemoveMultipleFurnitures() {
        Furniture f1 = new Furniture("Sofa", 20, 50, 60);
        Furniture f2 = new Furniture("Table", 30, 30, 30);

        try {
            room.addFurniture(f1);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        try {
            room.addFurniture(f2);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }

        try {
            room.removeFurniture("Sofa");
            room.removeFurniture("Table");
        } catch (FurnitureNotFound e) {
            fail("Furniture was not found when it should be in room");
        }
        assertFalse(room.currentContains(f1));
        assertFalse(room.currentContains(f2));
    }

    @Test
    public void testRemoveFurnitureEmptyRoom() {

        try {
            room.removeFurniture("Sofa");
            fail("FurnitureNotFound exception should have been thrown");
        } catch (FurnitureNotFound e) {
            //
        }
    }

    @Test
    public void testPrintFurniture() {
        StringBuilder sb = new StringBuilder();
        Furniture f = new Furniture("Chair", 20, 60, 20);
        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        assertEquals(room.printFurniture(f), sb.toString().concat("\nName: ").concat(f.getName())
                .concat("\n   Width:    ").concat(String.valueOf(f.getWidth()))
                .concat("\n   Height:   ").concat(String.valueOf(f.getHeight()))
                .concat("\n   Length:  ").concat(String.valueOf(f.getLength())));
    }

    @Test
    public void testPrintCurrentFurnitureFull() {
        StringBuilder sb = new StringBuilder();
        Furniture f = new Furniture("Chair", 20, 60, 20);
        try {
            room.addFurniture(f);
        } catch (FurnitureDoesNotFit dnf) {
            fail();
        }
        assertEquals(room.printCurrentFurniture(), sb.toString().concat("\nName: ").concat(f.getName())
                .concat("\n   Width:    ").concat(String.valueOf(f.getWidth()))
                .concat("\n   Height:   ").concat(String.valueOf(f.getHeight()))
                .concat("\n   Length:  ").concat(String.valueOf(f.getLength())));
    }

    @Test
    public void testPrintCurrentFurnitureEmpty() {
        StringBuilder sb = new StringBuilder();
        Furniture f = new Furniture("Chair", 20, 60, 20);
        assertEquals(room.printCurrentFurniture(), sb.toString().concat("\nNo furniture in room."));
    }
}
