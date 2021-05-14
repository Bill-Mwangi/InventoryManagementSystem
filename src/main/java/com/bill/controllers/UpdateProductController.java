package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UpdateProductController extends WindowSetter implements KeyListener {
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
        setWindow("/fxml/products.fxml", "Products Menu");
    }

    @Override
    public void keyTyped(KeyEvent event) {
        String str = "";
        str += event.getKeyChar();
        String query = "SELECT * FROM  WHERE LIKE " + str + " LIMIT 5;";

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
