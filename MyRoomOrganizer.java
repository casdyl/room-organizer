package ui;

import exceptions.FurnitureDoesNotFit;
import exceptions.FurnitureNotFound;
import model.Furniture;
import model.Room;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Represents the main window of the Room application
public class MyRoomOrganizer extends JFrame {

    private Room room;
    private AddFurnitureName addFurnitureNameField;
    private AddFurnitureInputs addFurnitureWidthField;
    private AddFurnitureInputs addFurnitureHeightField;
    private AddFurnitureInputs addFurnitureLengthField;
    private RemoveFurnitureNameField removeNameField;
    private AddFurnitureTool add;
    private RemoveFurnitureTool remove;
    private Tool activeTool;

    private JTextArea textArea;
    private JPanel roomPanel;
    private JPanel toolArea;
    private JLabel furnitureName;
    private JLabel furnitureWidth;
    private JLabel furnitureHeight;
    private JLabel furnitureLength;
    private JList<String> list;
    private DefaultListModel defaultListModel;
    private JScrollPane scrollPane;
    private Font textFieldFont = new Font("Helvetica",Font.PLAIN,14);

    private static int WIDTH = 700;
    private static int HEIGHT = 700;

    //Constructor
    //EFFECTS: runs room application
    public MyRoomOrganizer() throws IOException {
        super("Room Organizer");
        room = new Room();
        loadRoom(room);
        initializeGraphics();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    room.save(room);
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: draws the JFrame window where this room will operate, and populates the tools to be used to manipulate
    //          this room
    private void initializeGraphics() throws IOException {
        setLayout(new BorderLayout());
        setSize((int) (WIDTH * 1.6), HEIGHT);
        createRoomPanel();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates area where furniture will be printed
    private void createRoomPanel() {
        roomPanel = new JPanel();
        textArea = new JTextArea();
        roomPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        roomPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Your Room"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        Color color = new Color(169, 194, 196);
        roomPanel.setBackground(color);
        textArea.setFont(textFieldFont);
        textArea.setEditable(false);
        Color color1 = new Color(227, 231, 231);
        textArea.setBackground(color1);
        setText("Welcome to your room! Enter furniture details in inches or remove by name.");
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(WIDTH / 2, (int) (HEIGHT / 4.5)));
        defaultListModel = new DefaultListModel();
        for (String name: room.getFurniture().keySet()) {
            defaultListModel.addElement(name);
        }
        list = new JList<>(defaultListModel);
        roomPanel.add(list);
        add(roomPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.SOUTH);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: constructs text fields
    private void createAddPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Enter furniture details: "),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(0, 0, 10, 0);

        //enter furniture name
        furnitureName = new JLabel("Name: ");
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(furnitureName, constraints);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        addFurnitureNameField = new AddFurnitureName(this, panel, constraints);

        //enter furniture width
        createFieldWidth(panel, constraints);

        //enter furniture height
        createFieldHeight(panel, constraints);

        //enter furniture length
        createFieldLength(panel, constraints);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridy = 4;
        add = new AddFurnitureTool(this, panel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs area for view room button
    private void createViewPanel(JPanel viewPanel) {
        viewPanel.setLayout(new GridBagLayout());
        viewPanel.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(0,0,10,0);

        constraints.gridy = 1;
        ViewRoomTool view = new ViewRoomTool(this, viewPanel, constraints);

        constraints.gridy = 2;
        SaveTool save = new SaveTool(this, viewPanel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs area where user inputs furniture to remove
    private void createRemovePanel(JPanel removePanel) {
        removePanel.setLayout(new GridBagLayout());
        removePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Remove a piece of furniture: "),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(0, 0, 10, 0);

        furnitureName = new JLabel("Name: ");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 0;
        removePanel.add(furnitureName, constraints);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        removeNameField = new RemoveFurnitureNameField(this, removePanel, constraints);

        constraints.gridy = 1;
        remove = new RemoveFurnitureTool(this, removePanel, constraints);
    }

    //Helper to create tools
    private void createTools() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridLayout());
        toolArea.setPreferredSize(new Dimension((WIDTH), HEIGHT));
        toolContainer.add(toolArea, BorderLayout.EAST);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        JPanel addPanel = new JPanel();
        createAddPanel(addPanel);
        toolArea.add(addPanel, constraints);

        constraints.gridy = 2;
        JPanel removePanel = new JPanel();
        createRemovePanel(removePanel);
        toolArea.add(removePanel, constraints);

        constraints.gridy = 3;
        JPanel viewPanel = new JPanel();
        createViewPanel(viewPanel);
        toolArea.add(viewPanel, constraints);

    }

    //getters
    public Room getRoom() {
        return room;
    }

    public JList getList() {
        return list;
    }

    public AddFurnitureTool getAdd() {
        return add;
    }

    public RemoveFurnitureTool getRemove() {
        return remove;
    }

    //EFFECTS: gets text in remove name field in remove area
    public String getRemoveFurnitureName() {
        return removeNameField.getTextField();
    }

    //EFFECTS: gets the inputted text in name field in add area
    public String getAddFurnitureNameField() {
        return addFurnitureNameField.getTextField();
    }

    //EFFECTS: gets the inputted int in width field in add area
    public int getAddFurnitureWidthField() {
        return Integer.parseInt(addFurnitureWidthField.getTextField());
    }

    //EFFECTS: gets the inputted int in height field in add area
    public int getAddFurnitureHeightField() {
        return Integer.parseInt(addFurnitureHeightField.getTextField());
    }

    //EFFECTS: gets the inputted int in length field in add area
    public int getAddFurnitureLengthField() {
        return Integer.parseInt(addFurnitureLengthField.getTextField());
    }

    //setters
    //MODIFIES: this
    //EFFECTS: sets the name field in remove area to empty
    public void setRemoveFurnitureNameField() {
        removeNameField.setEmpty();
    }

    //MODIFIES: this
    //EFFECTS: sets the given tool as the activeTool
    public void setActiveTool(Tool setTool) {
        if (activeTool != null) {
            activeTool.deactivate();
            setTool.activate();
            activeTool = setTool;
        }
    }

    //MODIFIES: this
    //EFFECTS: sets text in text area of room
    public void setText(String txt) {
        textArea.setText(txt);
    }

    //MODIFIES: this
    //EFFECTS: sets name field in add area to empty
    public void setAddFurnitureName() {
        addFurnitureNameField.setEmpty();
    }

    //MODIFIES: this
    //EFFECTS: sets name field in add area to empty
    public void setAddFurnitureWidth() {
        addFurnitureWidthField.setEmpty();
    }

    //MODIFIES: this
    //EFFECTS: sets name field in add area to empty
    public void setAddFurnitureHeight() {
        addFurnitureHeightField.setEmpty();
    }

    //MODIFIES: this
    //EFFECTS: sets name field in add area to empty
    public void setAddFurnitureLength() {
        addFurnitureLengthField.setEmpty();
    }

    //MODIFIES: this
    //EFFECTS: adds given furniture to currentFurniture list (room)
    public void addToRoom(Furniture f) {
        try {
            room.addFurniture(f);
            setText("The following has been added to your room: " + room.printFurniture(f));
        } catch (FurnitureDoesNotFit dnf) {
            setText("This furniture does not fit!");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes given furniture figure from Room
    public void removeFromRoom(String name) {
        try {
            room.removeFurniture(name);
            setText("The following has been removed from your room: " + removeNameField.getTextField());
        } catch (FurnitureNotFound e) {
            setText("The following furniture piece could not be found in room: " + removeNameField.getTextField());
        }
    }

    //MODIFIES: this
    //EFFECTS: creates input field with labels
    private void createFieldLength(JPanel panel, GridBagConstraints constraints) {
        furnitureLength = new JLabel("Length: ");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(furnitureLength, constraints);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        addFurnitureLengthField = new AddFurnitureInputs(this, panel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: creates input field with labels
    private void createFieldHeight(JPanel panel, GridBagConstraints constraints) {
        furnitureHeight = new JLabel("Height: ");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(furnitureHeight, constraints);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        addFurnitureHeightField = new AddFurnitureInputs(this, panel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: creates input field with labels
    private void createFieldWidth(JPanel panel, GridBagConstraints constraints) {
        furnitureWidth = new JLabel("Width: ");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(furnitureWidth, constraints);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        addFurnitureWidthField = new AddFurnitureInputs(this, panel, constraints);
    }

    //EFFECTS: prints current furniture in room
    public void viewCurrent(Room room) {
        String currentFurniture = "Furniture in room: " + room.printCurrentFurniture();
        setText(currentFurniture);
    }

    //MODIFIES: this
    //EFFECTS: loads the room
    public void loadRoom(Room room) throws IOException {
        room.load(room);
    }

    //MODIFIES: this
    //EFFECTS: saves the room
    public void saveRoom(Room room) throws IOException {
        room.save(room);
    }

    public static void main(String[] args) throws IOException {
        new MyRoomOrganizer();
    }
}



