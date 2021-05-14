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
        setWindow("/fxml/login.fxml", "Sign In");
        DatabaseAccess.startConnection();
    }


    public void setWindow(String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, 550, 700);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            actionTarget.setText("Error opening window");
        }
    }

    public void setWindow(int height, int width, String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, width, height);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            actionTarget.setText("Error opening window");
        }
    }

}
