package org.example.weatherapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyForecast {
    private String condition;
    private Double maxTemp;
    private Double minTemp;
    private boolean isDay;
    private String date;
}
