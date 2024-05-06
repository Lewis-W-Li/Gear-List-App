package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GearListTest {

    private Gear gear0;
    private Gear gear1;
    private Gear gear2;
    private GearList gearList;

    @BeforeEach
    public void runBefore() {

        gear0 = new Gear("Petzl Nomic", 1, 585, false);
        gear1 = new Gear("Petzl Ergonomic", 2, 635, true);
        gear2 = new Gear("Petzl Dart", 3, 870, true);
        gearList = new GearList();
    }

    @Test
    void testAddGear() {
        gearList.addGear(gear0);
        assertEquals(1, gearList.getNumberOfGearInList());
        gearList.addGear(gear1);
        assertEquals(2, gearList.getNumberOfGearInList());
    }

    @Test
    void getNumberOfGearInList() {
    }

    @Test
    void testGetGearInList() {
        gearList.addGear(gear0);
        gearList.addGear(gear1);
        assertEquals(gear0,
                gearList.getGearInList(0));
        assertEquals(gear1,
                gearList.getGearInList(1));
    }

    @Test
    void testGetGearList() {
        gearList.addGear(gear0);
        assertEquals("Name: Petzl Nomic Quantity: 1 Weight: 585 Needed for next trip? false" + "\n",
                gearList.getGearList());
        gearList.addGear(gear1);
        assertEquals("Name: Petzl Nomic Quantity: 1 Weight: 585 Needed for next trip? false" + "\n" +
                        "Name: Petzl Ergonomic Quantity: 2 Weight: 635 Needed for next trip? true" + "\n",
                gearList.getGearList());
    }

    @Test
    void testGetGearListForNextTrip() {
        gearList.addGear(gear0);
        assertEquals("",
                gearList.getGearListForNextTrip());
        gearList.addGear(gear1);
        assertEquals("Name: Petzl Ergonomic Quantity: 2 Weight: 635 Needed for next trip? true" + "\n",
                gearList.getGearListForNextTrip());
    }

    @Test
    void testGetWeightForNextTrip() {
        gearList.addGear(gear0);
        assertEquals(0,
                gearList.getWeightForNextTrip());
        gearList.addGear(gear1);
        assertEquals(635 * 2,
                gearList.getWeightForNextTrip());
        gearList.addGear(gear2);
        assertEquals(635 * 2 + 870 * 3,
                gearList.getWeightForNextTrip());
    }
}