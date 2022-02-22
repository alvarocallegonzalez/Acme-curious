package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

@Embeddable
@Access(AccessType.FIELD)
public class CreditCard {

	// Attributes ---------------------
	@NotBlank
	private String holderName;

	@NotBlank
	private String brandName;

	@NotBlank
	@CreditCardNumber
	@Column(unique = true)
	private String number;

	@NotBlank
	@Pattern(regexp = "^[0-9]{1,2}$")
	private String expirationMonth;

	@NotBlank
	@Pattern(regexp = "^[0-9]{4}$")
	private String expirationYear;

	@NotBlank
	@Range(min = 100, max = 999)
	private int cvv;

	// Constructors ------------------
	public CreditCard() {
		super();

		holderName = ConstantPool.BLANK;
		brandName = ConstantPool.BLANK;
		number = ConstantPool.BLANK;
		expirationMonth = ConstantPool.BLANK;
		expirationYear = ConstantPool.BLANK;
	}

}
