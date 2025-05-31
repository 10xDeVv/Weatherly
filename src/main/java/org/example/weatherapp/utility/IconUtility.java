package org.example.weatherapp.utility;

import org.example.weatherapp.models.WeatherCondition;
import org.example.weatherapp.models.Icon;

public class IconUtility {

    public static Icon getIcon(String condition, Boolean isDay) {
        WeatherCondition con = WeatherCondition.getIconFromCondition(condition, isDay);
        return new Icon(con.getLabel(), con.getIcon());
    }
}
