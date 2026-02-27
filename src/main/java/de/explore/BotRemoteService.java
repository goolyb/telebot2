package de.explore;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080")
public interface BotRemoteService {

    @POST
    @Path("/hello/broadcast")
    void broadcast(@QueryParam("text") String text);
}
