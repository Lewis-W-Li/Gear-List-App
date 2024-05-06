package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GearTest {

    private Gear gear0;
    private Gear gear1;

    @BeforeEach
    public void runBefore() {

        gear0 = new Gear("Petzl Nomic", 1,585, false);
        gear1 = new Gear("Petzl Ergonomic", 2,635, true);
    }

    @Test
    void name() {

    }

    @Test
    void testGetName() {
        assertEquals("Petzl Nomic", gear0.getName());
        assertEquals("Petzl Ergonomic", gear1.getName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(1, gear0.getQuantity());
        assertEquals(2, gear1.getQuantity());
    }

    @Test
    void testGetWeight() {
        assertEquals(585, gear0.getWeight());
        assertEquals(635, gear1.getWeight());
    }

    @Test
    void testGetNeededNextTrip() {
        assertEquals(false, gear0.getNeededNextTrip());
        assertEquals(true, gear1.getNeededNextTrip());
    }

    @Test
    void testSubtotalWeight() {
        assertEquals(585, gear0.subtotalWeight());
        assertEquals(635*2, gear1.subtotalWeight());
    }
}
