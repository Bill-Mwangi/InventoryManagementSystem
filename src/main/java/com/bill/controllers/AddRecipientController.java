package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Recipient;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddRecipientController extends WindowSetter {
    @FXML public static Text actionTarget;
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField deptField;

    public void handleSubmitButton() {
        DatabaseAccess.addRecipients(new Recipient(firstNameField.getText(), lastNameField.getText(), deptField.getText()));
    }

    public void handleBackButton() {
        setWindow("/fxml/recipients.fxml", "Recipients Menu");
    }
}
