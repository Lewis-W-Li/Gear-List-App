package ui;

import model.Gear;
import model.GearList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

// Represents the UI of Gear List App
public class GearListApp {

    private static final String JSON_STORE = "./data/GearList.json";

    private Scanner input;
    private GearList fullList;
    private GearList nextTripList;
    private Gear gear;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the application
    public GearListApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // The ui layout was inspired by the TellerApp ui
    // The data persistence was inspired by the JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            uiAddGear();
        } else if (command.equals("b")) {
            uiFullList();
        } else if (command.equals("c")) {
            uiListForNextTrip();
        } else if (command.equals("s")) {
            saveGearList();
        } else if (command.equals("l")) {
            loadGearList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the Gear List to file
    private void saveGearList() {
        try {
            jsonWriter.open();
            jsonWriter.write(fullList);
            jsonWriter.close();
            System.out.println("The full list saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Gear List from file
    private void loadGearList() {
        try {
            fullList = jsonReader.read();
            System.out.println("The full list loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes lists
    private void init() {
        fullList = new GearList();
        nextTripList = new GearList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a piece of gear");
        System.out.println("\tb -> view full list");
        System.out.println("\tc -> view list for next trip");
        System.out.println("\ts -> save gear list to file");
        System.out.println("\tl -> load gear list from file");
        System.out.println("\tq -> quit");
    }

    /*
    // MODIFIES: this
    // EFFECTS: a setter to help change the needed boolean variable
    public void setBoolean(boolean needed) {
        this.needed = needed;
    }
    */


    // MODIFIES: this
    // EFFECTS: add a piece of gear in ui
    private void uiAddGear() {
        System.out.print("Enter gear name:");
        String name = input.next();

        System.out.print("Enter gear quantity:");
        int quantity = input.nextInt();

        System.out.print("Enter gear weight:");
        int weight = input.nextInt();

        System.out.print("Needed for next trip? (T/F):");
        String neededInput = input.next();

        if (neededInput.equals("t")) {
            gear = new Gear(name, quantity, weight, true);
        } else {
            gear = new Gear(name, quantity, weight, false);
        }


        fullList.addGear(gear);

        System.out.println("Gear added");


    }

    // EFFECTS: display the full list of gear the user entered
    private void uiFullList() {
        System.out.print(fullList.getGearList());
    }

    // EFFECTS: display the list of gear for next trip and the weight of the list
    private void uiListForNextTrip() {
        System.out.print("Total gear weight: " + fullList.getWeightForNextTrip() + "\n");
        System.out.print(fullList.getGearListForNextTrip());
    }
}
