package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class AllProductsController extends WindowSetter implements Initializable {
    @FXML
    protected TableView<Product> allProductsTable;
    @FXML
    protected TableColumn<Product, Integer> productID;
    @FXML
    protected TableColumn<Product, String> productName;
    @FXML
    protected TableColumn<Product, String> description;
    @FXML
    protected TableColumn<Product, Integer> quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        allProductsTable.getItems().setAll(DatabaseAccess.getAllProducts());
    }

    public void handleBackButton() { setWindow(450, 390, "/fxml/products.fxml", "Products Menu");
    }
}
