module com.clinic_project.clinic_system {
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

    exports com.clinic_project.clinic_system;

    // customer controllers
    exports com.clinic_project.clinic_system.presentation_layer.controllers.customer;
    opens com.clinic_project.clinic_system.presentation_layer.controllers.customer to javafx.fxml;

    // auth controllers
    exports com.clinic_project.clinic_system.presentation_layer.controllers.auth;
    opens com.clinic_project.clinic_system.presentation_layer.controllers.auth to javafx.fxml;

    // general controllers
    exports com.clinic_project.clinic_system.presentation_layer.controllers.general;
    opens com.clinic_project.clinic_system.presentation_layer.controllers.general to javafx.fxml;

    // messaging controllers
    exports com.clinic_project.clinic_system.presentation_layer.controllers.messaging;
    opens com.clinic_project.clinic_system.presentation_layer.controllers.messaging to javafx.fxml;
}
