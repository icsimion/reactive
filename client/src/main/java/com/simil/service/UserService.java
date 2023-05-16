package com.simil.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.simil.client.ContactUserClient;
import com.simil.client.DirectoryUserClient;
import com.simil.client.LocationUserClient;
import com.simil.dto.ContactUserDto;
import com.simil.dto.DirectoryUserDto;
import com.simil.dto.LocationUserDto;
import com.simil.dto.UserDto;

@Service
public class UserService {

	private final ContactUserClient contactUserClient;
	private final DirectoryUserClient directoryUserClient;
	private final LocationUserClient locationUserClient;

	public UserService(ContactUserClient contactUserClient, DirectoryUserClient directoryUserClient, LocationUserClient locationUserClient) {
		this.contactUserClient = contactUserClient;
		this.directoryUserClient = directoryUserClient;
		this.locationUserClient = locationUserClient;
	}

	public Mono<UserDto> findById(Integer id) {
		Mono<ContactUserDto> contact = contactUserClient.getUser(id);
		Mono<DirectoryUserDto> directory = directoryUserClient.getUser(id);
		Mono<LocationUserDto> location = locationUserClient.getUser(id);

		return Mono.zip(contact, directory, location)
				.map(truple -> new UserDto(truple.getT1(), truple.getT2(), truple.getT3()));
	}

//	public Flux<UserDto> findAll(Pageable pageable) {
//		Mono<ContactUserDto> contact = contactUserClient.getUser(id);
//		Mono<DirectoryUserDto> directory = directoryUserClient.getUser(id);
//		Mono<LocationUserDto> location = locationUserClient.getUser(id);
//
//		return Mono.zip(contact, directory, location)
//				.map(truple -> new UserDto(truple.getT1(), truple.getT2(), truple.getT3()));
//	}

}
