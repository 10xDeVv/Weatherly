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
    private String localTime;
    private Double currentTemperature;
    private Boolean isDay;
    private String weatherCondition;
    private String visibility;
    private Double windSpeed;
    private Double pressure;
    private Double feelsLike;
    private Double dewPoint;
    private Double uv;
    private String sunriseTime;
    private String sunsetTime;
    private String moonriseTime;
    private String moonsetTime;
    private List<DailyForecast> dailyForecasts;
    private List<HourlyForecast> hourlyForecasts;
}
