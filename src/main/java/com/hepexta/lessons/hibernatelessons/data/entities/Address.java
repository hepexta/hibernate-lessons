package com.hepexta.lessons.hibernatelessons.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

	@Column(name="ADDRESS_LINE_1")
	private String addressLine1;

	@Column(name="ADDRESS_LINE_2")
	private String addressLine2;

	@Column(name="CITY")
	private String city;

	@Column(name="STATE")
	private String state;

	@Column(name="ZIP_CODE")
	private String zipCode;
}