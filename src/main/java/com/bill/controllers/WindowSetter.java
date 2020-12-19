package com.bill.controllers;

import com.bill.DatabaseAccess;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowSetter extends Application {
    @FXML
    Text actionTarget;
    static Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        DatabaseAccess a = new DatabaseAccess();
        setWindow("/fxml/login.fxml", "Inventory Management System");
    }


    public void setWindow(String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, 400, 300);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            actionTarget.setText("Error opening window");
        }
    }

    public void setWindow(int w, int h, String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, w, h);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            actionTarget.setText("Error opening window");
        }
    }
}
