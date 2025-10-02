package com.clinc_project.clinec_system.controllers;


import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import com.clinc_project.clinec_system.utils.SceneManager;

public class DashboardController {

    @FXML private BorderPane mainContainer;
    @FXML private VBox sidebarMenu;
    @FXML private StackPane contentArea;

    // Sidebar buttons
    @FXML private Button analyticsBtn;
    @FXML private Button uploadDataBtn;
    @FXML private Button customerDataBtn;
    @FXML private Button sendSMSBtn;
    @FXML private Button sendWhatsAppBtn;
    @FXML private Button logoutBtn;

    // Analytics widgets
    @FXML private Label totalCustomersLabel;
    @FXML private Label totalMessagesLabel;
    @FXML private Label successRateLabel;
    @FXML private Label pendingTasksLabel;

    @FXML
    public void initialize() {
        setupSidebarNavigation();
        loadAnalyticsDashboard();
    }

    private void setupSidebarNavigation() {
        analyticsBtn.setOnAction(e -> loadAnalyticsDashboard());
        uploadDataBtn.setOnAction(e -> loadUploadDataScene());
        customerDataBtn.setOnAction(e -> loadCustomerDataScene());
        sendSMSBtn.setOnAction(e -> loadSendSMSScene());
        sendWhatsAppBtn.setOnAction(e -> loadSendWhatsAppScene());
        logoutBtn.setOnAction(e -> handleLogout());
    }

    private void loadAnalyticsDashboard() {
        // Load main analytics dashboard
        updateAnalytics();
    }

    private void updateAnalytics() {
        // Fetch and display analytics data
        totalCustomersLabel.setText("1,234");
        totalMessagesLabel.setText("5,678");
        successRateLabel.setText("94.5%");
        pendingTasksLabel.setText("23");
    }

    private void loadUploadDataScene() {
        loadScene("UploadData");
    }

    private void loadCustomerDataScene() {
        loadScene("CustomerData");
    }

    private void loadSendSMSScene() {
        loadScene("SendSMS");
    }

    private void loadSendWhatsAppScene() {
        loadScene("SendWhatsApp");
    }

    private void loadScene(String sceneName) {
        try {
            // Load scene into content area
            SceneManager.getInstance().switchScene(sceneName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogout() {
        SceneManager.getInstance().switchScene("Login");
    }
}
