package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddFurnitureInputs extends TextField {
    private String pattern = "\\d+";
    private JLabel errorMsg = new JLabel("Please enter a number");

    public AddFurnitureInputs(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        super(organizer, parent, constraints);
    }

    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener((new AddFurnitureInputsHandler()));
    }

    private class AddFurnitureInputsHandler implements DocumentListener {

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
    //EFFECTS: enables add button if field is not empty and is correct type
    public void fieldFilled() {
        String width = textField.getText();
        String height = textField.getText();
        String length = textField.getText();

        Pattern r = Pattern.compile(pattern);
        Matcher widthM = r.matcher(width);
        Matcher heightM = r.matcher(height);
        Matcher lengthM = r.matcher(length);

        try {
            if (widthM.matches() && heightM.matches() && lengthM.matches()) {
                errorMsg.setForeground(organizer.getBackground());
                organizer.getAdd().setEnabled(true);
            } else {
                errorMsg.setForeground(Color.RED);
            }
        } catch (NumberFormatException e) {
            organizer.setText("Fill out all fields");
        }
    }
}
