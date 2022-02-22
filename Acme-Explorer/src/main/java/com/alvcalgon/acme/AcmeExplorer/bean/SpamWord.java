package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class SpamWord extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes -------------------
	@NotBlank
	@Column(unique = true)
	private String title;

	// Constructors -----------------
	public SpamWord() {
		super();

		title = ConstantPool.BLANK;
	}

}
