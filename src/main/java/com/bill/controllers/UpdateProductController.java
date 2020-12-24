package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateProductController extends WindowSetter {
    @FXML TextField productIdField;
    @FXML TextField quantityField;
    @FXML TextField supplierIDField;
    @FXML Text confirmation;

    public void handleSubmitButton() {
        int productID = Integer.parseInt(productIdField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        int supplierID = Integer.parseInt(supplierIDField.getText());
        String message = DatabaseAccess.updateProducts(productID, quantity, supplierID);
        confirmation.setText(message);
    }

    public void handleBackButton() {
        setWindow(450, 390, "/fxml/products.fxml", "Products Menu");
    }
}
