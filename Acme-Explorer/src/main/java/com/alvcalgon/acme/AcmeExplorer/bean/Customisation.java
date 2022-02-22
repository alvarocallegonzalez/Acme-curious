package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Customisation extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------------
	@NotBlank
	private String banner;

	@NotBlank
	private String spanishWelcomeMessage;

	@NotBlank
	private String englishWelcomeMessage;

	@Digits(integer = 3, fraction = 2)
	@PositiveOrZero
	private double vatTax;

	@NotBlank
	@Pattern(regexp = "\\+[0-9]{1,3}")
	private String countryCode;

	// Constructors -------------------------------
	public Customisation() {
		super();

		banner = ConstantPool.BLANK;
		spanishWelcomeMessage = ConstantPool.BLANK;
		englishWelcomeMessage = ConstantPool.BLANK;
		vatTax = 0.0;
		countryCode = ConstantPool.BLANK;
	}

}
