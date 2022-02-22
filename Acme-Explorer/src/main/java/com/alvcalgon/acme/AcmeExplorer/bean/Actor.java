package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Actor extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ------------------------
	@NotBlank
	@Pattern(regexp = ConstantPool.PATTERN_NO_NUMBERS, message = "0")
	private String name;

	@NotBlank
	@Pattern(regexp = ConstantPool.PATTERN_NO_NUMBERS, message = "0")
	private String surname;

	@Transient
	private String fullname;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	private String phoneNumber;

	private String address;

	// Relationships ---------------------
	@Valid
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccount userAccount;

	public Actor() {
		super();

		name = ConstantPool.BLANK;
		surname = ConstantPool.BLANK;
		email = ConstantPool.BLANK;
		phoneNumber = ConstantPool.BLANK;
		address = ConstantPool.BLANK;

		userAccount = new UserAccount();
	}

//	public String getFullname() {
//		return name + ConstantPool.BLANK + surname;
//	}

}
