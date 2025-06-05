package org.example.weatherapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HourlyForecastController {

    @FXML
    private Label hourlyModalTime;

    @FXML
    private ImageView hourlyModalImage;

    @FXML
    private Label hourlyModalTemperature;

    public void setInfo(String time, String imageName, String temperature) {
        hourlyModalTime.setText(time);
        hourlyModalImage.setImage(new Image(getClass().getResource("/org/example/weatherapp/icons/medium/" + imageName).toExternalForm()));
        hourlyModalTemperature.setText(temperature + "°");
    }
}
