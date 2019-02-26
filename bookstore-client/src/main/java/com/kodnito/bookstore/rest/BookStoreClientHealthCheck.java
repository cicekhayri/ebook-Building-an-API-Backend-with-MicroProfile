package com.kodnito.bookstore.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class BookStoreClientHealthCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "bookservice.url")
    private String bookServiceUrl;

    @Override
    public HealthCheckResponse call() {

        boolean isHealthy = false;

        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(bookServiceUrl).request(MediaType.APPLICATION_JSON).get();
            
            if (response.getStatus() != 200) {
                isHealthy = false;
            }
            isHealthy = true;
        } catch (Exception e) {
            isHealthy = false;
        }

        if (!isHealthy) {
            return HealthCheckResponse.named("bookservice")
                    .withData("service", "not available")
                    .down().build();
        }

        return HealthCheckResponse.named("bookservice")
                .withData("service", "available")
                .up().build();
    }

}

