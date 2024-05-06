package persistence;


import model.Gear;
import model.GearList;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads gear list from JSON data stored in file
// The data persistence was inspired by the JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads gear list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GearList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGearList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GearList from JSON object and returns it
    private GearList parseGearList(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        GearList gl = new GearList();
        addGears(gl, jsonObject);
        return gl;
    }

    // MODIFIES: gl
    // EFFECTS: parses pieces of gear from JSON object and adds them to gear list
    private void addGears(GearList gl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gearList");
        for (Object json : jsonArray) {
            JSONObject nextGear = (JSONObject) json;
            addGear(gl, nextGear);
        }
    }

    // MODIFIES: gl
    // EFFECTS: parses gear from JSON object and adds it to gear list
    private void addGear(GearList gl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = jsonObject.getInt("quantity");
        int weight = jsonObject.getInt("weight");
        boolean neededNextTrip = jsonObject.getBoolean("neededNextTrip");
        //int subtotalWeight = jsonObject.getInt("subtotalWeight");
        Gear gear = new Gear(name, quantity, weight, neededNextTrip);
        gl.addGear(gear);
    }
}
