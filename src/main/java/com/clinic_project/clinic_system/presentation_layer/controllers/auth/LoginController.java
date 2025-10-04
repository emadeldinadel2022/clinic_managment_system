package com.clinic_project.clinic_system.presentation_layer.controllers.auth;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.clinic_project.clinic_system.presentation_layer.utils.SceneManager;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button signupButton;
    @FXML private Button resetPasswordButton;
    @FXML private Label errorLabel;

    @FXML
    public void initialize() {
        loginButton.setOnAction(e -> handleLogin());
        signupButton.setOnAction(e -> handleSignup());
        resetPasswordButton.setOnAction(e -> handleResetPassword());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            SceneManager.getInstance().switchScene("dashboard/Dashboard");
        } else {
            errorLabel.setText("Invalid credentials");
        }
    }

    private void handleSignup() {
        SceneManager.getInstance().switchScene("auth/Signup");
    }

    private void handleResetPassword() {
        SceneManager.getInstance().switchScene("auth/ResetPassword");
    }

    private boolean authenticate(String username, String password) {
        return true;
    }
}