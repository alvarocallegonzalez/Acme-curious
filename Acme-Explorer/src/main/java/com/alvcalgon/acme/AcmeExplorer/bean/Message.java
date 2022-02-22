package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Message extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------------
	@PastOrPresent
	@NotNull
	private Date moment;

	@NotBlank
	private String subject;

	@NotBlank
	private String body;

	@NotBlank
	@Pattern(regexp = "^HIGH|NEUTRAL|LOW$")
	private String priority;

	// Relationships --------------------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Actor sender;
	@ManyToMany
	@NotEmpty
	private Collection<Actor> recipients;

	// Constructors ---------------------------
	public Message() {
		super();

		moment = new Date();
		subject = ConstantPool.BLANK;
		body = ConstantPool.BLANK;
		priority = ConstantPool.BLANK;

		recipients = Collections.emptyList();
	}

}
