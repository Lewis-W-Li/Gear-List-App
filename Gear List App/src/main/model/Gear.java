package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a piece of climbing gear
public class Gear implements Writable {

    private String name;
    private int weight;
    private int subtotalWeight;
    private int quantity;
    private boolean neededNextTrip;


    // Constructs a new piece of gear with the gear info as arguments
    public Gear(String name, int quantity, int weight, boolean neededNextTrip) {
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.neededNextTrip = neededNextTrip;
        //this.subtotalWeight = subtotalWeight;
    }

    public String getName() {

        return name;
    }

    public int getQuantity() {

        return quantity;
    }

    public int getWeight() {

        return weight;
    }

    public boolean getNeededNextTrip() {

        return neededNextTrip;
    }


    // EFFECTS: get the subtotal weight by quantity * weight of a single piece
    public int subtotalWeight() {

        return subtotalWeight = quantity * weight;
    }


    // EFFECTS: returns a piece of gear as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("weight", weight);
        json.put("neededNextTrip", neededNextTrip);
        //json.put("subtotalWeight", subtotalWeight);
        return json;
    }

}
