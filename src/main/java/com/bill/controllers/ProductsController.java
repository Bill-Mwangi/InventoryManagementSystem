package com.bill.controllers;

import com.bill.Product;
import com.bill.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

//import static com.bill.interfaces.ProductInterface.PRODUCTLIST;

public class ProductsController extends WindowSetter {
    @FXML
    TableView<Product> allProductsTable;

    @FXML
    FXCollections tableValues;

    public void addProduct() { setWindow("/fxml/addProduct.fxml", "Add Product");
    }

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

    public void allProducts() { setWindow(600, 500, "/fxml/allProducts.fxml", "All Products");
    }
}
