package ui;

import model.Event;
import model.Gear;
import model.GearList;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//represents the graphical user interface for the gear list app
// part of the GUI was inspired by https://github.com/pkhiani/AuctionApp
// and Youtube channel BroCode
public class GUI extends JFrame implements ActionListener {

    private GearList gearListGUI;

    private static final String JSON_STORE = "./data/GearListGUI.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JPanel mainMenu;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    private JPanel gearListPanel;

    private JPanel addGearPanel;
    private JButton addGear;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;

    private JLabel name;
    private JLabel quantity;
    private JLabel weight;
    private JLabel neededNextTrip;

    private JTextArea textArea;
    private String displayedList;
    private JLabel labelForWeight;

    // Create a panel to hold the buttons
    private JPanel buttonPanel = new JPanel();


    // represents a GUI window
    public GUI() {
        super("Gear List App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 800));

        loadMainMenu();
        gearListPanel();
        addGearPanel();

        JLabel mainScreenImage = new JLabel();
        addImageToLabel(mainScreenImage);
        ImageIcon image = new ImageIcon("logo icon.png");
        setIconImage(image.getImage());
        JLabel mainScreenLabel = new JLabel("Gear List App");

        //       add(mainScreenLabel, BorderLayout.NORTH);
        addLabel(mainScreenLabel);


        initializeMenuButtons();
        addButtons(button1, button2, button3, button4, button5, button6);
        addActionToButton();

        mainMenu.setVisible(true);

        gearListGUI = new GearList();

        shutdownHook();

    }

    // EFFECTS: Registering shutdown hook, for printing log
    private void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook is executing...");
            printLog();
        }));
    }

    // EFFECTS: print to the console all the events that have been logged
    private void printLog() {

        // Get the instance of EventLog
        EventLog el = EventLog.getInstance();

        // Iterates over the events and print them out
        for (Event event : el) {
            System.out.println(event); // Assuming Event class overrides toString() method
        }
    }




    // EFFECTS: Creates main menu panel
    private void loadMainMenu() {
        mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout());
        mainMenu.setBackground(Color.white);
        add(mainMenu);
    }

    // EFFECTS: creates buttons on main menu
    private void initializeMenuButtons() {
        button1 = new JButton("add a piece of gear");
        button2 = new JButton("view full list");
        button3 = new JButton("view list for next trip");
        button4 = new JButton("save gear list to file");
        button5 = new JButton("load gear list from file");
        button6 = new JButton("quit");
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    private void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.cyan);

        // Set layout manager to BoxLayout with Y_AXIS alignment
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(button);

        // Add the nested panel to the main panel
        panel.add(buttonPanel, BorderLayout.EAST);

        pack();
//        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: add buttons to main menus
    private void addButtons(JButton button1, JButton button2, JButton button3,
                            JButton button4, JButton button5, JButton button6) {

        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);

    }

    // EFFECTS: Creates a button for sub panels
    private void addMenuButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.gray);
        button.setForeground(Color.black);

        panel.add(button);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // EFFECTS: Creates and adds the main menu image
    private void addImageToLabel(JLabel j) {
        j.setIcon(new ImageIcon("big-wall-climbing.jpg"));
        j.setMinimumSize(new Dimension(100, 100));
        mainMenu.add(j, BorderLayout.WEST);
        //add(mainScreenLabel, BorderLayout.NORTH);
    }

    // EFFECTS: Creates and adds the main menu text
    private void addLabel(JLabel j) {
        j.setFont(new Font("Arial", Font.BOLD, 50));
        mainMenu.add(j, BorderLayout.NORTH);
        //       add(mainScreenLabel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: add action to button
    private void addActionToButton() {

        button1.addActionListener(this);
        button1.setActionCommand(null);
        button2.addActionListener(this);
        button2.setActionCommand(null);
        button3.addActionListener(this);
        button3.setActionCommand(null);
        button4.addActionListener(this);
        button4.setActionCommand(null);
        button5.addActionListener(this);
        button5.setActionCommand(null);
        button6.addActionListener(this);
        button6.setActionCommand(null);
    }

    // EFFECTS: assign button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("add a piece of gear")) {
            initializeAddGearPanel();
        } else if (ae.getActionCommand().equals("view full list")) {
            initializeGearListPanel();
        } else if (ae.getActionCommand().equals("view list for next trip")) {
            initializeGearListForNextTripPanel();
        } else if (ae.getActionCommand().equals("save gear list to file")) {
            saveGearList();
        } else if (ae.getActionCommand().equals("load gear list from file")) {
            loadGearList();
        } else if (ae.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (ae.getActionCommand().equals("Add gear")) {
            addGearToList();
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates add Gear Panel
    private void addGearPanel() {

        addGearPanel = new JPanel(new GridLayout(0, 2));
        JButton mainMenuButton = new JButton("Return to main menu");
        mainMenuButton.setActionCommand(null);
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, addGearPanel);

        createAddGearPanel();
        addInputElements();
    }

    // EFFECTS: switch display to AddGearPanel
    private void initializeAddGearPanel() {
        add(addGearPanel);
        addGearPanel.setVisible(true);
        mainMenu.setVisible(false);
        gearListPanel.setVisible(false);
    }

    // EFFECTS: switch display to main menu
    private void returnToMainMenu() {
        mainMenu.setVisible(true);
        gearListPanel.setVisible(false);
        addGearPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates the gear info input panel
    private void createAddGearPanel() {

        addGear = new JButton("Add gear");
        addGear.setActionCommand(null);
        addGear.addActionListener(this);

        name = new JLabel("name:");
        t1 = new JTextField(10);
        quantity = new JLabel("quantity:");
        t2 = new JTextField(10);
        weight = new JLabel("weight:");
        t3 = new JTextField(10);
        neededNextTrip = new JLabel("needed for next trip? (type true for true, or anything else for false):");
        t4 = new JTextField(10);

        inputPanelSettings();
    }

    // EFFECTS: Adds the labels and text fields to the input panel
    private void addInputElements() {

        addGearPanel.add(addGear);

        addGearPanel.add(name);
        addGearPanel.add(t1);
        addGearPanel.add(quantity);
        addGearPanel.add(t2);
        addGearPanel.add(weight);
        addGearPanel.add(t3);
        addGearPanel.add(neededNextTrip);
        addGearPanel.add(t4);
    }

    // EFFECTS: Changes settings for the labels and text fields
    private void inputPanelSettings() {

        addGear.setBackground(Color.cyan);
        addGear.setForeground(Color.black);
        addGear.setFont(new Font("Arial", Font.BOLD, 26));

        name.setFont(new Font("Arial", Font.BOLD, 26));
        quantity.setFont(new Font("Arial", Font.BOLD, 26));
        weight.setFont(new Font("Arial", Font.BOLD, 26));
        neededNextTrip.setFont(new Font("Arial", Font.BOLD, 26));

        t1.setMaximumSize(new Dimension(500, 200));
        t2.setMaximumSize(new Dimension(500, 200));
        t3.setMaximumSize(new Dimension(500, 200));
        t4.setMaximumSize(new Dimension(500, 200));
    }

    // MODIFIES: this
    // EFFECTS: Adds the user input gear
    private void addGearToList() {

        Gear user = new Gear(t1.getText(), Integer.parseInt(t2.getText()), Integer.parseInt(t3.getText()),
                Boolean.parseBoolean(t4.getText())); //must type true to be true, false otherwise

        gearListGUI.addGear(user);

        System.out.print("added 1 piece");

    }


    // MODIFIES: this
    // EFFECTS: Creates the gear list panel
    private void gearListPanel() {

        gearListPanel = new JPanel();
        JButton mainMenuButton = new JButton("Return to main menu");
        mainMenuButton.setActionCommand(null);
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, gearListPanel);

        displayedList = "empty list";

        // Create a JTextArea with multi-line text
        textArea = new JTextArea(displayedList, 3, 40);

        // disable line wrapping
        textArea.setLineWrap(false);

        // disable word wrapping
        textArea.setWrapStyleWord(false);

        // Create a JScrollPane to provide scrolling functionality
        JScrollPane scrollPane = new JScrollPane(textArea);

        gearListPanel.add(scrollPane);

        labelForWeight = new JLabel("Total Weight for Next Trip: 0");
        gearListPanel.add(labelForWeight, BorderLayout.SOUTH); // Add JLabel to the panel

    }

    // EFFECTS: switch display to Gear List Panel, display calculated total weight
    private void initializeGearListPanel() {
        add(gearListPanel);
        gearListPanel.setVisible(true);
        mainMenu.setVisible(false);
        addGearPanel.setVisible(false);

        displayedList = gearListGUI.getGearList();

        textArea.setText(displayedList);
        labelForWeight.setText("Total Weight for Next Trip: " + gearListGUI.getWeightForNextTrip());

    }

    // EFFECTS: switch display to Gear List for next trip Panel, display calculated total weight
    private void initializeGearListForNextTripPanel() {
        add(gearListPanel);
        gearListPanel.setVisible(true);
        mainMenu.setVisible(false);
        addGearPanel.setVisible(false);

        displayedList = gearListGUI.getGearListForNextTrip();

        textArea.setText(displayedList);
        labelForWeight.setText("Total Weight for Next Trip: " + gearListGUI.getWeightForNextTrip());

    }

    // EFFECTS: saves the gear list to json
    private void saveGearList() {
        try {
            jsonWriter.open();
            jsonWriter.write(gearListGUI);
            jsonWriter.close();
            System.out.println("The full list saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the gear list from json file
    private void loadGearList() {

        try {
            gearListGUI = jsonReader.read();
            System.out.println("The full list loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }


}


