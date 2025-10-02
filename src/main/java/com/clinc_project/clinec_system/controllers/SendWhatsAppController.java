package com.clinc_project.clinec_system.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SendWhatsAppController {

    @FXML private ComboBox<String> recipientTypeComboBox;
    @FXML private TextArea recipientListArea;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<String> templateComboBox;
    @FXML private CheckBox includeMediaCheckBox;
    @FXML private Button selectMediaBtn;
    @FXML private Button sendBtn;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        setupRecipientTypes();
        setupTemplates();
        sendBtn.setOnAction(e -> handleSendWhatsApp());
        selectMediaBtn.setOnAction(e -> handleMediaSelection());
    }

    private void setupRecipientTypes() {
        recipientTypeComboBox.getItems().addAll(
                "All Customers",
                "Active Customers",
                "Segmented Group",
                "Custom List"
        );
        recipientTypeComboBox.setValue("All Customers");
    }

    private void setupTemplates() {
        templateComboBox.getItems().addAll(
                "Welcome Message",
                "Order Update",
                "Promotional",
                "Support Message",
                "Custom"
        );
        templateComboBox.setOnAction(e -> loadTemplate());
    }

    private void loadTemplate() {
        String template = templateComboBox.getValue();
        // Load WhatsApp template
    }

    private void handleMediaSelection() {
        // Open file chooser for media
    }

    private void handleSendWhatsApp() {
        String message = messageArea.getText();
        String recipientType = recipientTypeComboBox.getValue();

        // Send WhatsApp message to selected recipients
        statusLabel.setText("Sending WhatsApp messages...");
        // Implement WhatsApp API integration
        statusLabel.setText("Messages sent successfully!");
    }
}
