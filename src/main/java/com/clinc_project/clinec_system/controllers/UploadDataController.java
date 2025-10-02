package com.clinc_project.clinec_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;

public class UploadDataController {

    @FXML private Button selectFileBtn;
    @FXML private Button uploadBtn;
    @FXML private ComboBox<String> dataTypeComboBox;
    @FXML private Label filePathLabel;
    @FXML private Label statusLabel;

    private File selectedFile;

    @FXML
    public void initialize() {
        setupDataTypes();
        selectFileBtn.setOnAction(e -> handleFileSelection());
        uploadBtn.setOnAction(e -> handleUpload());
    }

    private void setupDataTypes() {
        dataTypeComboBox.getItems().addAll(
                "Customer Data",
                "Product Data",
                "Sales Data",
                "Inventory Data",
                "Transaction Data"
        );
        dataTypeComboBox.setValue("Customer Data");
    }

    private void handleFileSelection() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Excel File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xls")
        );

        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
        }
    }

    private void handleUpload() {
        if (selectedFile == null) {
            statusLabel.setText("Please select a file first");
            return;
        }

        String dataType = dataTypeComboBox.getValue();
        // Process and upload file
        processExcelFile(selectedFile, dataType);
        statusLabel.setText("Upload successful!");
    }

    private void processExcelFile(File file, String dataType) {
        // Implement Excel processing logic
    }
}