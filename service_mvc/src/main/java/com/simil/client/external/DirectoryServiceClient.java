package com.simil.client.external;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simil.model.User;

@Service
@Slf4j
public class DirectoryServiceClient {

	// retrieves:
	// - loginId
	// - jobTitle
	// - department
	// - company
	public User getUser(String id) {


		return User.builder()
				.id(Integer.getInteger(id))
				.build();
	}

	public List<User> getUsers() {

		try (Stream<String> lines = Files.lines(
				Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("static/MOCK_DATA.json")).toURI()))) {
			return lines
					.skip(1) // header
					.map(string -> {
						try {
							return new ObjectMapper().readValue(string, User.class);
						} catch (JsonProcessingException e) {
							log.error("Error parsing JSON", e);
						}
						return null;
					})
					.collect(Collectors.toList());
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}

	}
}


