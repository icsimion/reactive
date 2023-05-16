package com.simil.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.simil.model.LocationUser;

public interface LocationUserRepository extends R2dbcRepository<LocationUser, Integer> {

}