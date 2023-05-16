package com.simil.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.simil.dto.UserDto;
import com.simil.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public Mono<UserDto> getUser(@PathVariable int userId) {
		return userService.findById(userId);
	}

//	@GetMapping("/{userId}")
//	public Flux<UserDto> getUsers(Pageable pageable) {
//		return userService.findById(pageable);
//	}
}
