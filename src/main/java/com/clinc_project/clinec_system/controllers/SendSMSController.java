package com.clinc_project.clinec_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SendSMSController {

    @FXML private ComboBox<String> recipientTypeComboBox;
    @FXML private TextArea recipientListArea;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<String> templateComboBox;
    @FXML private Button sendBtn;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        setupRecipientTypes();
        setupTemplates();
        sendBtn.setOnAction(e -> handleSendSMS());
    }

    private void setupRecipientTypes() {
        recipientTypeComboBox.getItems().addAll(
                "All Customers",
                "Active Customers",
                "Inactive Customers",
                "VIP Customers",
                "Custom List"
        );
        recipientTypeComboBox.setValue("All Customers");
    }

    private void setupTemplates() {
        templateComboBox.getItems().addAll(
                "Promotional",
                "Notification",
                "Reminder",
                "Alert",
                "Custom"
        );
        templateComboBox.setOnAction(e -> loadTemplate());
    }

    private void loadTemplate() {
        String template = templateComboBox.getValue();
        // Load SMS template
    }

    private void handleSendSMS() {
        String message = messageArea.getText();
        String recipientType = recipientTypeComboBox.getValue();

        // Send SMS to selected recipients
        statusLabel.setText("Sending SMS...");
        // Implement SMS sending logic
        statusLabel.setText("SMS sent successfully!");
    }
}
