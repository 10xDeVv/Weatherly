module org.example.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.graphics;


    opens org.example.weatherapp to javafx.fxml;
    exports org.example.weatherapp;
    exports org.example.weatherapp.controllers;
    opens org.example.weatherapp.controllers to javafx.fxml;
}