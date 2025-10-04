module com.clinc_project.clinec_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.clinic_project.clinic_system.controllers to javafx.fxml;
    exports com.clinic_project.clinic_system;
    exports com.clinic_project.clinic_system.controllers;
}