package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class ContactEmergency extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------
	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	private String phoneNumber;

	// Relationships -------------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Explorer explorer;

	// Constructor ---------------------
	public ContactEmergency() {
		super();

		name = ConstantPool.BLANK;
		email = ConstantPool.BLANK;
		phoneNumber = ConstantPool.BLANK;
		explorer = new Explorer();
	}

}
