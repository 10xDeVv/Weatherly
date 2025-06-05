package org.example.weatherapp.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherApiClient {
    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private final String API_KEY = System.getenv("API_KEY");
    private final String BASE_URL = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=";

    public String fetchWeatherData(String city) throws IOException, InterruptedException {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        String url = BASE_URL + encodedCity + "&days=7" ;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", String.format("%s", API_KEY))
                .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}