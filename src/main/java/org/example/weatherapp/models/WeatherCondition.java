package org.example.weatherapp.models;

import lombok.Getter;

@Getter
public enum WeatherCondition {
    HAIL("Hail", "hail.png"),
    HAZE("Haze", "haze.png"),
    HEAVY_RAIN("Heavy Rain", "heavy_rain.png"),
    HEAVY_RAINSTORM("Heavy Rainstorm", "heavy_rainstorm.png"),
    HEAVY_SNOW("Heavy Snow", "heavy_snow.png"),
    HEAVY_SNOWSTORM("Heavy Snowstorm", "heavy_snowstorm.png"),
    LIGHT_RAIN("Light Rain", "light_rain.png"),
    LIGHT_SNOW("Light Snow", "light_snow.png"),
    LIGHTNING("Lightning", "lightning.png"),
    MIST("Mist", "mist.png"),
    MODERATE_RAIN("Moderate Rain", "moderate_rain.png"),
    MODERATE_SNOW("Moderate Snow", "moderate_snow.png"),
    MOON("Moon", "moon.png"),
    MOON_CLOUDY("Moon Cloudy", "moon_cloudy.png"),
    FULL_MOON("Full Moon", "full_moon.png"),
    PARTLY_CLOUDY_DAYTIME("Partly Cloudy Daytime", "partly_cloudy_daytime.png"),
    PARTLY_CLOUDY_NIGHT("Partly Cloudy Night", "partly_cloudy_night.png"),
    OVERCAST("Overcast", "cloudy.png"),
    SNOW("Snow", "snow.png"),
    SNOWSTORM("Snowstorm", "snowstorm.png"),
    SUN_CLOUDY("Sun Cloudy", "sun_cloudy.png"),
    SUNNY("Sunny", "sunny.png"),
    SUNRISE("Sunrise", "sunrise.png"),
    SUNSET("Sunset", "sunset.png"),
    THUNDERSTORM("Thunderstorm", "thunderstorm.png"),
    WET("Wet", "wet.png"),
    WINDY("Windy", "windy.png");

    private final String label;
    private final String icon;

    WeatherCondition(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }

    public static WeatherCondition getIconFromCondition(String apiCondition,  Boolean isDay) {
        String condition = apiCondition.toLowerCase();

        if (condition.contains("thunder")) return THUNDERSTORM;
        if (condition.contains("hail")) return HAIL;
        if (condition.contains("lightning")) return LIGHTNING;

        if (condition.contains("heavy rainstorm")) return HEAVY_RAINSTORM;
        if (condition.contains("heavy rain")) return HEAVY_RAIN;
        if (condition.contains("moderate rain")) return MODERATE_RAIN;
        if (condition.contains("light rain") || condition.contains("drizzle") || condition.contains("patchy rain")) return LIGHT_RAIN;
        if (condition.contains("rain")) return HEAVY_RAIN;

        if (condition.contains("heavy snowstorm")) return HEAVY_SNOWSTORM;
        if (condition.contains("heavy snow")) return HEAVY_SNOW;
        if (condition.contains("moderate snow")) return MODERATE_SNOW;
        if (condition.contains("light snow") || condition.contains("patchy snow")) return LIGHT_SNOW;
        if (condition.contains("snowstorm")) return SNOWSTORM;
        if (condition.contains("snow")) return SNOW;

        if (condition.contains("fog")) return MIST;
        if (condition.contains("mist")) return MIST;
        if (condition.contains("haze")) return HAZE;

        if (condition.contains("overcast")) return OVERCAST;

        if (condition.contains("partly cloudy")) {
            return isDay ? PARTLY_CLOUDY_DAYTIME : PARTLY_CLOUDY_NIGHT ;
        }

        if (condition.contains("cloudy")) {
            return isDay ? SUN_CLOUDY : MOON_CLOUDY;
        }

        if (condition.contains("clear") || condition.contains("sunny")) {
            return isDay ? SUNNY : MOON;
        }

        if (condition.contains("moon")) return FULL_MOON;

        if (condition.contains("wind")) return WINDY;
        if (condition.contains("wet")) return WET;

        return isDay ? SUNNY : MOON;
    }
}