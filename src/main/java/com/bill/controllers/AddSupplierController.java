package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Suppliers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class AddSupplierController extends WindowSetter {
    @FXML private Text actionTarget;
    @FXML TextField supplierNameField;
    @FXML TextField descField;
    @FXML TextField productsField;

    public void handleSubmitButton() {
       String message =  DatabaseAccess.addSupplier(new Suppliers(supplierNameField.getText(), descField.getText(),productsField.getText()));
       actionTarget.setText(message);
    }

    public void handleBackButton() {
        setWindow("/fxml/suppliers.fxml", "Suppliers Menu");
    }
}
