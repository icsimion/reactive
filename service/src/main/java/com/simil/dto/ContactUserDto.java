package com.simil.dto;

public record ContactUserDto(
		Integer id,

		String firstName,
		String lastName,
		String email,

		String phone) {
}
