module org.example.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires static lombok;

    opens org.example.weatherapp to javafx.fxml;
    exports org.example.weatherapp;
    exports org.example.weatherapp.controllers;
    exports org.example.weatherapp.service;
    exports org.example.weatherapp.utility;
    exports org.example.weatherapp.models;
    opens org.example.weatherapp.controllers to javafx.fxml;
}