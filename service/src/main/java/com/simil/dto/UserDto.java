package com.simil.dto;

import lombok.Getter;
import lombok.Setter;

import com.simil.model.ContactUser;
import com.simil.model.DirectoryUser;
import com.simil.model.LocationUser;

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

	public UserDto(ContactUser contactUser, DirectoryUser directoryUser, LocationUser locationUser) {
		this.id = contactUser.getId();

		this.firstName = contactUser.getFirstName();
		this.lastName = contactUser.getLastName();
		this.email = contactUser.getEmail();
		this.phone = contactUser.getPhone();

		if (directoryUser != null) {
			this.loginId = directoryUser.getLoginId();
			this.jobTitle = directoryUser.getJobTitle();
			this.department = directoryUser.getDepartment();
			this.company = directoryUser.getCompany();
		}

		if (locationUser != null) {
			this.address = locationUser.getAddress();
			this.city = locationUser.getCity();
			this.country = locationUser.getCountry();
			this.timezone = locationUser.getTimezone();
			this.language = locationUser.getLanguage();
		}
	}
}
