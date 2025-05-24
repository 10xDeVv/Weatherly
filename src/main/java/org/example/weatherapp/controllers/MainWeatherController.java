package org.example.weatherapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import lombok.Setter;
import org.example.weatherapp.models.HourlyForecast;
import org.example.weatherapp.models.WeatherData;
import org.example.weatherapp.service.ApiService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Setter
public class MainWeatherController implements Initializable {
    @FXML
    private VBox sevenDayForecastList;

    @FXML
    private HBox hourlyForecastContainer;

    @FXML
    private Label temperature;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label cityNameLabel;

    @FXML
    private Label localTimeLabel;

    @FXML
    private Label feelsLikeLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label visibilityLabel;

    @FXML
    private Label uvIndexLabel;

    @FXML
    private Label dewPointLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label sunriseLabel;

    @FXML
    private Label sunsetLabel;

    @FXML
    private Label moonriseLabel;

    @FXML
    private Label moonsetLabel;

    private ApiService service = new ApiService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 7; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/DailyForecastCard.fxml"));
                HBox card = loader.load();
                DailyForecastController controller = loader.getController();
                controller.setInfo("36/22", "Sunny", "Today", "thunderstorm.png");
                sevenDayForecastList.getChildren().add(card);
                if (i < 6) {
                    Line line = new Line();
                    line.setStroke(Color.WHITE);
                    line.setOpacity(0.3);
                    line.setStartX(0);
                    line.setEndX(450);
                    line.setStrokeWidth(1);
                    sevenDayForecastList.getChildren().add(line);
                }

                if (i < 6) {
                    FXMLLoader loaderHourly = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/HourlyForecastCard.fxml"));
                    VBox hourlyCard = loaderHourly.load();
                    HourlyForecastController controllerHourly = loaderHourly.getController();
                    controllerHourly.setInfo("12:00", "light_rain.png", "20");
                    hourlyForecastContainer.getChildren().add(hourlyCard);
                    if (i != 5) {
                        createLine();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void searchWeather(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            String input = searchTextField.getText();
            WeatherData data = service.getWeather(input);
            displayWeather(data);
        }
    }

    private void displayWeather(WeatherData weatherData) throws IOException {
        cityNameLabel.setText(weatherData.getCity());
        localTimeLabel.setText(weatherData.getLocalTime());
        feelsLikeLabel.setText(String.valueOf(weatherData.getFeelsLike()));
        temperature.setText(String.valueOf(weatherData.getCurrentTemperature()));
        windLabel.setText(String.valueOf(weatherData.getWindSpeed()));
        visibilityLabel.setText(weatherData.getVisibility());
        uvIndexLabel.setText(String.valueOf(weatherData.getUv()));
        dewPointLabel.setText(String.valueOf(weatherData.getDewPoint()));
        pressureLabel.setText(String.valueOf(weatherData.getPressure()));
        sunriseLabel.setText(weatherData.getSunriseTime());
        sunsetLabel.setText(weatherData.getSunsetTime());
        moonriseLabel.setText(weatherData.getMoonriseTime());
        moonsetLabel.setText(weatherData.getMoonsetTime());

        List<HourlyForecast> hourlyForecasts = weatherData.getHourlyForecasts();
        System.out.println(hourlyForecasts.size());

        int index = 0;
        hourlyForecastContainer.getChildren().clear();
        for (HourlyForecast fc : hourlyForecasts) {
            FXMLLoader loaderHourly = new FXMLLoader(getClass().getResource("/org/example/weatherapp/fxml/HourlyForecastCard.fxml"));
            VBox hourlyCard = loaderHourly.load();
            HourlyForecastController controllerHourly = loaderHourly.getController();
            controllerHourly.setInfo(fc.getTime(), "light_rain.png", String.valueOf(fc.getTemp()));
            hourlyForecastContainer.getChildren().add(hourlyCard);
            if (index++ < hourlyForecasts.size() - 1) {
                createLine();
            }
        }
    }

    private void createLine() {
        Line line = new Line();
        line.setStroke(Color.WHITE);
        line.setStartY(0);
        line.setEndY(170);
        line.setOpacity(0.3);
        line.setStrokeWidth(1);
        hourlyForecastContainer.getChildren().add(line);
    }
}
