package persistence;

import model.Gear;
import org.json.JSONObject;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGear
            (String name, int quantity, int weight, boolean neededNextTrip, Gear gear) {
        assertEquals(name, gear.getName());
        assertEquals(quantity, gear.getQuantity());
        assertEquals(weight, gear.getWeight());
        assertEquals(neededNextTrip, gear.getNeededNextTrip());
    }
}
