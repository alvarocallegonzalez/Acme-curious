package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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
public class Category extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes --------------
	@NotBlank
	private String name;

	// Relationships -----------
	@Valid
	@NotNull
	@ManyToOne(optional = true)
	public Category parent;

	// Constructors -------------
	public Category() {
		super();

		name = ConstantPool.BLANK;
	}

}
