package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Trip extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------
	@NotBlank
	@Pattern(regexp = "^\\d{6}-\\w{3}$")
	@Column(unique = true)
	private String ticker;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String price;
	@NotBlank
	private String currency;
	@NotBlank
	private String requirements;
	@Future
	private Date publicationDate;
	@Future
	private Date startDate;
	@Future
	private Date endDate;
	private boolean status;
	private String reasonCancellation;

	// Relationships -------------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Category category;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Manager manager;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Ranger ranger;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Stage> stages;

	@ManyToMany
	private Collection<Tag> tags;
}
