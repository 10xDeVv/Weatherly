package org.example.weatherapp.service;

import org.example.weatherapp.client.WeatherApiClient;
import org.example.weatherapp.models.WeatherData;
import org.example.weatherapp.utility.WeatherParser;

import java.io.IOException;

public class ApiService {

    private final WeatherApiClient apiClient = new WeatherApiClient();
    private final WeatherParser parser = new WeatherParser();

    public WeatherData getWeather(String city) throws IOException, InterruptedException {
        String weatherResponse = apiClient.fetchWeatherData(city);
        return parser.parse(weatherResponse);
    }
}
