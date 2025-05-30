package org.example.weatherapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class DailyForecastController {
    @FXML
    public Label temperatureSummaryLabel;

    @FXML
    public ImageView weatherStatusImage;

    @FXML
    public Label weatherStatusLabel;

    @FXML
    public Label currentDay;

    public void setInfo(String temperatureSummary, String weatherStatus, String currentDate, String imageName) {
        this.temperatureSummaryLabel.setText(temperatureSummary);
        this.weatherStatusLabel.setText(weatherStatus);
        this.currentDay.setText(currentDate);
        String fullPath = "/org/example/weatherapp/icons/big/" + imageName;
        URL imageUrl = getClass().getResource(fullPath);

        if (imageUrl == null) {
            System.err.println("❌ Image not found at: " + fullPath);
        } else {
            System.out.println("✅ Image found: " + imageUrl.toExternalForm());

        }
        weatherStatusImage.setImage(new Image(getClass().getResource("/org/example/weatherapp/icons/small/" + imageName).toExternalForm()));
    }
}
