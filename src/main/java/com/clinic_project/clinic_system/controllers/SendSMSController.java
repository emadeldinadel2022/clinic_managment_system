package com.clinic_project.clinic_system.controllers;



import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SendSMSController extends BaseController {

    @FXML private ComboBox<String> recipientTypeComboBox;
    @FXML private ComboBox<String> countryFilterComboBox;
    @FXML private TextArea recipientListArea;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<String> templateComboBox;
    @FXML private Button sendBtn;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;
    @FXML private Label charCountLabel;
    @FXML private Label recipientCountLabel;
    @FXML private Label messagePreviewLabel;
    @FXML private RadioButton sendNowRadio;
    @FXML private RadioButton scheduleRadio;
    @FXML private DatePicker scheduleDatePicker;
    @FXML private TextField scheduleTimeField;
    @FXML private TableView smsHistoryTable;

    @Override
    protected void initializeContent() {
        setupRecipientTypes();
        setupTemplates();
        setupMessageTracking();
        if (sendBtn != null) sendBtn.setOnAction(e -> handleSendSMS());
    }

    @Override
    protected void highlightCurrentPage() {
        resetAllButtons();
        setActiveStyle(sendSMSBtn);
    }

    private void setupRecipientTypes() {
        if (recipientTypeComboBox != null) {
            recipientTypeComboBox.getItems().addAll(
                    "All Customers", "Active Customers", "Inactive Customers",
                    "VIP Customers", "Custom List"
            );
            recipientTypeComboBox.setValue("All Customers");
        }

        if (countryFilterComboBox != null) {
            countryFilterComboBox.getItems().addAll(
                    "All Countries", "United States", "United Kingdom", "Canada", "Egypt"
            );
            countryFilterComboBox.setValue("All Countries");
        }
    }

    private void setupTemplates() {
        if (templateComboBox != null) {
            templateComboBox.getItems().addAll(
                    "Promotional", "Notification", "Reminder", "Alert", "Custom"
            );
            templateComboBox.setOnAction(e -> loadTemplate());
        }
    }

    private void setupMessageTracking() {
        if (messageArea != null) {
            messageArea.textProperty().addListener((obs, oldVal, newVal) -> {
                if (charCountLabel != null) {
                    charCountLabel.setText(newVal.length() + "/160 characters");
                }
                if (messagePreviewLabel != null) {
                    messagePreviewLabel.setText(newVal);
                }
            });
        }

        if (recipientListArea != null) {
            recipientListArea.textProperty().addListener((obs, oldVal, newVal) -> {
                if (recipientCountLabel != null) {
                    int count = newVal.isEmpty() ? 0 : newVal.split("\n").length;
                    recipientCountLabel.setText(count + " recipients selected");
                }
            });
        }
    }

    private void loadTemplate() {
        if (templateComboBox != null && messageArea != null) {
            String template = templateComboBox.getValue();
            if (template != null && !template.equals("Custom")) {
                messageArea.setText("Sample " + template + " message template");
            }
        }
    }

    private void handleSendSMS() {
        String message = messageArea != null ? messageArea.getText() : "";

        if (message.isEmpty()) {
            if (statusLabel != null) {
                statusLabel.setText("Please enter a message");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
            }
            return;
        }

        if (statusLabel != null) {
            statusLabel.setText("Sending SMS...");
            statusLabel.setStyle("-fx-text-fill: #3498db;");
        }

        if (progressBar != null) {
            progressBar.setVisible(true);
            progressBar.setProgress(0.5);
        }

        // Simulate completion
        if (statusLabel != null) {
            statusLabel.setText("SMS sent successfully!");
            statusLabel.setStyle("-fx-text-fill: #27ae60;");
        }
    }
}