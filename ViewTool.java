package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;

public abstract class ViewTool extends Tool {

    public ViewTool(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    //MODIFIES: this
    //EFFECTS: construct button with given label
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
    }

    //EFFECTS: returns label for button
    protected abstract String getLabel();
}
