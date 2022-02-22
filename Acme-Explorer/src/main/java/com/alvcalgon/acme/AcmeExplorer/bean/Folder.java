package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Folder extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------
	@NotBlank
	private String title;
	private boolean isSystemFolder;

	// Relationships -------------------
	@Valid
	@NotNull
	@ManyToOne(optional = true)
	private Folder parent;

	@ManyToMany
	private Collection<Message> messages;

	// Constructors --------------------
	public Folder() {
		super();

		title = ConstantPool.BLANK;
		messages = Collections.emptyList();
	}

}
