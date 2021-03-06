package com.bill.controllers;

import com.bill.AssignedProduct;
import com.bill.DatabaseAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;


public class AssignedProductsController extends WindowSetter implements Initializable {
    @FXML
    protected TableView<AssignedProduct> assignedProductsTable;
    @FXML
    protected TableColumn<AssignedProduct, String> product_name;
    @FXML
    protected TableColumn<AssignedProduct, String> first_name;
    @FXML
    protected TableColumn<AssignedProduct, String> last_name;
    @FXML
    protected TableColumn<AssignedProduct, Integer> quantity;
    @FXML
    protected TableColumn<AssignedProduct, Time> time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        assignedProductsTable.getItems().setAll(DatabaseAccess.getAssignedProducts());
    }

    public void handleBackButton() { setWindow( "/fxml/products.fxml", "Products Menu");
    }
}
