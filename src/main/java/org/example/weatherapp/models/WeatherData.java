package org.example.weatherapp.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherData {
    private String city;
    private String country;
    private String localTime;
    private double currentTemperature;
    private Boolean isDay;
    private String weatherCondition;
    private String visibility;
    private double windSpeed;
    private double pressure;
    private double feelsLike;
    private double dewPoint;
    private double uv;
    private String sunriseTime;
    private String sunsetTime;
    private String moonriseTime;
    private String moonsetTime;
    private List<DailyForecast> dailyForecasts;
    private List<HourlyForecast> hourlyForecasts;
}
