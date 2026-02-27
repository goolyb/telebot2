package de.explore;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped

public class WeatherService {
    @ConfigProperty(name = "weather.api.key")
    String apiKey;
    @Inject
    @RestClient
    MyRemoteService weatherClient;

    public String getWeather(String city) {
        try {
            String json = weatherClient.getWeather(city, apiKey, "metric", "eng");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            int temp = node.get("main").get("temp").asInt();
            String description = node.get("weather").get(0).get("description").asText();
            return "Temperature: " + temp + "°C\nWeather: " + description;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
