package com.example.jokes;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
class JokeResourceTest {

	@InjectMock
	@RestClient
	ChuckApi chuckApi;

	@Test
	void randomJoke_returnsJson(){
		when(chuckApi.random()).thenReturn(new Joke("abc123", "Roundhouse kicks fix everything"));

		given()
				.when().get("hello/joke")
				.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body("id", is("abc123"))
				.and().body("value", is("Roundhouse kicks fix everything"));
	}

	@Test
	void categories_returnsList() {
		when(chuckApi.categories()).thenReturn(List.of("dev", "animal", "science"));

		given()
				.when().get("/hello/joke/categories")
				.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body("$", hasItems("dev", "animal", "science"));
	}
}
