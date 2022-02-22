package com.alvcalgon.acme.AcmeExplorer.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alvcalgon.acme.AcmeExplorer.bean.Authority;
import com.alvcalgon.acme.AcmeExplorer.bean.UserAccount;
import com.alvcalgon.acme.AcmeExplorer.repositories.UserAccountRepository;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	// CRUD Methods ------------------------------
	public UserAccount create(String authority) {
		UserAccount userAccount = new UserAccount();

		if (StringUtils.hasText(authority)) {
			Authority authorityObj = new Authority(authority);

			userAccount.addAuthority(authorityObj);
		}

		return userAccount;
	}

	public UserAccount save(UserAccount userAccount) {
		if (userAccount != null) {
			return userAccountRepository.save(userAccount);
		}

		return null;
	}
}
