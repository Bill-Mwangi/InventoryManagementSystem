package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignProductController extends WindowSetter implements Initializable {
    @FXML TextField productField;
    @FXML TextField recipientField;
    @FXML TextField quantityField;
    @FXML  Text confirmation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(productField, DatabaseAccess.getProductNames());
        TextFields.bindAutoCompletion(recipientField, DatabaseAccess.getRecipientNames());
    }

    public void handleSubmitButton() {
        String productName = productField.getText();
        String recipientName = recipientField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String conf = DatabaseAccess.assignProduct(productName, recipientName, quantity);

        productField.clear();
        recipientField.clear();
        quantityField.clear();
        confirmation.setText(conf);
    }

    public void handleBackButton() { setWindow("/fxml/products.fxml", "Products Menu");
    }
}
