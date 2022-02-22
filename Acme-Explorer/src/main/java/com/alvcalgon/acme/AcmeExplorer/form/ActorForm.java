package com.alvcalgon.acme.AcmeExplorer.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.alvcalgon.acme.AcmeExplorer.bean.DomainEntity;
import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class ActorForm extends DomainEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = ConstantPool.MSG_NOT_BLANK)
	@Pattern(regexp = ConstantPool.PATTERN_NO_NUMBERS, message = ConstantPool.MSG_NOT_NUMBERS)
	private String name;

	@NotBlank(message = ConstantPool.MSG_NOT_BLANK)
	@Pattern(regexp = ConstantPool.PATTERN_NO_NUMBERS, message = ConstantPool.MSG_NOT_NUMBERS)
	private String surname;

	@NotBlank(message = ConstantPool.MSG_NOT_BLANK)
	@Email(message = ConstantPool.MSG_EMAIL)
	@Column(unique = true)
	private String email;

	private String phoneNumber;
	private String address;

	@NotBlank(message = ConstantPool.MSG_NOT_BLANK)
	@Length(min = 5, max = 60, message = ConstantPool.MSG_RANGE)
	@Column(unique = true)
	private String username;

	@NotBlank(message = ConstantPool.MSG_NOT_BLANK)
	@Length(min = 10, max = 60, message = ConstantPool.MSG_RANGE)
	private String password;

	@NotBlank(message = ConstantPool.MSG_NOT_BLANK)
	@Length(min = 10, max = 60, message = ConstantPool.MSG_RANGE)
	private String confirmPassword;

	public ActorForm() {
		super();

		this.name = ConstantPool.BLANK;
		this.surname = ConstantPool.BLANK;
		this.email = ConstantPool.BLANK;
		this.phoneNumber = ConstantPool.BLANK;
		this.address = ConstantPool.BLANK;
		this.username = ConstantPool.BLANK;
		this.password = ConstantPool.BLANK;
		this.confirmPassword = ConstantPool.BLANK;
	}

	public ActorForm(String name, String surname, String email, String phoneNumber, String address, String username,
			String password, String confirmPassword) {
		super();

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

}
