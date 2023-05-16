package com.simil.dto;

public record LocationUserDto(
		Integer id,

		String address,
		String city,
		String country,
		String timezone,
		String language
) {
}
