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

    opens com.clinc_project.clinec_system.controllers to javafx.fxml;
    exports com.clinc_project.clinec_system;
    exports com.clinc_project.clinec_system.controllers;
}