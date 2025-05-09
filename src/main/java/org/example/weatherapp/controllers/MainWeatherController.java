package org.example.weatherapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWeatherController implements Initializable {
    @FXML
    public VBox sevenDayForecastList;

    @FXML
    public HBox hourlyForecastContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 7; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/DailyForecastCard.fxml"));
                HBox card = loader.load();
                DailyForecastController controller = loader.getController();
                controller.setInfo("Temperature Summary", "Weather Status", "Today", "thunderstorm.png");
                sevenDayForecastList.getChildren().add(card);
                if (i < 6){
                    Separator sep = new Separator();
                    sep.setOrientation(Orientation.HORIZONTAL);
                    sevenDayForecastList.getChildren().add(sep);
                }

                if (i < 6){
                    FXMLLoader loaderHourly = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/HourlyForecastCard.fxml"));
                    VBox hourlyCard = loaderHourly.load();
                    HourlyForecastController controllerHourly = loaderHourly.getController();
                    controllerHourly.setInfo("12:00", "light_rain.png", "20");
                    hourlyForecastContainer.getChildren().add(hourlyCard);
                    Separator sep = new Separator();
                    sep.setOrientation(Orientation.VERTICAL);
                    hourlyForecastContainer.getChildren().add(sep);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}