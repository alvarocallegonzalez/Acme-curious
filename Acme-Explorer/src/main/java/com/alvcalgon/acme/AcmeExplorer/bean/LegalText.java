package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class LegalText extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes -------------------
	@NotBlank
	private String title;

	@NotBlank
	private String body;

	@NotBlank
	private String laws;

	@PastOrPresent
	@NotNull
	private Date registeredMoment;

	private boolean draftMode;

	// Relationships ----------------

	// Constructors -----------------
	public LegalText() {
		super();

		title = ConstantPool.BLANK;
		body = ConstantPool.BLANK;
		laws = ConstantPool.BLANK;
		registeredMoment = new Date();
	}
}
