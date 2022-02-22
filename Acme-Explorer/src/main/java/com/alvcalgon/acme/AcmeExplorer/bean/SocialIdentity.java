package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class SocialIdentity extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes -----------------------------
	@NotBlank
	private String nickname;

	@NotBlank
	private String socialNetwork;

	@NotBlank
	@URL
	private String linkProfile;

	@URL
	private String photo;

	// Relationships --------------------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Actor actor;

	public SocialIdentity() {
		nickname = ConstantPool.BLANK;
		socialNetwork = ConstantPool.BLANK;
		linkProfile = ConstantPool.BLANK;
		photo = ConstantPool.BLANK;
	}

}
