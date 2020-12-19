package com.bill.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateProductController extends WindowSetter {
    @FXML TextField nameField;
    @FXML TextField descField;
    @FXML TextField quantityField;
    @FXML TextField supplierIDField;
    @FXML Text confirmation;

    public void handleSubmitButton() {
        int quantity = Integer.parseInt(quantityField.getText());
        int supplierID = Integer.parseInt(supplierIDField.getText());
//        DatabaseAccess.updateProducts(new Product(nameField.getText(),
//                descField.getText(), quantity), supplierID);

    }

    public void handleBackButton() {
        setWindow(450, 390, "/fxml/products.fxml", "Products Menu");
    }
}
