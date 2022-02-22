package com.alvcalgon.acme.AcmeExplorer.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class UserAccount extends DomainEntity implements UserDetails {

	// Serialization identifier -----------------------------------------------
	private static final long serialVersionUID = 1L;

	// Attributes -------------------------
	@NotBlank
	@Length(min = 5, max = 60)
	@Column(unique = true)
	private String username;

	@NotBlank
	@Length(min = 10, max = 60)
	private String password;

	private boolean isBanned;

	@NotEmpty
	@Valid
	@ElementCollection
	private Collection<Authority> authorities;

	public UserAccount() {
		super();

		this.username = ConstantPool.BLANK;
		this.password = ConstantPool.BLANK;
		this.authorities = new ArrayList<>();
	}

	public void addAuthority(final Authority authority) {
		Assert.notNull(authority, ConstantPool.ASSERT_ERROR_MSG_NULL_ELEMENT);
		Assert.isTrue(!this.authorities.contains(authority), ConstantPool.ASSERT_ERROR_MSG_AUTHORITY_INCLUDED);

		this.authorities.add(authority);
	}

	public void removeAuthority(final Authority authority) {
		Assert.notNull(authority, ConstantPool.ASSERT_ERROR_MSG_NULL_ELEMENT);
		Assert.isTrue(this.authorities.contains(authority), ConstantPool.ASSERT_ERROR_MSG_AUTHORITY__DID_NOT_INCLUDE);

		this.authorities.remove(authority);
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return !this.isBanned;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return Boolean.TRUE;
	}

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", authorities=" + authorities + "]";
	}

}
