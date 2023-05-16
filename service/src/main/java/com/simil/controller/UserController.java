package com.simil.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.simil.dto.ContactUserDto;
import com.simil.dto.DirectoryUserDto;
import com.simil.dto.LocationUserDto;
import com.simil.dto.UserDto;
import com.simil.service.UserService;

@RestController
@RequestMapping("/service/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public Mono<UserDto> getUserById(@PathVariable int userId) {
		return userService.findById(userId);
	}

	@GetMapping("/page")
	public Flux<UserDto> getUsersPaged(Pageable pageable) {
		return userService.findAllPaged(pageable);
	}

	@GetMapping("/ids/{ids}")
	public Flux<UserDto> getUsersByIds(@PathVariable String ids) {
		List<Integer> split = Arrays.stream(ids.split(";")).map(Integer::parseInt).collect(Collectors.toList());
		return userService.findAllByIds(split);
	}

	@GetMapping("/{userId}/contact")
	public Mono<ContactUserDto> getContactUserById(@PathVariable int userId) {
		return userService.findContactUserById(userId);
	}

	@GetMapping("/{userId}/directory")
	public Mono<DirectoryUserDto> getDirectoryUserById(@PathVariable int userId) {
		return userService.findDirectoryUserById(userId);
	}

	@GetMapping("/{userId}/location")
	public Mono<LocationUserDto> getLocationUserById(@PathVariable int userId) {
		return userService.findLocationUserById(userId);
	}
}
