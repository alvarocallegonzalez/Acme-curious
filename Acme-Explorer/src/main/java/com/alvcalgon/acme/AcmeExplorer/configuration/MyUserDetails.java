package com.alvcalgon.acme.AcmeExplorer.configuration;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import com.alvcalgon.acme.AcmeExplorer.bean.Authority;
import com.alvcalgon.acme.AcmeExplorer.bean.UserAccount;

public class MyUserDetails implements UserDetails {

	// private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean active;
	private Collection<Authority> authorities;

	public MyUserDetails(UserAccount userAccount) {
		this.username = userAccount.getUsername();
		this.password = userAccount.getPassword();
		this.active = userAccount.isEnabled();
		this.authorities = new ArrayList<>(userAccount.getAuthorities());
	}

	public MyUserDetails() {
		super();
	}

	@Override
	public Collection<Authority> getAuthorities() {
		return new ArrayList<Authority>(this.authorities);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
