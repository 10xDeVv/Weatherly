package org.example.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.weatherapp.service.ApiService;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainWeatherView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println(System.getenv("API_KEY"));
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ApiService service = new ApiService();
        System.setProperty("prism.lcdtext", "false");
      //  String body = service.getWeather("SanFrancisco");
        //System.out.println(body);
        launch();
    }
}