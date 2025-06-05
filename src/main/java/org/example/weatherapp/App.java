package org.example.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/MainWeatherView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Weatherly Application");
        stage.getIcons().add(new Image(getClass().getResource("/org/example/weatherapp/icons/airconditions/weather-icon.png").toExternalForm()));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        launch();
    }
}