package ui.tools;

import ui.MyRoomOrganizer;

import javax.swing.*;
import java.awt.*;

public abstract class TextField {
    MyRoomOrganizer organizer;
    JTextField textField;

    public TextField(MyRoomOrganizer organizer, JComponent parent, GridBagConstraints constraints) {
        this.organizer = organizer;
        textField = new JTextField(10);
        addToParent(parent, constraints);
        addListener();
    }

    //MODIFIES: parent
    //EFFECTS: adds input (text) fields to parent JComponent with constrains
    private void addToParent(JComponent parent, GridBagConstraints constraints) {
        parent.add(textField, constraints);
    }

    //EFFECTS: add listener to input (text) field
    protected abstract void addListener();

    //EFFECTS: returns the string in the field
    public String getTextField() {
        return textField.getText();
    }

    //MODIFIES: this
    //EFFECTS: sets name input field to empty
    public void setEmpty() {
        textField.setText("");
    }

}
