package de.explore;

import jakarta.ws.rs.POST;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient(baseUri = "https://api.openweathermap.org/data/2.5")
public interface MyRemoteService {

    @GET
    @Path("/weather")
    String getWeather(@QueryParam("q") String city,
                      @QueryParam("appid") String apiKey,
                      @QueryParam("units") String units,
                      @QueryParam("lang") String lang);
    @POST
    @Path("/hello/broadcast")
    void broadcast(@QueryParam("text") String text);
}