package org.example.weatherapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HourlyForecast {
    private String time;
    private double temp;
    private String condition;
    private boolean isDay;
}
