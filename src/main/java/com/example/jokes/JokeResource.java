package com.example.jokes;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/hello/joke")
public class JokeResource {

	@Inject
	@RestClient
	ChuckApi chuckApi;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Joke getJoke() {
		return chuckApi.random();
	}

	@GET
	@Path("/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
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
