package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AssignProductController extends WindowSetter {
    @FXML TextField productIDField;
    @FXML TextField recipientIDField;
    @FXML TextField quantityField;
    @FXML Text confirmation;

    public void handleSubmitButton() {
        int productID = Integer.parseInt(productIDField.getText());
        int recipientID = Integer.parseInt(recipientIDField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        DatabaseAccess.assignProduct(productID, recipientID, quantity);
    }

    public void handleBackButton() { setWindow(450, 390, "/fxml/products.fxml", "Products Menu");
    }
}
