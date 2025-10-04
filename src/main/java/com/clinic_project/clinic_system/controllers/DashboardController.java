package com.clinic_project.clinic_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController extends BaseController {

    @FXML private Label totalCustomersLabel;
    @FXML private Label totalMessagesLabel;
    @FXML private Label successRateLabel;
    @FXML private Label pendingTasksLabel;
    @FXML private Label dateTimeLabel;
    @FXML private LineChart activityChart;
    @FXML private PieChart distributionChart;
    @FXML private TableView recentActivityTable;

    @Override
    protected void initializeContent() {
        if (dateTimeLabel != null) {
            updateDateTime();
        }
        loadAnalyticsDashboard();
    }

    @Override
    protected void highlightCurrentPage() {
        resetAllButtons();
        setActiveStyle(analyticsBtn);
    }

    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy");
        dateTimeLabel.setText(formatter.format(now));
    }

    private void loadAnalyticsDashboard() {
        if (totalCustomersLabel != null) totalCustomersLabel.setText("1,234");
        if (totalMessagesLabel != null) totalMessagesLabel.setText("5,678");
        if (successRateLabel != null) successRateLabel.setText("94.5%");
        if (pendingTasksLabel != null) pendingTasksLabel.setText("23");
    }
}
