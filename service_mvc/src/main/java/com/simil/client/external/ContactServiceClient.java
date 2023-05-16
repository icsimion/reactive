package com.simil.client.external;

import org.springframework.stereotype.Service;

import com.simil.model.User;

@Service
public class ContactServiceClient {

	// retrieves:
	// - firstName
	// - lastName
	// - email
	// - phone
	public User getUser(String id) {
		return User.builder()
//				.id(id)
				.build();
	}
}

