package com.clinic_project.clinic_system.presentation_layer.controllers.customer;

import com.clinic_project.clinic_system.presentation_layer.controllers.general.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerDataController extends BaseController {

    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> messageTypeComboBox;
    @FXML private ComboBox<String> customerTypeComboBox;
    @FXML private ComboBox<String> countryComboBox;
    @FXML private ComboBox<String> languageComboBox;
    @FXML private ComboBox<String> deliveryMethodComboBox;
    @FXML private TextArea messageTemplateArea;
    @FXML private Button addCustomerBtn;
    @FXML private Button clearBtn;
    @FXML private TableView customerTable;
    @FXML private TextField searchField;
    @FXML private Label statusLabel;

    @Override
    protected void initializeContent() {
        setupDropdowns();
        if (addCustomerBtn != null) addCustomerBtn.setOnAction(e -> handleAddCustomer());
        if (clearBtn != null) clearBtn.setOnAction(e -> handleClearForm());
    }

    @Override
    protected void highlightCurrentPage() {
        resetAllButtons();
        setActiveStyle(customerDataBtn);
    }

    private void setupDropdowns() {
        if (messageTypeComboBox != null) {
            messageTypeComboBox.getItems().addAll(
                    "Welcome Message", "Promotional Offer", "Order Confirmation",
                    "Reminder", "Custom Message"
            );
            messageTypeComboBox.setValue("Welcome Message");
            messageTypeComboBox.setOnAction(e -> updateMessageTemplate());
        }

        if (customerTypeComboBox != null) {
            customerTypeComboBox.getItems().addAll("Regular", "VIP", "New", "Inactive");
        }

        if (countryComboBox != null) {
            countryComboBox.getItems().addAll(
                    "United States", "United Kingdom", "Canada", "Australia", "Egypt"
            );
        }

        if (languageComboBox != null) {
            languageComboBox.getItems().addAll("English", "Arabic", "Spanish", "French");
        }

        if (deliveryMethodComboBox != null) {
            deliveryMethodComboBox.getItems().addAll("SMS", "WhatsApp", "Email", "All Channels");
        }
    }

    private void updateMessageTemplate() {
        if (messageTypeComboBox != null && messageTemplateArea != null) {
            String type = messageTypeComboBox.getValue();
            messageTemplateArea.setText("Template for " + type);
        }
    }

    private void handleAddCustomer() {
        String name = nameField != null ? nameField.getText() : "";
        String phone = phoneField != null ? phoneField.getText() : "";

        if (name.isEmpty() || phone.isEmpty()) {
            if (statusLabel != null) {
                statusLabel.setText("Name and phone are required");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
            }
            return;
        }

        if (statusLabel != null) {
            statusLabel.setText("Customer added successfully!");
            statusLabel.setStyle("-fx-text-fill: #27ae60;");
        }
        handleClearForm();
    }

    private void handleClearForm() {
        if (nameField != null) nameField.clear();
        if (phoneField != null) phoneField.clear();
        if (emailField != null) emailField.clear();
        if (messageTemplateArea != null) messageTemplateArea.clear();
    }
}