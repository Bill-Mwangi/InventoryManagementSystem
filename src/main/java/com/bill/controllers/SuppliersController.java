package com.bill.controllers;

public class SuppliersController extends WindowSetter {

    public void handleAddSupplier() {
        setWindow("/fxml/addSupplier.fxml", "New Supplier");
    }

    public void handleAllSuppliers() {
        setWindow(700, 700, "/fxml/allSuppliers.fxml", "Suppliers List");
    }

    public void handleBackButton() {
        setWindow( "/fxml/index.fxml", "Inventory Management System");
    }

    public void listSuppliedProducts() {setWindow(700, 700, "/fxml/suppliedProducts.fxml", "Supplied Products");
    }
}
