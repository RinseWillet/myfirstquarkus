package com.example.jokes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/jokes")
@RegisterRestClient(configKey="chuck")
public interface ChuckApi {

	@GET
	@Path("/random")
	@Produces(MediaType.APPLICATION_JSON)
	Joke random();

	@GET
	@Path("/random")
	@Produces(MediaType.APPLICATION_JSON)
	Joke randomByCategory(@QueryParam("category") String category);

	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	List<String> categories();
}
