package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Access(javax.persistence.AccessType.FIELD)
@Getter
@Setter
@ToString
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	// Constructors -------------------------------------------------
	public Authority() {
		super();
	}

	public Authority(String authority) {
		super();

		this.authority = authority;
	}

	// ATTRIBUTES ----------------------------
	@NotBlank
	@Pattern(regexp = "^" + ConstantPool.ADMINISTRATOR + "|" + ConstantPool.RANGER + "|" + ConstantPool.EXPLORER + "|"
			+ ConstantPool.MANAGER + "$")
	private String authority;

	public static Collection<Authority> listAuthorities() {
		Collection<Authority> result;
		Authority authority;

		result = new ArrayList<>();

		authority = new Authority(ConstantPool.ADMINISTRATOR);
		result.add(authority);

		authority = new Authority(ConstantPool.MANAGER);
		result.add(authority);

		authority = new Authority(ConstantPool.RANGER);
		result.add(authority);

		return result;
	}

	@Override
	public int hashCode() {
		return this.getAuthority().hashCode();
	}

	@Override
	public boolean equals(final Object other) {
		boolean result;

		if (this == other)
			result = true;
		else if (other == null)
			result = false;
		else if (!this.getClass().isInstance(other))
			result = false;
		else
			result = (this.getAuthority().equals(((Authority) other).getAuthority()));

		return result;
	}

}
