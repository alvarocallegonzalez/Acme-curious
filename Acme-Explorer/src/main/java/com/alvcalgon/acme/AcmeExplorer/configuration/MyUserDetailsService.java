package com.alvcalgon.acme.AcmeExplorer.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alvcalgon.acme.AcmeExplorer.bean.Authority;
import com.alvcalgon.acme.AcmeExplorer.bean.UserAccount;
import com.alvcalgon.acme.AcmeExplorer.repositories.UserAccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	private static final Log log = LogFactory.getLog(MyUserDetailsService.class);

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findByUsername(username);
		UserDetails result = null;

		if (userAccountOpt != null && StringUtils.hasText(username)) {
			Collection<Authority> authorities = new ArrayList<>();

			UserAccount userAccount = userAccountOpt.get();
			log.debug("UserAccount: " + userAccount);

			if (userAccount != null) {
				String pass = userAccount.getPassword();
				userAccount.setPassword(pass);

				Authority authority = new Authority();

				String authorityName = userAccountRepository.findAuthorityByUserId(userAccount.getId());
				log.debug("userAccount authority: " + authorityName);

				if (StringUtils.hasText(authorityName)) {
					authority.setAuthority(authorityName.trim());

					authorities.add(authority);

					userAccount.setAuthorities(authorities);

					result = userAccountOpt.map(MyUserDetails::new).get();
				}
			}
		} else {
			log.debug("It couldn't found a user account for username: " + username);
		}

		return result;
	}

}
