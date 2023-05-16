package com.simil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
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

}
