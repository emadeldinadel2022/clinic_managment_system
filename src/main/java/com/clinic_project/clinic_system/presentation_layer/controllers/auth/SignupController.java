package com.clinic_project.clinic_system.presentation_layer.controllers.auth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.clinic_project.clinic_system.presentation_layer.utils.SceneManager;
import com.clinic_project.clinic_system.business_layer.services.AuthService;
import com.clinic_project.clinic_system.business_layer.models.User;

public class SignupController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signupButton;
    @FXML private Button backToLoginButton;
    @FXML private Label errorLabel;

    private AuthService authService;

    @FXML
    public void initialize() {
        authService = new AuthService();

        // Set up button actions
        signupButton.setOnAction(e -> handleSignup());
        backToLoginButton.setOnAction(e -> handleBackToLogin());

        // Add real-time validation listeners
        setupValidation();
    }

    private void setupValidation() {
        // Real-time password matching validation
        confirmPasswordField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && !newVal.equals(passwordField.getText())) {
                errorLabel.setText("Passwords do not match");
                errorLabel.setStyle("-fx-text-fill: #e74c3c;");
            } else {
                errorLabel.setText("");
            }
        });

        // Email format validation
        emailField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused && !emailField.getText().isEmpty()) {
                if (!isValidEmail(emailField.getText())) {
                    errorLabel.setText("Invalid email format");
                    errorLabel.setStyle("-fx-text-fill: #e74c3c;");
                }
            }
        });
    }

    private void handleSignup() {
        // Clear previous error messages
        errorLabel.setText("");

        // Get input values
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validation
        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
            showError("All fields are required");
            return;
        }

        if (!isValidEmail(email)) {
            showError("Please enter a valid email address");
            return;
        }

        if (username.length() < 4) {
            showError("Username must be at least 4 characters long");
            return;
        }

        if (password.length() < 8) {
            showError("Password must be at least 8 characters long");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match");
            return;
        }

        if (!isPasswordStrong(password)) {
            showError("Password must contain uppercase, lowercase, number and special character");
            return;
        }

        // Create new user
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Attempt to register user
        try {
            boolean success = authService.registerUser(newUser);

            if (success) {
                showSuccess("Account created successfully! Redirecting to login...");

                // Delay before redirecting to login
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        javafx.application.Platform.runLater(() -> {
                            SceneManager.getInstance().switchScene("Login");
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                showError("Username or email already exists");
            }
        } catch (Exception e) {
            showError("Registration failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleBackToLogin() {
        SceneManager.getInstance().switchScene("Login");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isPasswordStrong(String password) {
        // Check for uppercase, lowercase, digit, and special character
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        return hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }

    private void showSuccess(String message) {
        errorLabel.setText(message);
        errorLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }
}