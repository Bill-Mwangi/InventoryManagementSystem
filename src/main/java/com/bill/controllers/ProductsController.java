package com.bill.controllers;

public class ProductsController extends WindowSetter {

    public void addProduct() { setWindow("/fxml/addProduct.fxml", "Add Product"); }

    public void updateProduct() { setWindow("/fxml/updateProduct.fxml", "Update Product"); }

    public void assignProduct() {
        setWindow("/fxml/assignProduct.fxml", "Assign Products");
    }

    public void availableProducts() {
        setWindow(600, 500,"/fxml/availableProducts.fxml", "Available Products");
    }

    public void unavailableProducts() {
        setWindow(600, 500,"/fxml/unavailableProducts.fxml", "Unavailable Products");
    }

    public void handleBackButton() {
        setWindow("/fxml/index.fxml", "Inventory Management System");
    }

    public void assignedProducts() { setWindow(700, 500, "/fxml/assignedProducts.fxml", "Assigned Products");
    }
}
