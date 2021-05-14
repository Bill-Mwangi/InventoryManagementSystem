package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddProductController extends WindowSetter implements KeyListener {
    @FXML private Text actionTarget;
    @FXML TextField nameField;
    @FXML TextField descField;
    @FXML TextField quantityField;
    @FXML TextField supplierIDField;

    public void handleSubmitButton() {
        // TODO: 11/05/2021 Add Auto complete Textfield

        int quantity = Integer.parseInt(quantityField.getText());
        int supplierID = Integer.parseInt(supplierIDField.getText());
        String message = DatabaseAccess.addProduct(new Product(nameField.getText(), descField.getText(), quantity), supplierID);
        actionTarget.setText(message);
    }

    public void handleBackButton() {
        setWindow("/fxml/products.fxml", "Products Menu");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TextFields.bindAutoCompletion(supplierIDField, DatabaseAccess.getAllSuppliers()); // TODO: 11/05/2021 SuggestionList );

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
