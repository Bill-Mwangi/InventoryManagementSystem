package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddProductController extends WindowSetter {
    @FXML private Text actionTarget;
    @FXML TextField nameField;
    @FXML TextField descField;
    @FXML TextField quantityField;
    @FXML TextField supplierIDField;

    public void handleSubmitButton() {
        int quantity = Integer.parseInt(quantityField.getText());
        int supplierID = Integer.parseInt(supplierIDField.getText());
        String message = DatabaseAccess.addProduct(new Product(nameField.getText(), descField.getText(), quantity), supplierID);
        actionTarget.setText(message);
    }

    public void handleBackButton() {
        setWindow(450, 390, "/fxml/products.fxml", "Products Menu");
    }
}
