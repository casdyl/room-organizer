package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AddFurnitureName extends TextField {

    public AddFurnitureName(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs new listener and adds it to input field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddFurnitureNameHandler());
    }

    private class AddFurnitureNameHandler implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            fieldFilled();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fieldFilled();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fieldFilled();
        }
    }

    //MODIFIES: organizer
    //EFFECTS: enables add button if field is filled
    private void fieldFilled() {
        String furnitureName = textField.getText();

        if (!furnitureName.isEmpty()) {
            organizer.getAdd().setEnabled(false);
        }
    }
}
