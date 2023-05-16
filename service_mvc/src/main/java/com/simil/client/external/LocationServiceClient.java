package com.simil.client.external;

import org.springframework.stereotype.Service;

import com.simil.model.User;

@Service
public class LocationServiceClient {

	// retrieves:
	// - address
	// - city
	// - country
	// - timezone
	// - language
	public User getUser(String id) {
		return User.builder()
//				.id(id)
				.build();
	}
}

