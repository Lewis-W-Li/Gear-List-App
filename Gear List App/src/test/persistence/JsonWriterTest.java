package persistence;

import model.Gear;
import model.GearList;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            GearList gl = new GearList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGearList() {
        try {
            GearList gl = new GearList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGearList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGearList.json");
            gl = reader.read();
            assertEquals(0, gl.getNumberOfGearInList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGearList() {
        try {
            GearList gl = new GearList();
            gl.addGear(new Gear("Petzl Nomic", 1, 585, false));
            gl.addGear(new Gear("Petzl Ergonomic", 2, 635, true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGearList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGearList.json");
            assertEquals(2, gl.getNumberOfGearInList());
            checkGear("Petzl Nomic", 1, 585, false, gl.getGearInList(0));
            checkGear("Petzl Ergonomic", 2, 635, true, gl.getGearInList(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
