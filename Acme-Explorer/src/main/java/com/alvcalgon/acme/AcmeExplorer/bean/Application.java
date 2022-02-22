package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Application extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes -------------------
	@PastOrPresent
	private Date moment;

	private boolean status;

	private String comments;

	@Embedded
	@Valid
	private CreditCard creditCard;

	// Relationships ----------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Explorer applicant;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Trip trip;
}
