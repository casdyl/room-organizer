package ui.tools;

import exceptions.FurnitureDoesNotFit;
import model.Furniture;
import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFurnitureTool extends Tool {
    private Furniture furniture;

    public AddFurnitureTool(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs new button and disables initially
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add to room");
        button.setEnabled(false);

    }

    //MODIFIES: this
    //EFFECTS: constructs new listener object and adds to button
    @Override
    protected void addListener() {
        button.addActionListener(new AddFurnitureToolHandler());
    }

    private class AddFurnitureToolHandler implements ActionListener {

        //MODIFIES: organizer
        //EFFECTS: when button is pressed, get data from fields in panel and construct furniture object
        //        shape based on inputs, and resets text field
        @Override
        public void actionPerformed(ActionEvent e) {
            String furnitureName = organizer.getAddFurnitureNameField();
            int furnitureWidth = organizer.getAddFurnitureWidthField();
            int furnitureHeight = organizer.getAddFurnitureHeightField();
            int furnitureLength = organizer.getAddFurnitureLengthField();
            furniture = new Furniture(furnitureName, furnitureWidth, furnitureHeight, furnitureLength);

            organizer.addToRoom(furniture);

            DefaultListModel<String> defaultListModel = new DefaultListModel();
            for (String name: organizer.getRoom().getFurniture().keySet()) {
                defaultListModel.addElement(name);
            }
            organizer.getList().setModel(defaultListModel);

            organizer.setAddFurnitureName();
            organizer.setAddFurnitureHeight();
            organizer.setAddFurnitureLength();
            organizer.setAddFurnitureWidth();

            //CONSTRUCT FURNITURE SHAPE OBJECT
        }
    }
}
