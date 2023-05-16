package com.simil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simil.client.external.ContactServiceClient;
import com.simil.client.external.DirectoryServiceClient;
import com.simil.client.external.LocationServiceClient;
import com.simil.model.User;

@Service
public class UserService {

	DirectoryServiceClient directoryServiceClient;
	ContactServiceClient contactServiceClient;
	LocationServiceClient locationServiceClient;

	public UserService(DirectoryServiceClient directoryServiceClient, ContactServiceClient contactServiceClient,
			LocationServiceClient locationServiceClient) {
		this.contactServiceClient = contactServiceClient;
		this.directoryServiceClient = directoryServiceClient;
		this.locationServiceClient = locationServiceClient;
	}

	public User getUserFromExternalServices(String id) {


		return User.builder()
//				.id(id)
				.firstName(contactServiceClient.getUser(id).getFirstName())
				.build();
	}

	public List<User> getUsersFromExternalServices() {
		return directoryServiceClient.getUsers();
	}
}
