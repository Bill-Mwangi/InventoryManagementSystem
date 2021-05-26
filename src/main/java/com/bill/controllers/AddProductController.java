package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController extends WindowSetter implements Initializable {
    @FXML private Text actionTarget;
    @FXML TextField nameField;
    @FXML TextField descField;
    @FXML TextField quantityField;
    @FXML TextField supplierField;

    public void handleSubmitButton() {
        int quantity = Integer.parseInt(quantityField.getText());
        String supplierName = supplierField.getText();
        String message = DatabaseAccess.addProduct(new Product(nameField.getText(), descField.getText(), quantity), supplierName);
        actionTarget.setText(message);
        nameField.clear();
        descField.clear();
        quantityField.clear();
        supplierField.clear();
    }

    public void handleBackButton() {
        setWindow("/fxml/products.fxml", "Products Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            TextFields.bindAutoCompletion(supplierField, DatabaseAccess.getSupplierNames());
    }
}
