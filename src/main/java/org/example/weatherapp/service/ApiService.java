package org.example.weatherapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.weatherapp.models.DailyForecast;
import org.example.weatherapp.models.HourlyForecast;
import org.example.weatherapp.models.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private final ObjectMapper MAPPER = new ObjectMapper();

    public WeatherData getWeather(String city) throws IOException, InterruptedException {
        String url = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + city + "&days=7"))
                .header("x-rapidapi-key", String.format("%s", System.getenv("API_KEY")))
                .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        return parseResponse(MAPPER.readTree(responseBody));
    }

    private WeatherData parseResponse(JsonNode node){
        JsonNode location = node.get("location");
        JsonNode current = node.get("current");
        JsonNode forecast = node.get("forecast");
        
        String name = location.get("name").asText();
        String localtime = location.get("localtime").asText();
        
        Double temp = current.get("temp_c").asDouble();
        Boolean isDay = current.get("is_day").asInt() == 1;

        String condition = current.get("condition").get("text").asText();
        String visibility = current.get("vis_km").asText();

        Double wind = current.get("wind_kph").asDouble();
        Double pressure = current.get("pressure_mb").asDouble();
        Double feelsLike = current.get("feelslike_c").asDouble();
        Double dewPoint = current.get("dewpoint_c").asDouble();
        Double uv = current.get("uv").asDouble();

        JsonNode dailyForecastArray = forecast.get("forecastday");
        String sunrise = dailyForecastArray.get(0).get("astro").get("sunrise").asText();
        String sunset = dailyForecastArray.get(0).get("astro").get("sunset").asText();
        String moonrise = dailyForecastArray.get(0).get("astro").get("moonrise").asText();
        String moonset = dailyForecastArray.get(0).get("astro").get("moonset").asText();

        JsonNode hourArray = dailyForecastArray.get(0).get("hour");
        List<HourlyForecast> hourlyForecasts = new ArrayList<>();
        int[] times = {6, 9, 12, 15, 18, 21};

        for (int time : times) {
            JsonNode h = hourArray.get(time);
            hourlyForecasts.add(new HourlyForecast(
                    formatTo12HourTime(h.path("time").asText()),
                    h.path("temp_c").asDouble(),
                    h.path("condition").path("text").asText()
            ));
        }

        List<DailyForecast> dailyForecasts = new ArrayList<>();
        for (int i = 1; i < dailyForecastArray.size(); i++) {
            JsonNode day = dailyForecastArray.get(i).get("day");
            Double maxTemp = day.get("maxtemp_c").asDouble();
            Double minTemp = day.get("mintemp_c").asDouble();
            String conditionText = day.get("condition").get("text").asText();
            String date = dailyForecastArray.get(i).get("date").asText();

            DailyForecast dailyForecast = new DailyForecast(date, maxTemp, minTemp, conditionText);
            dailyForecasts.add(dailyForecast);
        }

        return WeatherData.builder()
                .city(name)
                .localTime(formatToDayAndTime(localtime))
                .currentTemperature(temp)
                .isDay(isDay)
                .weatherCondition(condition)
                .windSpeed(wind)
                .pressure(pressure)
                .feelsLike(feelsLike)
                .dewPoint(dewPoint)
                .visibility(visibility)
                .sunriseTime(sunrise)
                .sunsetTime(sunset)
                .moonriseTime(moonrise)
                .moonsetTime(moonset)
                .uv(uv)
                .dailyForecasts(dailyForecasts)
                .hourlyForecasts(hourlyForecasts)
                .build();
    }

    public static String formatTo12HourTime(String dateTimeText) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("h:mm a");

        LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, inputFormat);
        return dateTime.format(outputFormat);
    }

    public static String formatToDayAndTime(String dateTimeText) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("EEE, h:mm a");

        LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, inputFormat);
        return dateTime.format(outputFormat);
    }

    public static void main(String[] args) {
        ApiService service = new ApiService();
        try {
            WeatherData data = service.getWeather("Fredericton");
            System.out.println("City: " + data.getCity());
            System.out.println("Temperature: " + data.getCurrentTemperature() + "°C");
            System.out.println("Condition: " + data.getWeatherCondition());

            System.out.println("\nHourly Forecast:");
            data.getHourlyForecasts().forEach(f ->
                    System.out.println(f.getTime() + ": " + f.getTemp() + "°C, " + f.getCondition()));

            System.out.println("\nDaily Forecast:");
            data.getDailyForecasts().forEach(d ->
                    System.out.println(d.getDate() + ": High " + d.getMaxTemp() + "°C, Low " + d.getMinTemp() + "°C"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
