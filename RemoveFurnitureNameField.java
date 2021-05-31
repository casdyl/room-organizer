package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class RemoveFurnitureNameField  extends TextField {

    public RemoveFurnitureNameField(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs new listener and adds to input text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new RemoveFurnitureNameFieldHandler());
    }

    private class RemoveFurnitureNameFieldHandler implements DocumentListener {

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

        //MODIFIES: organizer
        //EFFECTS: enables button if input field is filled
        public void fieldFilled() {
            String name = organizer.getRemoveFurnitureName();
            if (!name.isEmpty()) {
                organizer.getRemove().setEnabled(true);
            } else {
                organizer.getRemove().setEnabled(false);
            }
        }
    }
}
