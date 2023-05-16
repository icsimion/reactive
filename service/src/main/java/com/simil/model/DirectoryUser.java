package com.simil.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

import com.simil.dto.ContactUserDto;
import com.simil.dto.DirectoryUserDto;

@Table("directory")
@Data
public class DirectoryUser {
	@Id
	@Column
	private Integer id;

	@Column("loginId")
	private String loginId;
	@Column("jobTitle")
	private String jobTitle;
	@Column
	private String department;
	@Column
	private String company;

	public DirectoryUserDto getDto() {
		return new DirectoryUserDto(
				this.id,
				this.loginId,
				this.jobTitle,
				this.department,
				this.company);
	}

}
