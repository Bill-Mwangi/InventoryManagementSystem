package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Suppliers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class AddSupplierController extends WindowSetter {
    @FXML public static Text actionTarget;
    @FXML TextField supplierNameField;
    @FXML TextField descField;
    @FXML TextField productsField;

    public void handleSubmitButton() {
        DatabaseAccess.addSupplier(new Suppliers(supplierNameField.getText(), descField.getText(),productsField.getText()));
    }

    public void handleBackButton() {
        setWindow("/fxml/suppliers.fxml", "Suppliers Menu");
    }
}
