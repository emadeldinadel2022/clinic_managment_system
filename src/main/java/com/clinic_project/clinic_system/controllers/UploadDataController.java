package com.clinic_project.clinic_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;

public class UploadDataController extends BaseController {

    @FXML private Button selectFileBtn;
    @FXML private Button uploadBtn;
    @FXML private ComboBox<String> dataTypeComboBox;
    @FXML private Label filePathLabel;
    @FXML private Label statusLabel;
    @FXML private TableView uploadHistoryTable;

    private File selectedFile;

    @Override
    protected void initializeContent() {
        setupDataTypes();
        if (selectFileBtn != null) selectFileBtn.setOnAction(e -> handleFileSelection());
        if (uploadBtn != null) uploadBtn.setOnAction(e -> handleUpload());
    }

    @Override
    protected void highlightCurrentPage() {
        resetAllButtons();
        setActiveStyle(uploadDataBtn);
    }

    private void setupDataTypes() {
        if (dataTypeComboBox != null) {
            dataTypeComboBox.getItems().addAll(
                    "Customer Data",
                    "Product Data",
                    "Sales Data",
                    "Inventory Data",
                    "Transaction Data"
            );
            dataTypeComboBox.setValue("Customer Data");
        }
    }

    private void handleFileSelection() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Excel File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xls")
        );

        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null && filePathLabel != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
        }
    }

    private void handleUpload() {
        if (selectedFile == null) {
            if (statusLabel != null) {
                statusLabel.setText("Please select a file first");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
            }
            return;
        }

        String dataType = dataTypeComboBox != null ? dataTypeComboBox.getValue() : "Unknown";
        // Process and upload file
        if (statusLabel != null) {
            statusLabel.setText("Upload successful!");
            statusLabel.setStyle("-fx-text-fill: #27ae60;");
        }
    }
}
