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
                createUser = con.prepareStatement("INSERT INTO inventory.user(FirstName, LastName, Email, Password) VALUES(?,?,?,?)");
                createUser.setString(1, FnameField.getText());
                createUser.setString(2, LnameField.getText());
                createUser.setString(3, emailField.getText());
                createUser.setString(4, DigestUtils.sha256Hex(passwordField.getText()));
                int rowCount = createUser.executeUpdate();

                if (rowCount == 1)
                    actionTarget.setText("New user added.");
                else
                    actionTarget.setText("Trouble adding new user.");
                FnameField.clear();
                LnameField.clear();
                emailField.clear();
                passwordField.clear();
                confirmField.clear();
            } catch (SQLException exception) {
                actionTarget.setText("Error connecting to database.");
            }
        } else
            actionTarget.setText("The passwords do not match.");
    }

    public void handleBackButton() {
        setWindow("/fxml/login.fxml", "Inventory Management System");
    }
}
