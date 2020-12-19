package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.Recipient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AllRecipientsController extends WindowSetter implements Initializable {
    @FXML
    protected TableView<Recipient> allRecipientsTable;
    @FXML
    protected TableColumn<Recipient, Integer> recipientID;
    @FXML
    protected TableColumn<Recipient, String> firstName;
    @FXML
    protected TableColumn<Recipient, String> lastName;
    @FXML
    protected TableColumn<Recipient, String> department;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipientID.setCellValueFactory(new PropertyValueFactory<>("recipientID"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        allRecipientsTable.getItems().setAll(DatabaseAccess.getAllRecipients());
    }

    public void handleBackButton() {
        setWindow("/fxml/recipients.fxml", "Recipients Menu");

    }
}

