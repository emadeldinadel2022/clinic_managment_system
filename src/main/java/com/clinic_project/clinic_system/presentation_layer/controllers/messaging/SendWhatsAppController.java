package com.clinic_project.clinic_system.presentation_layer.controllers.messaging;

import com.clinic_project.clinic_system.presentation_layer.controllers.general.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;

public class SendWhatsAppController extends BaseController {

    @FXML private ComboBox<String> recipientTypeComboBox;
    @FXML private ComboBox<String> segmentFilterComboBox;
    @FXML private TextArea recipientListArea;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<String> templateComboBox;
    @FXML private CheckBox includeMediaCheckBox;
    @FXML private ComboBox<String> mediaTypeComboBox;
    @FXML private Button selectMediaBtn;
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
    @FXML private ComboBox<String> timezoneComboBox;
    @FXML private TableView whatsappHistoryTable;

    private File selectedMediaFile;

    @Override
    protected void initializeContent() {
        setupRecipientTypes();
        setupTemplates();
        setupMediaOptions();
        setupMessageTracking();
        if (sendBtn != null) sendBtn.setOnAction(e -> handleSendWhatsApp());
    }

    @Override
    protected void highlightCurrentPage() {
        resetAllButtons();
        setActiveStyle(sendWhatsAppBtn);
    }

    private void setupRecipientTypes() {
        if (recipientTypeComboBox != null) {
            recipientTypeComboBox.getItems().addAll(
                    "All Customers", "Active Customers", "Segmented Group", "Custom List"
            );
            recipientTypeComboBox.setValue("All Customers");
        }

        if (segmentFilterComboBox != null) {
            segmentFilterComboBox.getItems().addAll(
                    "All Segments", "High Value", "Recent Buyers", "Inactive"
            );
            segmentFilterComboBox.setValue("All Segments");
        }
    }

    private void setupTemplates() {
        if (templateComboBox != null) {
            templateComboBox.getItems().addAll(
                    "Welcome Message", "Order Update", "Promotional",
                    "Support Message", "Custom"
            );
            templateComboBox.setOnAction(e -> loadTemplate());
        }
    }

    private void setupMediaOptions() {
        if (mediaTypeComboBox != null) {
            mediaTypeComboBox.getItems().addAll(
                    "Image (JPG, PNG)", "Video (MP4)", "Document (PDF)", "Audio (MP3)"
            );
        }

        if (includeMediaCheckBox != null) {
            includeMediaCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (mediaTypeComboBox != null) mediaTypeComboBox.setDisable(!newVal);
                if (selectMediaBtn != null) selectMediaBtn.setDisable(!newVal);
            });
        }

        if (selectMediaBtn != null) {
            selectMediaBtn.setOnAction(e -> handleMediaSelection());
        }
    }

    private void setupMessageTracking() {
        if (messageArea != null) {
            messageArea.textProperty().addListener((obs, oldVal, newVal) -> {
                if (charCountLabel != null) {
                    charCountLabel.setText(newVal.length() + "/4096 characters");
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
                messageArea.setText("Sample " + template + " template for WhatsApp");
            }
        }
    }

    private void handleMediaSelection() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Media File");

        selectedMediaFile = fileChooser.showOpenDialog(null);
        if (selectedMediaFile != null && statusLabel != null) {
            statusLabel.setText("Media file selected: " + selectedMediaFile.getName());
            statusLabel.setStyle("-fx-text-fill: #27ae60;");
        }
    }

    private void handleSendWhatsApp() {
        String message = messageArea != null ? messageArea.getText() : "";

        if (message.isEmpty()) {
            if (statusLabel != null) {
                statusLabel.setText("Please enter a message");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
            }
            return;
        }

        if (statusLabel != null) {
            statusLabel.setText("Sending WhatsApp messages...");
            statusLabel.setStyle("-fx-text-fill: #25D366;");
        }

        if (progressBar != null) {
            progressBar.setVisible(true);
            progressBar.setProgress(0.5);
        }

        // Simulate completion
        if (statusLabel != null) {
            statusLabel.setText("WhatsApp messages sent successfully!");
            statusLabel.setStyle("-fx-text-fill: #27ae60;");
        }
    }
}