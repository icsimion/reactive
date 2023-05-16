package com.simil.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.simil.model.DirectoryUser;

public interface DirectoryUserRepository extends R2dbcRepository<DirectoryUser, Integer> {

}