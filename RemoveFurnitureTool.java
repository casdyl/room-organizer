package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFurnitureTool extends Tool {

    public RemoveFurnitureTool(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs remove button added to JComponent (parent) and passed as param
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Remove from room");
        button.setEnabled(false);

    }

    //MODIFIES: this
    //EFFECTS: constructs new listener object added to JButton
    @Override
    protected void addListener() {
        button.addActionListener(new RemoveToolClickHandler());
    }

    private class RemoveToolClickHandler implements ActionListener {

        //EFFECTS: sets active tool to remove tool
        //          called by framework when tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = organizer.getRemoveFurnitureName();
            organizer.setActiveTool(RemoveFurnitureTool.this);
            organizer.removeFromRoom(name);
            organizer.setRemoveFurnitureNameField();

            DefaultListModel<String> defaultListModel = new DefaultListModel();
            for (String n: organizer.getRoom().getFurniture().keySet()) {
                defaultListModel.addElement(n);
            }
            organizer.getList().setModel(defaultListModel);
        }
    }
}
