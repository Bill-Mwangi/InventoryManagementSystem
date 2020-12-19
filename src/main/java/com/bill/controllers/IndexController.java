package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class IndexController extends WindowSetter{
    @FXML private ImageView productImage;

    //productImage = new Image("/icon/products.jpg");

    public void handleSupplierButton() {
        setWindow("/fxml/suppliers.fxml", "Suppliers Menu");
    }

    public void handleProductsButton() {
        setWindow(450, 390, "/fxml/products.fxml", "Products Menu");
    }

    public void handleRecipientButton() {
        setWindow("/fxml/recipients.fxml", "Recipients Menu");
    }

    public void handleQuitButton() {
        DatabaseAccess.closeConnection();
        setWindow("/fxml/login.fxml", "Inventory Management System");
    }
}
