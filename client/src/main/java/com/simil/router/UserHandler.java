package com.simil.router;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import com.simil.dto.UserDto;
import com.simil.service.UserService;

@Component
public class UserHandler {

	private final UserService userService;

	public UserHandler(UserService userService) {
		this.userService = userService;
	}

	public Mono<ServerResponse> findUserById(ServerRequest request) {
		return userService.findById(Integer.valueOf(request.pathVariable("userId")))
				.flatMap(post -> ServerResponse.ok().body(Mono.just(post), UserDto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> findUsersPageable(ServerRequest request) {
		return userService.findById(Integer.valueOf(request.pathVariable("userId")))
				.flatMap(post -> ServerResponse.ok().body(Mono.just(post), UserDto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> findUsersByIds(ServerRequest request) {
		return userService.findById(Integer.valueOf(request.pathVariable("userId")))
				.flatMap(post -> ServerResponse.ok().body(Mono.just(post), UserDto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> findContactById(ServerRequest request) {
		return userService.findById(Integer.valueOf(request.pathVariable("userId")))
				.flatMap(post -> ServerResponse.ok().body(Mono.just(post), UserDto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> findDirectoryById(ServerRequest request) {
		return userService.findById(Integer.valueOf(request.pathVariable("userId")))
				.flatMap(post -> ServerResponse.ok().body(Mono.just(post), UserDto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> findLocationById(ServerRequest request) {
		return userService.findById(Integer.valueOf(request.pathVariable("userId")))
				.flatMap(post -> ServerResponse.ok().body(Mono.just(post), UserDto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
