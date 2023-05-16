package com.simil.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.simil.dto.ContactUserDto;
import com.simil.dto.DirectoryUserDto;
import com.simil.dto.LocationUserDto;
import com.simil.dto.UserDto;
import com.simil.model.ContactUser;
import com.simil.model.DirectoryUser;
import com.simil.model.LocationUser;
import com.simil.repository.ContactUserRepository;
import com.simil.repository.DirectoryUserRepository;
import com.simil.repository.LocationUserRepository;

@Service
public class UserService {

	private final ContactUserRepository contactUserRepository;
	private final DirectoryUserRepository directoryUserRepository;
	private final LocationUserRepository locationUserRepository;

	public UserService(ContactUserRepository contactUserRepository, DirectoryUserRepository directoryUserRepository, LocationUserRepository locationUserRepository) {
		this.contactUserRepository = contactUserRepository;
		this.directoryUserRepository = directoryUserRepository;
		this.locationUserRepository = locationUserRepository;
	}

	public Mono<UserDto> findById(Integer id) {
		return Mono.zip(
				contactUserRepository.findById(id),
				directoryUserRepository.findById(id),
				locationUserRepository.findById(id))
				.map(truple -> new UserDto(truple.getT1(), truple.getT2(), truple.getT3()));
	}

	public Flux<UserDto> findAllPaged(Pageable pageable) {
		return contactUserRepository.findAllByIdNotNull(pageable)
				.map(contactUser -> new UserDto(contactUser, null, null));
	}

	public Flux<UserDto> findAllByIds(Iterable<Integer> ids) {
		return contactUserRepository.findAllById(ids)
				.map(contactUser -> new UserDto(contactUser, null, null));
	}

	public Mono<ContactUserDto> findContactUserById(Integer id) {
		return contactUserRepository.findById(id)
				.map(ContactUser::getDto);
	}

	public Mono<DirectoryUserDto> findDirectoryUserById(Integer id) {
		return directoryUserRepository.findById(id)
				.map(DirectoryUser::getDto);
	}

	public Mono<LocationUserDto> findLocationUserById(Integer id) {
		return locationUserRepository.findById(id)
				.map(LocationUser::getDto);
	}

}
