package com.simil.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

import com.simil.dto.ContactUserDto;

@Table("contact")
@Data
public class ContactUser {
	@Id
	@Column
	private Integer id;

	@Column("firstName")
	private String firstName;
	@Column("lastName")
	private String lastName;
	@Column
	private String email;
	@Column
	private String phone;

	public ContactUserDto getDto() {
		return new ContactUserDto(
				this.id,
				this.firstName,
				this.lastName,
				this.email,
				this.phone);
	}
}
