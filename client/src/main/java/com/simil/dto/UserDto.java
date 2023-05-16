package com.simil.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Integer id;

	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	private String loginId;
	private String jobTitle;
	private String department;
	private String company;

	private String address;
	private String city;
	private String country;
	private String timezone;
	private String language;

	public UserDto(ContactUserDto contactUser, DirectoryUserDto directoryUser, LocationUserDto locationUser) {

		if (contactUser != null) {
			this.id = contactUser.id();
			this.firstName = contactUser.firstName();
			this.lastName = contactUser.lastName();
			this.email = contactUser.email();
			this.phone = contactUser.phone();
		}

		if (directoryUser != null) {
			this.loginId = directoryUser.loginId();
			this.jobTitle = directoryUser.jobTitle();
			this.department = directoryUser.department();
			this.company = directoryUser.company();
		}

		if (locationUser != null) {
			this.address = locationUser.address();
			this.city = locationUser.city();
			this.country = locationUser.country();
			this.timezone = locationUser.timezone();
			this.language = locationUser.language();
		}
	}
}
