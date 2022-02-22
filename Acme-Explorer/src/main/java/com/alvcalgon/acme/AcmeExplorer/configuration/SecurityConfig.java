package com.alvcalgon.acme.AcmeExplorer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll() //
				.antMatchers("/static/**").permitAll() //
				.antMatchers("/resources/**").permitAll() //
				.antMatchers("/actor/administrator/register").permitAll() //
				.antMatchers("/actor/manager/register").hasAuthority(ConstantPool.ADMINISTRATOR)
				.antMatchers("/actor/ranger/register").anonymous() //
				.antMatchers("/actor/explorer/register").anonymous() //
				.and() //
				.formLogin() //
				.loginPage("/login") //
				.defaultSuccessUrl("/home") //
				.and() //
				.logout() //
				.logoutSuccessUrl("/home");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(5);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());

		return authenticationProvider;
	}

}
