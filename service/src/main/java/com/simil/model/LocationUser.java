package com.simil.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.simil.dto.LocationUserDto;

@Table("location")
@Data
public class LocationUser {
	@Id
	@Column
	private Integer id;

	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String country;
	@Column
	private String timezone;
	@Column
	private String language;

	public LocationUserDto getDto() {
		return new LocationUserDto(
				this.id,
				this.address,
				this.city,
				this.country,
				this.timezone,
				this.language);
	}

}
