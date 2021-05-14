package com.bill.controllers;

import com.bill.DatabaseAccess;

public class IndexController extends WindowSetter{

    public void handleSupplierButton() {
        setWindow("/fxml/suppliers.fxml", "Suppliers Menu");
    }

    public void handleProductsButton() { setWindow( "/fxml/products.fxml", "Products Menu");}

    public void handleRecipientButton() {
        setWindow("/fxml/recipients.fxml", "Recipients Menu");
    }

    public void handleQuitButton() {
        DatabaseAccess.closeConnection();
        setWindow("/fxml/login.fxml", "Inventory Management System");
    }
}
