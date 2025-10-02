package com.clinc_project.clinec_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.clinc_project.clinec_system.models.Customer;

public class CustomerDataController {

    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> messageTypeComboBox;
    @FXML private TextArea messageTemplateArea;
    @FXML private Button addCustomerBtn;
    @FXML private TableView<Customer> customerTable;

    @FXML
    public void initialize() {
        setupMessageTypes();
        setupTable();
        addCustomerBtn.setOnAction(e -> handleAddCustomer());
    }

    private void setupMessageTypes() {
        messageTypeComboBox.getItems().addAll(
                "Welcome Message",
                "Promotional Offer",
                "Order Confirmation",
                "Reminder",
                "Custom Message"
        );
        messageTypeComboBox.setValue("Welcome Message");
        messageTypeComboBox.setOnAction(e -> updateMessageTemplate());
    }

    private void setupTable() {
        // Setup table columns
    }

    private void updateMessageTemplate() {
        String type = messageTypeComboBox.getValue();
        // Load appropriate message template
        messageTemplateArea.setText(getTemplate(type));
    }

    private String getTemplate(String type) {
        // Return message template based on type
        return "Default template for " + type;
    }

    private void handleAddCustomer() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        // Add customer to database and table
        // Send message if needed
    }
}
