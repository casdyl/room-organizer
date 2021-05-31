package ui.tools;

import model.Room;
import model.Saveable;
import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveTool extends Tool implements Saveable {

    public SaveTool(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        button.setEnabled(true);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolHandler());
    }

    @Override
    public void save(Room room) throws IOException {

    }

    private class SaveToolHandler implements ActionListener {

        //MODIFIES: organizer
        //EFFECTS: when button is pressed room is saved
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                organizer.saveRoom(organizer.getRoom());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
