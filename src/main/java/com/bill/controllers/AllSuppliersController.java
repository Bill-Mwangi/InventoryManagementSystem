package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Suppliers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AllSuppliersController extends WindowSetter implements Initializable {
    @FXML
    protected TableView<Suppliers> allSuppliersTable;
    @FXML
    protected TableColumn<Suppliers, Integer> supplierID;
    @FXML
    protected TableColumn<Suppliers, String> supplierName;
    @FXML
    protected TableColumn<Suppliers, String> description;
    @FXML
    protected TableColumn<Suppliers, String> products;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        products.setCellValueFactory(new PropertyValueFactory<>("products"));
        allSuppliersTable.getItems().setAll(DatabaseAccess.getAllSuppliers());
    }

    public void handleBackButton() { setWindow("/fxml/suppliers.fxml","Suppliers Menu");
    }
}

