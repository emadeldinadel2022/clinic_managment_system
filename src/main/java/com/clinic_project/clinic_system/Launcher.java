package com.clinic_project.clinic_system;

import javafx.application.Application;
import javafx.stage.Stage;
import com.clinic_project.clinic_system.presentation_layer.utils.SceneManager;

public class Launcher extends Application{

    @Override
    public void start(Stage primaryStage) {
        SceneManager.getInstance().setPrimaryStage(primaryStage);
        SceneManager.getInstance().switchScene("Login");

        primaryStage.setTitle("Clinic Management System");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
