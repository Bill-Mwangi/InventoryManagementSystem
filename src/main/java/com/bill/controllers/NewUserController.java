package com.bill.controllers;

import com.bill.interfaces.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewUserController extends WindowSetter implements Connect {
    private static PreparedStatement createUser;
    @FXML private TextField FnameField;
    @FXML private TextField LnameField;
    @FXML private TextField emailField;
    @FXML private Text actionTarget;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;

    public void handleSignUpButton() {
        if (confirmField.getText().equals(passwordField.getText())) {
            try {
                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                createUser = con.prepareStatement("INSERT INTO inventory.user(first_name, last_name, email, password) VALUES(?,?,?,?)");
                createUser.setString(1, FnameField.getText());
                createUser.setString(2, LnameField.getText());
                createUser.setString(3, emailField.getText());
                createUser.setString(4, DigestUtils.sha256Hex(passwordField.getText()));

                if (createUser.execute()) {
                    actionTarget.setText("Trouble adding new user.");
                } else {
                    actionTarget.setText("New user added.");
                    FnameField.clear();
                    LnameField.clear();
                    emailField.clear();
                    passwordField.clear();
                    confirmField.clear();
                    setWindow( "/fxml/login.fxml", "Inventory Management System");
                }

            } catch (SQLException exception) {
                actionTarget.setText("Error connecting to database.");
            }
        } else
            actionTarget.setText("The passwords do not match.");
            passwordField.clear();
            confirmField.clear();
    }

    public void handleBackButton() {
        setWindow("/fxml/login.fxml", "Inventory Management System");
    }
}
