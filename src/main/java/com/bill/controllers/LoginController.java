package com.bill.controllers;

import com.bill.DatabaseAccess;
import com.bill.interfaces.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class LoginController extends WindowSetter implements Connect {
    private static PreparedStatement selectPassHash;
    @FXML private Text actionTarget;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;

    @FXML
    public void handleSignInButtonAction() {
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            selectPassHash = con.prepareStatement("SELECT Password from inventory.user where Email = ?");
            } catch (SQLException exception) {
            actionTarget.setText("Error connecting to database.");
        }

        try {
            selectPassHash.setString(1, emailField.getText());
            ResultSet resultSet = selectPassHash.executeQuery();

            if (resultSet.next()) {
                String userPassHash = DigestUtils.sha256Hex(passwordField.getText());
                if (userPassHash.equalsIgnoreCase(resultSet.getString("Password"))) {
                    setWindow("/fxml/index.fxml", "Inventory Management System");
                    DatabaseAccess.startConnection();
                }
                else actionTarget.setText("Invalid password");
            } else
                actionTarget.setText("Invalid email");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        emailField.clear();
        passwordField.clear();
    }

    public void handleForgotPassword() {

    }

    public void handleSignUpButton() {
        setWindow("/fxml/signUp.fxml", "Sign Up");
    }

    public void handleEnterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) handleSignInButtonAction();

    }
}
