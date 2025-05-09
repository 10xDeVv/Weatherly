package org.example.weatherapp.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private final HttpClient CLIENT = HttpClient.newHttpClient();

    public String getWeather(String city) throws IOException, InterruptedException {
        String url = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + city + "&days=3"))
                .header("x-rapidapi-key", String.format("%s", System.getenv("API_KEY")))
                .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
