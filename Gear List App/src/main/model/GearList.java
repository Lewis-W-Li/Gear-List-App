package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of Gear
public class GearList implements Writable {

    private ArrayList<Gear> gearList;

    // Constructs a new ArrayList of Gear
    public GearList() {

        gearList = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: adds a Gear to the list
    public void addGear(Gear gear) {

        //event log
        EventLog.getInstance().logEvent(new Event("A piece of gear is added to the app"));


        gearList.add(gear);
    }

    // EFFECTS: returns the size of the gear list
    public int getNumberOfGearInList() {

        return gearList.size();
    }

    // EFFECTS: returns the gear with the index given
    public Gear getGearInList(int i) {

        return gearList.get(i);
    }

//    // EFFECTS: lists 1 gear in list with their name and weight quantity
//    public String getSingleGearText(int i) {
//        StringBuilder gearInfo = new StringBuilder();
//        gearInfo.append("Name: ").append(gearList.get(i).getName())
//                .append(" Quantity: ").append(gearList.get(i).getQuantity())
//                .append(" Weight: ")
//                .append(gearList.get(i).getWeight()).append(" Needed for next trip? ")
//                .append(gearList.get(i).getNeededNextTrip());
//
//        return gearInfo.toString();
//    }

    // EFFECTS: lists all the gear in list with their name and weight quantity
    public String getGearList() {

        //event log
        EventLog.getInstance().logEvent(new Event("Full Gear List is generated"));

        StringBuilder gearInfo = new StringBuilder();
        for (int i = 0; i < getNumberOfGearInList(); i++) {
            gearInfo.append("Name: ").append(gearList.get(i).getName())
                    .append(" Quantity: ").append(gearList.get(i).getQuantity())
                    .append(" Weight: ")
                    .append(gearList.get(i).getWeight()).append(" Needed for next trip? ")
                    .append(gearList.get(i).getNeededNextTrip()).append("\n");
        }
        return gearInfo.toString();
    }

    // EFFECTS: lists all the gear in list for next trip with their name and weight
    public String getGearListForNextTrip() {

        //event log
        EventLog.getInstance().logEvent(new Event("Gear List For Next Trip is generated"));

        StringBuilder gearInfo = new StringBuilder();
        for (int i = 0; i < getNumberOfGearInList(); i++) {
            if (gearList.get(i).getNeededNextTrip()) {
                gearInfo.append("Name: ").append(gearList.get(i).getName())
                        .append(" Quantity: ").append(gearList.get(i).getQuantity())
                        .append(" Weight: ")
                        .append(gearList.get(i).getWeight()).append(" Needed for next trip? ")
                        .append(gearList.get(i).getNeededNextTrip()).append("\n");
            }
        }
        return gearInfo.toString();
    }

    // EFFECTS: get the total weight of the gear in list for next trip
    public int getWeightForNextTrip() {
        int weight = 0;
        for (int i = 0; i < getNumberOfGearInList(); i++) {
            if (gearList.get(i).getNeededNextTrip()) {
                weight += gearList.get(i).subtotalWeight();
            }
        }
        return weight;
    }

    // EFFECTS: returns gear in gear list as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("gearList", gearListToJson());
        return json;
    }


    // EFFECTS: returns gear in gear list as a JSON array
    private JSONArray gearListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Gear g : gearList) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }

}
