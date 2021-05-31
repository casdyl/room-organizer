package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;

public abstract class Tool {
    protected JButton button;               //activates tool through associated button
    private boolean active;                 //is this tool active?

    protected MyRoomOrganizer organizer;

    //Constructor
    public Tool(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        this.organizer = organizer;
        createButton(parent);
        addToParent(parent, constraints);
        active = false;
        addListener();
    }

    //EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    //EFFECTS: sets this Tool's active fields to false
    public void deactivate() {
        active = false;
    }

    //EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    //EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    //MODIFIES: this
    //EFFECTS: sets button, true if enabled, false if disabled
    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }

    //MODIFIES: parent
    //EFFECTS: adds the given button to the parent component
    public void addToParent(JComponent parent, GridBagConstraints constraints) {
        parent.add(button, constraints);
    }

}
