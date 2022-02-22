package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Stage extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------
	@Min(0)
	private int number;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@PositiveOrZero
	@Digits(integer = 6, fraction = 2)
	private double price;

}
