package modelTests;

import model.Furniture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FurnitureTest {
    private Furniture f1;
    private Furniture f2;

    public static final int FURNITURE_WIDTH = 20;
    public static final int FURNITURE_HEIGHT = 30;
    public static final int FURNITURE_LENGTH = 100;

    @BeforeEach
    public void setUp() {
        f1 = new Furniture("Double Bed", 20, 30, 100);
        f1.setName("Double Bed");
        f1.setFw(20);
        f1.setFh(30);
        f1.setFl(100);

        f2 = new Furniture("Double Bed", 20, 30, 100);
        f2.setName("Double Bed");
        f2.setFl(100);
        f2.setFw(20);
        f2.setFh(30);
    }

    @Test
    public void testConstructor() {
        assertEquals("Double Bed", f1.getName());
        assertEquals(FURNITURE_WIDTH, f1.getWidth());
        assertEquals(FURNITURE_HEIGHT, f1.getHeight());
        assertEquals(FURNITURE_LENGTH, f1.getLength());
        assertTrue(f1.hashCode()==f2.hashCode());
    }

    @Test
    public void testNullObject() {
        Object o = null;
        assertFalse(f1.equals(o==null));
        assertFalse(f1.getClass() != f2.getClass());
    }

    @Test
    public void testDifferentClassEquals() {
        assertFalse(f1.getClass() != f2.getClass());
    }

    @Test
    public void testDifferentClassObject() {
        Object o = "bed";
        assertTrue(f1.getClass() != o.getClass());
    }

}