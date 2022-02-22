package com.alvcalgon.acme.AcmeExplorer.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alvcalgon.acme.AcmeExplorer.bean.Administrator;
import com.alvcalgon.acme.AcmeExplorer.bean.UserAccount;
import com.alvcalgon.acme.AcmeExplorer.configuration.MyUserDetailsService;
import com.alvcalgon.acme.AcmeExplorer.form.AdministratorForm;
import com.alvcalgon.acme.AcmeExplorer.repositories.AdministratorRepository;
import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private ActorService actorService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private UtilityService utilityService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public Administrator findOne(int administratorId) {
		Administrator result = null;

		if (administratorId > 0) {
			Optional<Administrator> actorOpt = administratorRepository.findById(administratorId);

			if (actorOpt != null) {
				result = actorOpt.orElse(null);
			}
		}

		return result;
	}

	public Administrator findByUsername(String username) {
		Administrator result = null;

		if (StringUtils.hasText(username)) {
			result = administratorRepository.findByUserName(username);
		}

		return result;
	}

	public Administrator findByPrincipal() {
		Administrator result = null;

		UserDetails userDetails = utilityService.getUserDetailsByPrincipal();

		if (userDetails != null) {
			String username = userDetails.getUsername();

			result = findByUsername(username);
		}

		return result;
	}

	public Administrator create() {
		Administrator result = new Administrator();

		UserAccount userAccount = userAccountService.create(ConstantPool.ADMINISTRATOR);

		if (userAccount != null) {
			result.setUserAccount(userAccount);
		}

		return result;
	}

	public Administrator save(Administrator administrator) {
		if (administrator != null) {
			return administratorRepository.saveAndFlush(administrator);
		}

		return null;
	}

	public Administrator edit(AdministratorForm administratorForm) {
		if (administratorForm != null) {
			String name = administratorForm.getName().trim();
			String surname = administratorForm.getSurname().trim();
			String email = administratorForm.getEmail().trim();
			String telephoneNumber = administratorForm.getPhoneNumber();
			String username = administratorForm.getUsername().trim();
			String password = administratorForm.getPassword().trim();
			String passwordMatch = administratorForm.getConfirmPassword().trim();

			// String encodedPassword = this.passwordEncoder.encode(password);
			String encodedPassword = this.utilityService.getTextEncoded(password);

			Administrator result = this.findByPrincipal();

			if (result != null) {
				UserAccount userAccount = result.getUserAccount();

				// Passwords must match
				Assert.isTrue(utilityService.checkPassword(password, passwordMatch),
						ConstantPool.ASSERT_ERROR_MSG_PASSWORD_MATCH);

				String usernameDB = userAccount.getUsername();
				// If user has decided changing his or her user name, we must check that new
				// user name doesn't exist
				if (!usernameDB.equals(username)) {
					Assert.isTrue(actorService.checkIsUsernameUnique(username),
							ConstantPool.ASSERT_ERROR_MSG_UNIQUE_USERNAME);
				}

				userAccount.setUsername(username.trim());
				userAccount.setPassword(encodedPassword.trim());

				result.setName(name);
				result.setSurname(surname);
				result.setEmail(email);
				result.setPhoneNumber(telephoneNumber);

				Administrator saved = this.save(result);

				// If condition is true, the administrator has been saved successfully
				if (saved != null) {
					UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

					UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userAccount.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(usernameToken);

					return saved;
				}
			}
		}

		return null;
	}

	public Administrator register(AdministratorForm administratorForm) {
		if (administratorForm != null) {
			Administrator result = this.create();

			UserAccount userAccount = result.getUserAccount();

			String name = administratorForm.getName().trim();
			String surname = administratorForm.getSurname().trim();
			String email = administratorForm.getEmail().trim();
			String telephone = administratorForm.getPhoneNumber();
			String address = administratorForm.getAddress();
			String username = administratorForm.getUsername().trim();
			String password = administratorForm.getPassword().trim();
			String confirmPassword = administratorForm.getConfirmPassword().trim();

			// Passwords must match.
			Assert.isTrue(this.utilityService.checkPassword(password, confirmPassword),
					ConstantPool.ASSERT_ERROR_MSG_PASSWORD_MATCH);
			// User name must be unique
			Assert.isTrue(this.actorService.checkIsUsernameUnique(username),
					ConstantPool.ASSERT_ERROR_MSG_UNIQUE_USERNAME);

			// String encodedPass = this.passwordEncoder.encode(password);
			String encodedPass = this.utilityService.getTextEncoded(password);

			result.setName(name);
			result.setSurname(surname);
			result.setEmail(email);
			result.setPhoneNumber(this.utilityService.getValueOrDefault(telephone));
			result.setAddress(this.utilityService.getValueOrDefault(address));

			userAccount.setUsername(username);
			userAccount.setPassword(encodedPass);

			Administrator saved = this.save(result);

			// If condition is true, the administrator has been saved successfully
			if (saved != null) {
				return saved;
			}
		}

		return null;
	}

}
