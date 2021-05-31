package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRoomTool extends ViewTool {

    public ViewRoomTool(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs new listener object and adds it to button
    @Override
    protected String getLabel() {
        return "View room";
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewRoomToolHandler());
    }

    private class ViewRoomToolHandler implements ActionListener {

        //EFFECTS: prints current furniture in room when button pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            organizer.viewCurrent(organizer.getRoom());
        }
    }
}
