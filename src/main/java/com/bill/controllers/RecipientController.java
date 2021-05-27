package com.bill.controllers;

public class RecipientController extends WindowSetter {
    public void handleAddRecipient() {
        setWindow( "/fxml/addRecipient.fxml", "New recipient");
    }

    public void handleAllRecipients() {
        setWindow(700, 1000,"/fxml/allRecipients.fxml", "Recipients List");
    }

    public void handleEditRecipient() {
    }

    public void handleBackButton() {
        setWindow("/fxml/index.fxml", "Inventory Management System");
    }
}
