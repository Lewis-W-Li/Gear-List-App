package persistence;

import model.Gear;
import model.GearList;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GearList gl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGearList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGearList.json");
        try {
            GearList gl = reader.read();
            assertEquals(0, gl.getNumberOfGearInList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGearList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGearList.json");
        try {
            GearList gl = reader.read();
            assertEquals(2, gl.getNumberOfGearInList());
            checkGear("Petzl Nomic", 1, 585, false, gl.getGearInList(0));
            checkGear("Petzl Ergonomic", 2, 635, true, gl.getGearInList(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}