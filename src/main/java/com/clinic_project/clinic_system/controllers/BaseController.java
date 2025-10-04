package com.clinic_project.clinic_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import com.clinic_project.clinic_system.utils.SceneManager;

public abstract class BaseController {

    @FXML protected VBox sidebarMenu;
    @FXML protected Button analyticsBtn;
    @FXML protected Button uploadDataBtn;
    @FXML protected Button customerDataBtn;
    @FXML protected Button sendSMSBtn;
    @FXML protected Button sendWhatsAppBtn;
    @FXML protected Button logoutBtn;

    @FXML
    public void initialize() {
        setupSidebar();
        initializeContent(); // Let child classes implement this
    }

    protected void setupSidebar() {
        // Only setup buttons that exist in the FXML
        if (analyticsBtn != null) {
            analyticsBtn.setOnAction(e -> navigateTo("Dashboard"));
        }
        if (uploadDataBtn != null) {
            uploadDataBtn.setOnAction(e -> navigateTo("UploadData"));
        }
        if (customerDataBtn != null) {
            customerDataBtn.setOnAction(e -> navigateTo("CustomerData"));
        }
        if (sendSMSBtn != null) {
            sendSMSBtn.setOnAction(e -> navigateTo("SendSMS"));
        }
        if (sendWhatsAppBtn != null) {
            sendWhatsAppBtn.setOnAction(e -> navigateTo("SendWhatsApp"));
        }
        if (logoutBtn != null) {
            logoutBtn.setOnAction(e -> handleLogout());
        }

        // Highlight current page
        highlightCurrentPage();
    }

    protected void navigateTo(String sceneName) {
        SceneManager.getInstance().switchScene(sceneName);
    }

    protected void handleLogout() {
        SceneManager.getInstance().switchScene("Login");
    }

    protected void highlightCurrentPage() {
        // Override this in child classes to highlight the active button
    }

    // Helper method to set button highlight style
    protected void setActiveStyle(Button button) {
        if (button != null) {
            button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 15; -fx-background-radius: 8;");
        }
    }

    // Helper method to reset all buttons to inactive style
    protected void resetAllButtons() {
        String inactiveStyle = "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 15; -fx-background-radius: 8; -fx-cursor: hand;";

        if (analyticsBtn != null) analyticsBtn.setStyle(inactiveStyle);
        if (uploadDataBtn != null) uploadDataBtn.setStyle(inactiveStyle);
        if (customerDataBtn != null) customerDataBtn.setStyle(inactiveStyle);
        if (sendSMSBtn != null) sendSMSBtn.setStyle(inactiveStyle);
        if (sendWhatsAppBtn != null) sendWhatsAppBtn.setStyle(inactiveStyle);
    }

    // Abstract method for child classes to implement their specific initialization
    protected abstract void initializeContent();
}