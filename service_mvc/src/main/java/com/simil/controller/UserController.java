package com.simil.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simil.model.User;
import com.simil.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}


	private static final int DELAY_PER_ITEM_MS = 100;

	@GetMapping("/users")
	public Iterable<User> getUsers() throws Exception {
//		Thread.sleep(DELAY_PER_ITEM_MS * quoteMongoBlockingRepository.count());
		return userService.getUsersFromExternalServices();
	}

}
