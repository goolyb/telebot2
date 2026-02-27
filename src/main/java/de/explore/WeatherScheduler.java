package de.explore;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped


public class WeatherScheduler {
    @Inject
    WeatherService weatherService;

    @Inject
    @RestClient
    BotRemoteService botClient;

    @ConfigProperty(name = "weather.city")
    String city;

    @Scheduled(every = "1m")
    public void timer() {
        System.out.println("WeatherScheduler is running");
        String weather = weatherService.getWeather(city);
        System.out.println(weather);
        try {
            botClient.broadcast(weather);
        } catch (Exception e) {
            System.out.println("Failed to broadcast weather: " + e.getMessage());
        }
    }
    public void onStart(@jakarta.enterprise.event.Observes io.quarkus.runtime.StartupEvent ev) {
        System.out.println(weatherService.getWeather(city));
    }
}
