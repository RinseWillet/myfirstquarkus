package com.example.jokes;

import io.smallrye.config.ConfigMapping;

import java.util.Optional;

@ConfigMapping(prefix = "app.chuck")
public interface ChuckConfig {

	/** Fallback category when none/invalid is provided */
	Optional<String> defaultCategory();

	/** Toggle to make /hello/joke use the default category instead of random */
	boolean preferDefault();

	/** Client read timeout in milliseconds (we’ll map it to the REST client too) */
	Optional<Integer> timeoutMs();
}
