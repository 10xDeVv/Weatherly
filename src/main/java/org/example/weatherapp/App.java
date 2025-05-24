package org.example.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.weatherapp.service.ApiService;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/MainWeatherView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println(System.getenv("API_KEY"));
        stage.setTitle("Weatherly Application");
        stage.getIcons().add(new Image(getClass().getResource("/org/example/weatherapp/icons/weather-icon.png").toExternalForm()));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ApiService service = new ApiService();
        System.setProperty("prism.lcdtext", "false");
       // String body = service.getWeather("SanFrancisco");
       // System.out.println(body);
        launch();
    }
}