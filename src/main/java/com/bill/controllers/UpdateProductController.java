package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProductController extends WindowSetter implements Initializable {
    @FXML TextField productField;
    @FXML TextField quantityField;
    @FXML TextField supplierField;
    @FXML Text confirmation;

    public void handleSubmitButton() {
        String productName = productField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String supplierName = supplierField.getText();
        String message = DatabaseAccess.updateProducts(productName, quantity, supplierName);
        confirmation.setText(message);
        productField.clear();
        quantityField.clear();
        supplierField.clear();
    }

    public void handleBackButton() {
        setWindow("/fxml/products.fxml", "Products Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(productField, DatabaseAccess.getProductNames());
        TextFields.bindAutoCompletion(supplierField, DatabaseAccess.getSupplierNames());
    }
}
