package com.simil.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;

import com.simil.model.ContactUser;

public interface ContactUserRepository extends R2dbcRepository<ContactUser, Integer> {

	Flux<ContactUser> findAllByIdNotNull(Pageable pageable);
	Flux<ContactUser> findAllById(Iterable<Integer> ids, Pageable pageable);
}