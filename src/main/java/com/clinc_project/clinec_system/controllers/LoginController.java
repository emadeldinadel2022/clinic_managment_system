package com.clinc_project.clinec_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.clinc_project.clinec_system.utils.SceneManager;

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

        // Add authentication logic here
        if (authenticate(username, password)) {
            SceneManager.getInstance().switchScene("Dashboard");
        } else {
            errorLabel.setText("Invalid credentials");
        }
    }

    private void handleSignup() {
        SceneManager.getInstance().switchScene("Signup");
    }

    private void handleResetPassword() {
        SceneManager.getInstance().switchScene("ResetPassword");
    }

    private boolean authenticate(String username, String password) {
        // Implement authentication logic
        return true; // Placeholder
    }
}