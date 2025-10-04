package com.clinic_project.clinic_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.clinic_project.clinic_system.utils.SceneManager;
import com.clinic_project.clinic_system.services.AuthService;

public class ResetPasswordController {

    @FXML private TextField emailField;
    @FXML private Button resetButton;
    @FXML private Button backToLoginButton;
    @FXML private Label statusLabel;

    private AuthService authService;

    @FXML
    public void initialize() {
        authService = new AuthService();

        resetButton.setOnAction(e -> handlePasswordReset());
        backToLoginButton.setOnAction(e -> handleBackToLogin());

        // Clear status label initially
        statusLabel.setText("");
    }

    private void handlePasswordReset() {
        String email = emailField.getText().trim();

        // Validation
        if (email.isEmpty()) {
            showError("Please enter your email address");
            return;
        }

        if (!isValidEmail(email)) {
            showError("Please enter a valid email address");
            return;
        }

        // Disable button to prevent multiple clicks
        resetButton.setDisable(true);
        resetButton.setText("Sending...");

        // Attempt to send reset link
        try {
            boolean success = authService.sendPasswordResetEmail(email);

            if (success) {
                showSuccess("Password reset link sent to " + email +
                        "\nPlease check your email inbox and spam folder.");

                // Clear the email field
                emailField.clear();

                // Optionally redirect to login after a delay
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                        javafx.application.Platform.runLater(() -> {
                            SceneManager.getInstance().switchScene("Login");
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            } else {
                showError("No account found with this email address");
            }

        } catch (Exception e) {
            showError("Failed to send reset link: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Re-enable button
            resetButton.setDisable(false);
            resetButton.setText("Send Reset Link");
        }
    }

    private void handleBackToLogin() {
        SceneManager.getInstance().switchScene("Login");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }

    private void showSuccess(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }
}