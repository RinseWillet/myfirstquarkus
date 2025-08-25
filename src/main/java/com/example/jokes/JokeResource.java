package com.example.jokes;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.List;

@Path("/hello/joke")
@Produces(MediaType.APPLICATION_JSON)
public class JokeResource {

	@Inject
	@RestClient
	ChuckApi chuckApi;

	@Inject
	ChuckConfig config;

	@GET
	@Counted(name = "jokes_served_total", description = "Total jokes served")
	@Timed(name = "joke_latency", description = "Time to serve a joke")
	public Joke getJoke() {
		if(config.preferDefault() && config.defaultCategory().isPresent()) {
			return chuckApi.randomByCategory(config.defaultCategory().get());
		}
		return chuckApi.random();
	}

	@GET
	@Path("/category/{category}")
	@Counted(name = "jokes_by_category_total", description = "Jokes served by category")
	@Timed(name = "joke_by_category_latency", description = "Latency for category jokes")
	public Joke byCategory(@PathParam("category") String category) {
		return chuckApi.randomByCategory(category);
	}

	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> categories() {
		return chuckApi.categories();
	}
}
