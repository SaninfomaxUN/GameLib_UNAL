module com.gamelib.gamelib {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires IGDB.API.JVM;
    requires org.apache.commons.lang3;
    requires com.google.gson;

    opens com.gamelib.gamelib to javafx.fxml;
    exports com.gamelib.gamelib;
    exports com.gamelib.controller;
    opens com.gamelib.controller to javafx.fxml;
    opens com.gamelib.Logic.Model to com.google.gson;
}