package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.SuppliedProduct;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class SuppliedProductsController extends WindowSetter implements Initializable {
    public TableView<SuppliedProduct> suppliedProductsTable;
    public TableColumn<SuppliedProduct, String> product_name;
    public TableColumn<SuppliedProduct, String> supplier_name;
    public TableColumn<SuppliedProduct, Integer> quantity;
    public TableColumn<SuppliedProduct, Time> time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        supplier_name.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        suppliedProductsTable.getItems().setAll(DatabaseAccess.getSuppliedProducts());
    }

    public void handleBackButton() { setWindow( "/fxml/suppliers.fxml", "Suppliers Menu");
    }
}
